package frc.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CamAuto {

    RobotMap robotMap = RobotMap.getRobotMap();
    Timer timer;
    NavX navx = new NavX();

    public CamAuto() {
        timer = new Timer();
        timer.start();
    }
    
    public void camTestMethod() {
        SmartDashboard.putNumber("Timer Value", timer.get());

        if (timer.get() < 4 && !navx.collisionDetector()) {
            robotMap.drive.driveCartesian(0, 0.4, 0);
        }
        
        else {
            robotMap.drive.driveCartesian(0, 0, 0);
            //for ball deploy auto to work isLaunch has to be true
            //Robot.manipulator.manipulatorMode.deployAuto(false);
        }

        navx.accelerationDiagnostics();
    }
}