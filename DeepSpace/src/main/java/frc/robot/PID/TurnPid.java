package frc.robot.PID;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PIDBase.Tolerance;
import frc.robot.Constants;
import frc.robot.RobotMap;

public class TurnPid implements PIDOutput{

    public PIDController zController;
    private double zOutput;
    RobotMap robotMap;
    private MyCustomTolerance newTolerance;

    public TurnPid()
    {
        robotMap = RobotMap.getRobotMap();
    }

    public void turnPidSetup()
    {
        newTolerance = new MyCustomTolerance();
        zController = new PIDController(Constants.turnKP, Constants.turnKI, Constants.turnKD, robotMap.ahrs, this);
        zController.setInputRange(-180, 180);
        zController.setOutputRange(-Constants.turnOutput, Constants.turnOutput);
        zController.setTolerance(newTolerance);
        zController.setContinuous(true);
        zController.disable();
        LiveWindow.add(zController);
    }

    public double getTurnOutput()
    {
        SmartDashboard.putNumber("turn output", zOutput);
        SmartDashboard.putNumber("Zpid setpoint", zController.getSetpoint());
        return zOutput;
    }

    public void pidWrite(double output)
    {
        zOutput = output;
    }

    public class MyCustomTolerance implements Tolerance 
    {
        private final double m_posTolerance;
        private final double m_velocityLimit;

        MyCustomTolerance() 
        {
            m_posTolerance = Constants.turnTolerance;
            m_velocityLimit = Constants.pidLowSpeed; 
        }

        @Override
        public boolean onTarget() 
        {
            return Math.abs(zController.getError()) < m_posTolerance && Math.abs(zController.get()) < m_velocityLimit;
        }
    }
}