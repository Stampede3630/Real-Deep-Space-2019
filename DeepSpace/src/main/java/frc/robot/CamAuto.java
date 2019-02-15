package frc.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CamAuto {

    RobotMap robotMap = RobotMap.getRobotMap();
    Timer timer;

    public CamAuto() {
        timer = new Timer();
        timer.start();
    }
    
    public void camTestMethod() {        
        SmartDashboard.putNumber("Timer Value", timer.get());
        if (timer.get() > 2) {
            robotMap.drive.driveCartesian(0, 0, 0);
        }

        else {
            robotMap.drive.driveCartesian(0, 0.4, 0);
        }
    }
}