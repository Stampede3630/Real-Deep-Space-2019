package frc.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CamAuto {

    RobotMap robotMap = RobotMap.getRobotMap();
    Timer timer;
    ManipulatorMode manipulatorMode;

    public CamAuto() {
        timer = new Timer();
        timer.start();
    }
    
    public void camTestMethod() {        
        /*
        if (timer.get() > 2) {
            robotMap.drive.driveCartesian(0, 0, 0);
        }

        else {
            robotMap.drive.driveCartesian(0, 0.4, 0);
        }*/

        SmartDashboard.putNumber("Timer Value", timer.get());

        if (timer.get() < 1) {
            robotMap.drive.driveCartesian(0, 0.6, 0);
        }

        else {
            robotMap.drive.driveCartesian(0, 0, 0);
            //manipulatorMode.deployAuto(true);
        }
    }
}