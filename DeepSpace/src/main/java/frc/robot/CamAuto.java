package frc.robot;

import edu.wpi.first.wpilibj.Timer;

public class CamAuto {

    RobotMap robotMap = RobotMap.getRobotMap();
    Timer timer = new Timer();
    public CamAuto()
    {
        timer.start();
    }
    public void camTestMethod() {

        robotMap.drive.driveCartesian(0, 0.4, 0);

        if (timer.get() > 5) {
            robotMap.drive.driveCartesian(0, 0, 0);
        }
    }
}