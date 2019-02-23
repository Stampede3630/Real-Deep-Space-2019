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

    double distance;

    public static final int deviceID = 1;

    public static final double mediumGoalHeight = 0.825; //32.48 inches
    public static final double neoRadius = 0.03; //1.18 inches
    public static final int theta = 85; //degrees
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

    //something to consider for 10K - this might set us aside from other teams
    public double goalShot(double omega, CANSparkMax motor) {
        motor.set(rpmToRadPerSec(omega));
        velocity = omega * neoRadius;
        double xDistance = ((velocity * radiansToDegrees(Math.cos(theta))) * ((velocity * radiansToDegrees(Math.sin(theta)) + Math.sqrt(Math.pow(velocity, 2) * Math.pow(radiansToDegrees(Math.cos(theta)), 2) * Math.pow(radiansToDegrees(Math.tan(theta)), 2) - (2 * gravity * mediumGoalHeight))))) / gravity;
        return xDistance;
    }
}