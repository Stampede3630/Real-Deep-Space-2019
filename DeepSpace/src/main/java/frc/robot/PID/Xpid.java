package frc.robot.PID;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

public class Xpid implements PIDOutput{

    public PIDController xController;
    private XpidSource source;
    private double xOutput;

    public Xpid()
    {

    }

    public void xpidSetup()
    {
        source = new XpidSource();
        xController = new PIDController(Constants.xKP, Constants.xKI, Constants.xKD, source, this);
        xController.setOutputRange(-Constants.xOutput, Constants.xOutput);
        xController.setAbsoluteTolerance(Constants.xTolerance);
        xController.disable();
        LiveWindow.add(xController);
    }

    public void pidWrite(double output)
    {
        xOutput = output;
    }

    public double getXOutput()
    {
        SmartDashboard.putNumber("x PID error", source.pidGet());
        SmartDashboard.putNumber("x PID output", xOutput);
        return xOutput;
    }
}