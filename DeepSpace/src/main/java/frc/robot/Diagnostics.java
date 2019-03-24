package frc.robot;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Diagnostics
{

    public RobotMap robotMap;

    public Diagnostics()
    {
        this.robotMap = RobotMap.getRobotMap();
    }

    /**
     * Reports everything that is going on periodically
     */
    public void toSmartDashboard()
    {
        SmartDashboard.putBoolean("limelight one processing", Constants.limelight.equals("limelight-one"));
        SmartDashboard.putBoolean("limelight two processing", Constants.limelight.equals("limelight-two"));
        SmartDashboard.putBoolean("ball manipulator on", Constants.ballManipulator);
        SmartDashboard.putBoolean("toRocket", Constants.toRocket);
        SmartDashboard.putNumber("Pressure", getPSI());
        SmartDashboard.putNumber("ballStopTop", robotMap.ballStop.getVoltage());
        SmartDashboard.putNumber("ahrs", robotMap.ahrs.getYaw());
        SmartDashboard.putBoolean("ball follower", Constants.ballFollowerOn);
        SmartDashboard.putBoolean("hatchGrasshopperLimit", robotMap.hatchPositionLimitSwitch.get());
        SmartDashboard.putBoolean("cargoGrasshopperLimit", robotMap.cargoPositionLimitSwitch.get());
        SmartDashboard.putBoolean("ballstop button", robotMap.ballButton.get());
        SmartDashboard.putNumber("highReedSwitch",robotMap.highReedSwitch.getVoltage());
        SmartDashboard.putNumber("lowReedSwitch", robotMap.lowReedSwitch.getVoltage());
        SmartDashboard.putBoolean("light sensor", robotMap.ballStop.getVoltage()>4);
        SmartDashboard.putNumber("acceleration", robotMap.ahrs.getRawAccelY());
    }

    /**
     * Calculates the pressure stored in all air tanks.
     * @return the amount of PSI stored
     */
    public double getPSI()
    {
        double sensorV = robotMap.pressureLevel.getVoltage();
        double psi = 250*(sensorV / 5) - 25;
        psi = Math.round(psi);
        return psi;
    }

    /**
     * Sets the values from currently processing Limelight to a variable
     */
    public void limelightValues()
    {
        Constants.tv = NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("tv").getDouble(0);
        Constants.ty = NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("ty").getDouble(0);
        Constants.ta = NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("ta").getDouble(0);
        Constants.tx = NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("tx").getDouble(0);
    }

    /**
     * Checks the value of mode selecting widget (hatch/cargo)
     */
    public void getForwardMode()
    {
        Constants.forwardFromWidget = Robot.hatchBallSelected.getString("");
        //Constants.forwardFromWidget = SmartDashboard.getString("Test Forward Chooser", "");
    }

    /**
     * Retracts all climbing pistons
     */
    public void SolenoidReset() 
    {
        robotMap.solenoidBack.set(Value.kReverse);
        robotMap.solenoidFront.set(Value.kReverse);
    }

    /**
     * Periodically updates the pipeline number on currently processing Limelight
     */
    public void periodicVisionChange()
    {
        NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("pipeline").setNumber(Constants.pipelineNumber);   
    }
}