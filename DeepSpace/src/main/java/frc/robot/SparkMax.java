package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.XboxController;

public class SparkMax {

    XboxController xBox;

    CANSparkMax motor;
    CANEncoder encoder;
    double velocity;

    public static final int deviceID = 0;

    public final double highGoalHeight = 1.9;
    public final double mediumGoalHeight = 1.2;
    public static final double neoRadius = 0.03;
    public static final int theta = 85;
    public static final double gravity = 9.80;

    public SparkMax() {
        xBox = new XboxController(0);
        motor = new CANSparkMax(deviceID, MotorType.kBrushless);
        encoder = motor.getEncoder();
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
     * @param height - meausres in meters
     * @param motor - the motor we're using to shoot
     */

    //something to consider for 10K - this might set us aside from other teams
    public double goalShot(double omega, double height, CANSparkMax motor) {
        motor.set(rpmToRadPerSec(omega));
        velocity = omega * neoRadius;
        double xDistance = ((velocity * radiansToDegrees(Math.cos(theta))) * ((velocity * radiansToDegrees(Math.sin(theta)) + Math.sqrt(Math.pow(velocity, 2) * Math.pow(radiansToDegrees(Math.cos(theta)), 2) * Math.pow(radiansToDegrees(Math.tan(theta)), 2) - (2 * gravity * height))))) / gravity;
        return xDistance;
    }
}