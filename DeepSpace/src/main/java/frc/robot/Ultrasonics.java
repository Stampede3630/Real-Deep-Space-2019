package frc.robot;

import edu.wpi.first.wpilibj.Ultrasonic;

public class Ultrasonics {

    Ultrasonic sensor = new Ultrasonic(1, 1);

    double range;

    public void robotInit() {
        sensor.setAutomaticMode(true);
        sensor.setEnabled(true);
    }

    public void ultrasonicSample() {
        range = sensor.getRangeInches();
    }
}