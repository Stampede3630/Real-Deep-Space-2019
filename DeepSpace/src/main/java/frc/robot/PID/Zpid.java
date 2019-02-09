package frc.robot.PID;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.RobotMap;

public class Zpid implements PIDOutput{

    public PIDController zController;
    private double zOutput;
    RobotMap robotMap;

    public Zpid()
    {
        robotMap = RobotMap.getRobotMap();
    }

    public void zpidSetup()
    {
        robotMap.ahrs.setPIDSourceType(PIDSourceType.kDisplacement);
        zController = new PIDController(Constants.zKP, Constants.zKI, Constants.zKD, robotMap.ahrs, this);
        zController.setInputRange(-Constants.zInput, Constants.zInput);
        zController.setOutputRange(-Constants.zOutput, Constants.zOutput);
        zController.setAbsoluteTolerance(Constants.turnTolerance);
        zController.setContinuous(true);
        zController.disable();
        LiveWindow.add(zController);
    }

    public void pidWrite(double output)
    {
        zOutput = output;
    }

    public double getZOutput()
    {
        SmartDashboard.putNumber("turn output", zOutput);
        SmartDashboard.putNumber("Zpid setpoint", zController.getSetpoint());
        return zOutput;
    }
}