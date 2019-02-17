package frc.robot.PID;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.PIDBase.Tolerance;

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

    public class MyCustomTolerance implements Tolerance {
        private final double m_posTolerance;
        private final double m_velocityLimit;
        ;  
        MyCustomTolerance() {
        m_posTolerance = Constants.xTolerance;
        m_velocityLimit = Constants.pidLowSpeed; 
        }
        @Override
        public boolean onTarget() {
        return Math.abs(yController.getError()) <   m_posTolerance  && Math.abs(yController.get()) <  m_velocityLimit;
        }
    }
}