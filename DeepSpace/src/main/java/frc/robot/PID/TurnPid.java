package frc.robot.PID;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PIDBase.Tolerance;
import frc.robot.Constants;
import frc.robot.RobotMap;

public class TurnPid implements PIDOutput{

    public PIDController turnController;
    private double zOutput;
    RobotMap robotMap;
    private MyCustomTolerance newTolerance;

    public TurnPid()
    {
        robotMap = RobotMap.getRobotMap();
    }

    /**
     * Defines new PID controller. 
     * Sets output range and tolerance.
     */
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

    /**
     * Sends error and PID output to SmartDashboard. 
     * @return PID output for motors (left/right)
     */
    public double getTurnOutput()
    {
        SmartDashboard.putNumber("turn output", zOutput);
        SmartDashboard.putNumber("Zpid setpoint", turnController.getSetpoint());
        return zOutput;
    }

    /**
     * Assigns the output to a local variable
     * @param output from PID
     */
    public void pidWrite(double output)
    {
        zOutput = output;
    }

    /**
     * Nested class within TurnPid.java.
     * Overrides onTarget() method.
     */
    public class MyCustomTolerance implements Tolerance 
    {
        private final double m_posTolerance;
        private final double m_velocityLimit;

        MyCustomTolerance() 
        {
            m_posTolerance = Constants.turnTolerance;
            m_velocityLimit = Constants.pidLowSpeed; 
        }

        /**
         * Overrides the requirements of onTarget() method
         * @return error and speed are in bounds
         */
        @Override
        public boolean onTarget() 
        {
            return Math.abs(turnController.getError()) < m_posTolerance && Math.abs(turnController.get()) < m_velocityLimit;
        }
    }
}