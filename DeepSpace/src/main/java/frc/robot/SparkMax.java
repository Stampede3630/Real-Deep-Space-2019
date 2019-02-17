package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SparkMax {

    XboxController xBox;

    CANSparkMax motor;
    CANEncoder encoder;

    public static final int deviceID = 1;

    public SparkMax() {
        xBox = new XboxController(0);
        motor = new CANSparkMax(deviceID, MotorType.kBrushless);
        encoder = motor.getEncoder();
    }

    //we want to use radians per second for our calculations
    public double rpmToRadiansPerSecond(double rpm) {
        rpm = rpm * ((Math.PI) / 30);
        return rpm;
    }

    //basic outline for SDB code
    public void teleopPeriodic() {
        motor.set(xBox.getY());
        SmartDashboard.putNumber("Motor speed", rpmToRadiansPerSecond(encoder.getVelocity()));
    }

    public void motorRamp(double rpmSpeed) {
        motor.set(rpmSpeed);
    }
}