package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class NavX {

    AHRS navx;
    
    double velocityX;
    double velocityY;
    final double time = 0.02;
    
    public NavX() {
        navx = new AHRS(SPI.Port.kMXP);
    }

    public void velocityNullification() {

        velocityX = navx.getVelocityX();
        velocityY = navx.getVelocityY();

        if (Math.abs(velocityY) > 0) {
            velocityX = 0;
        }

        else if (Math.abs(velocityX) > 0) {
            velocityY = 0;
        }
    }

    public void velocityDiagnostics() {
        SmartDashboard.putNumber("ForwardBackward velocity", navx.getVelocityY());
        SmartDashboard.putNumber("LeftRight velocty", navx.getVelocityX());
    }
}