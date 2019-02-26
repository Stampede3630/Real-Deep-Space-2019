package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.XboxController;

public class MotorLaunch {

    XboxController xBox;
    double velocity;

    public final double mediumGoalHeight = 46.85; //halfway point on
    public static final double neoRadius = 3;
    public static final int theta = 85;
    public static final double gravity = 32.15;

    public MotorLaunch() {
        xBox = new XboxController(0);
    }

    public double radiansToDegrees(double theta) {
        theta = theta * (180 / Math.PI);
        return theta;
    }

    public double rpmToRadPerSec(double rpm) {
        rpm = rpm * (Math.PI / 30);
        return rpm;
    }

    /**
     * @param omega - measured in Hz
     * @param height - meausres in inches
     * @param motor - the motor we're using to shoot
     */

    //something to consider for 10K - this might set us aside from other teams
    public double goalShot(double omega, double height, WPI_TalonSRX motor) {
        motor.set(rpmToRadPerSec(omega));
        velocity = omega * neoRadius;
        double xDistance = ((velocity * radiansToDegrees(Math.cos(theta))) * ((velocity * radiansToDegrees(Math.sin(theta)) + Math.sqrt(Math.pow(velocity, 2) * Math.pow(radiansToDegrees(Math.cos(theta)), 2) * Math.pow(radiansToDegrees(Math.tan(theta)), 2) - (2 * gravity * height))))) / gravity;
        return xDistance;
    }
}