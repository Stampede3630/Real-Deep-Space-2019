package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
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

    public NavX() {
        navx = new AHRS(SPI.Port.kMXP);
    }

    public void collisionDetector() {
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

        SmartDashboard.putBoolean("Collision Detected", collisionDetected);
    }

    public void accelerationDiagnostics() {
        SmartDashboard.putNumber("X acceleration", currentWorldLinearAccelX);
        SmartDashboard.putNumber("Y acceleration", currentWorldLinearAccelY);
    }
}