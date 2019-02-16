package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class NavX {

    AHRS navx;
    
    boolean collisionDetected;
    
    double lastWorldLinearAccelX;
    double lastWorldLinearAccelY;
    double currentWorldLinearAccelX;
    double currentWorldLinearAccelY;
    double currentJerkX;
    double currentJerkY;
    double jerkThreshhold = 0.5; //inches per second cubed

    Timer crashTImer = new Timer();

    RobotMap robotMap = RobotMap.getRobotMap();

    public NavX() {
        navx = new AHRS(SPI.Port.kMXP);
    }

    public void collisionDetector() {
        if (crashTImer.get() < 2) {
            robotMap.drive.driveCartesian(0, -0.6, 0);
        }

        collisionDetected = false;
        
        currentWorldLinearAccelX = navx.getWorldLinearAccelX();
        currentJerkX = currentWorldLinearAccelX - lastWorldLinearAccelX;
        lastWorldLinearAccelX = currentWorldLinearAccelX;
        
        currentWorldLinearAccelY = navx.getWorldLinearAccelY();
        currentJerkY = currentWorldLinearAccelY - lastWorldLinearAccelY;
        lastWorldLinearAccelY = currentWorldLinearAccelY;

        if ((Math.abs(currentJerkX) > jerkThreshhold) || (Math.abs(currentJerkY) > jerkThreshhold)) {
            collisionDetected = true;
        }

        robotMap.drive.driveCartesian(0, 0, 0);

        SmartDashboard.putBoolean("Collision Detected", collisionDetected);
        
    }

    public void accelerationDiagnostics() {
        SmartDashboard.putNumber("X acceleration", currentWorldLinearAccelX);
        SmartDashboard.putNumber("Y acceleration", currentWorldLinearAccelY);
    }
}