package frc.robot;

import edu.wpi.first.wpilibj.Timer;

public class CamAuto {

    RobotMap robotMap = RobotMap.getRobotMap();
    Timer timer = new Timer();

    public void camTestMethod() {
        if (timer.get() < 2) {
            robotMap.drive.driveCartesian(0, 0.4, 0);
        }

        else if (timer.get() > 2) {
            robotMap.drive.driveCartesian(0, 0, 0);
        }
    }
}