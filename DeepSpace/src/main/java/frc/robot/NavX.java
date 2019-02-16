package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class NavX {

    AHRS navx;
    
    boolean collisionDetected = false;
    
    double lastWorldLinearAccelX = 0;
    double lastWorldLinearAccelY = 0;
    double currentWorldLinearAccelX = 0;
    double currentWorldLinearAccelY = 0;
    double currentJerkX = 0;
    double currentJerkY = 0;
    double jerkThreshhold = 0.5; //inches per second cubed

    public NavX() {
        navx = new AHRS(SPI.Port.kMXP);
    }

    public boolean collisionDetector() {
        //robotMap.drive.driveCartesian(0, -0.4, 0);
        
        currentWorldLinearAccelX = navx.getWorldLinearAccelX();
        currentJerkX = currentWorldLinearAccelX - lastWorldLinearAccelX;
        lastWorldLinearAccelX = currentWorldLinearAccelX;
        
        currentWorldLinearAccelY = navx.getWorldLinearAccelY();
        currentJerkY = currentWorldLinearAccelY - lastWorldLinearAccelY;
        lastWorldLinearAccelY = currentWorldLinearAccelY;

        if ((Math.abs(currentJerkY) > jerkThreshhold)) {
            collisionDetected = true;
            //robotMap.drive.driveCartesian(0, 0, 0);
            System.out.print("Crash boom :)");
        }

        else {
            System.out.print("No crash boom :(");
        }

        SmartDashboard.putBoolean("Collision Detected", collisionDetected);
        return collisionDetected;
        
    }

    public void resetCollisionDetector() {
        collisionDetected = false;
    }

    public void accelerationDiagnostics() {
        SmartDashboard.putNumber("X acceleration", currentWorldLinearAccelX);
        SmartDashboard.putNumber("Y acceleration", currentWorldLinearAccelY);
        SmartDashboard.putNumber("X Jerk", currentJerkX);
        SmartDashboard.putNumber("Y Jerk", currentJerkY);
    }
}