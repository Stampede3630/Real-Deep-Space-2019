package frc.robot.PID;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.*;

public class Ypid implements PIDOutput{

    public PIDController yController;
    private YpidSource source;
    private double yOutput;

    public Ypid()
    {

    }

    public void ypidSetup()
    {
        source = new YpidSource();
        yController = new PIDController(Constants.yKP, Constants.yKI, Constants.yKD, source, this);
        yController.setOutputRange(-Constants.yOutput, Constants.yOutput);
        yController.setAbsoluteTolerance(Constants.yTolerance);
        yController.disable();
        LiveWindow.add(yController);
    }

    public void pidWrite(double output)
    {
        yOutput = output;
    }

    public double getYOutput()
    {
        SmartDashboard.putNumber("y PID error", yController.getError());
        SmartDashboard.putNumber("y PID output", yOutput);
        return yOutput;
    }
}