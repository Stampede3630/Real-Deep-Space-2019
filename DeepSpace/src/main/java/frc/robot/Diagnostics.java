package frc.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Diagnostics
{

    public boolean ballManipulator, toRocket;
    public RobotMap robotMap;

    public Diagnostics()
    {
        this.robotMap = RobotMap.getRobotMap();
    }

    

    public void toSmartDashboard()
    {
        SmartDashboard.putBoolean("limelight one processing", Constants.limelight.equals("limelight-one"));
        SmartDashboard.putBoolean("limelight two processing", Constants.limelight.equals("limelight-two"));
        SmartDashboard.putBoolean("ball manipulator on", ballManipulator);
        SmartDashboard.putBoolean("toRocket", toRocket);
        SmartDashboard.putNumber("Pressure", getPSI());
        SmartDashboard.putNumber("ballStop", robotMap.ballStop.getVoltage());
        SmartDashboard.putString("y PID source", Constants.yPIDsource);
    }

    public double getPSI()
    {
        double sensorV = robotMap.pressureLevel.getVoltage();
        double psi = 250*(sensorV / 5) - 25;
        psi = Math.round(psi);
        return psi;
    }
}