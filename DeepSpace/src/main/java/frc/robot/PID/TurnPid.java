package frc.robot.PID;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PIDBase.Tolerance;
import frc.robot.Constants;
import frc.robot.RobotMap;

public class TurnPid implements PIDOutput 
{

    public PIDController turnController;
    private double zOutput;
    RobotMap robotMap;
    private MyCustomTolerance newTolerance;

    //constructs our robot map
    public TurnPid() 
    {
        robotMap = RobotMap.getRobotMap();
    }

    //Z axis PID
    public void turnPidSetup() 
    {
        newTolerance = new MyCustomTolerance();
        turnController = new PIDController(Constants.turnKP, Constants.turnKI, Constants.turnKD, robotMap.ahrs, this);
        turnController.setInputRange(-180, 180);
        turnController.setOutputRange(-Constants.turnOutput, Constants.turnOutput);
        turnController.setTolerance(newTolerance);
        turnController.setContinuous(true);
        turnController.disable();
        LiveWindow.add(turnController);
    }

    //shows us what our Z PID values are
    public double getTurnOutput() 
    {
        SmartDashboard.putNumber("turn output", zOutput);
        SmartDashboard.putNumber("Zpid setpoint", turnController.getSetpoint());
        return zOutput;
    }

    //outputs all of our Z PID values
    public void pidWrite(double output) 
    {
        zOutput = output;
    }

    //Z Tolerance
    public class MyCustomTolerance implements Tolerance 
    {
        private final double m_posTolerance;
        private final double m_velocityLimit;

        MyCustomTolerance() 
        {
            m_posTolerance = Constants.turnTolerance;
            m_velocityLimit = Constants.pidLowSpeed; 
        }

        //tells us if we're on target - centered
        @Override
        public boolean onTarget() 
        {
            return Math.abs(turnController.getError()) < m_posTolerance && Math.abs(turnController.get()) < m_velocityLimit;
        }
    }
}