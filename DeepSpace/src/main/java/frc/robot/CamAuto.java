package frc.robot;

import edu.wpi.first.wpilibj.Timer;

public class CamAuto {

    RobotMap robotMap = RobotMap.getRobotMap();
    Timer timer = new Timer();
    
    public void camTestMethod() {

        timer.start();
        if (timer.get() > 2) {
            robotMap.drive.driveCartesian(0, 0, 0);
        }

        else {
            robotMap.drive.driveCartesian(0, 0.4, 0);
        }
    }
}