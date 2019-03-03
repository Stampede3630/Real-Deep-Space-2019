package frc.robot;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Diagnostics
{

    public RobotMap robotMap;

    public Diagnostics()
    {
        this.robotMap = RobotMap.getRobotMap();
    }

    public void toSmartDashboard()
    {
        SmartDashboard.putBoolean("limelight one processing", Constants.limelight.equals("limelight-one"));
        SmartDashboard.putBoolean("limelight two processing", Constants.limelight.equals("limelight-two"));
        SmartDashboard.putBoolean("ball manipulator on", Constants.ballManipulator);
        SmartDashboard.putBoolean("toRocket", Constants.toRocket);
        SmartDashboard.putNumber("Pressure", getPSI());
        SmartDashboard.putNumber("ballStopTop", robotMap.ballStopTop.getVoltage());
        SmartDashboard.putNumber("ahrs", robotMap.ahrs.getYaw());
        SmartDashboard.putBoolean("ball follower", Constants.ballFollowerOn);
        SmartDashboard.putBoolean("hatchGrasshopperLimit", robotMap.hatchPositionLimitSwitch.get());
        SmartDashboard.putBoolean("cargoGrasshopperLimit", robotMap.cargoPositionLimitSwitch.get());
        SmartDashboard.putNumber("highReedSwitch",robotMap.highReedSwitch.getVoltage());
        SmartDashboard.putNumber("lowReedSwitch", robotMap.lowReedSwitch.getVoltage());
        SmartDashboard.putNumber("acceleration", robotMap.ahrs.getRawAccelY());
    }

    public double getPSI()
    {
        double sensorV = robotMap.pressureLevel.getVoltage();
        double psi = 250*(sensorV / 5) - 25;
        psi = Math.round(psi);
        return psi;
    }

    public void limelightValues()
    {
        Constants.tv = NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("tv").getDouble(0);
        Constants.ty = NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("ty").getDouble(0);
        Constants.ta = NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("ta").getDouble(0);
        Constants.tx = NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("tx").getDouble(0);
    }
}