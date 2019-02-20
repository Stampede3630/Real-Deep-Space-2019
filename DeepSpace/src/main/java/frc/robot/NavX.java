package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class NavX {

    AHRS navx;
    
    double accelerationX;
    double accelerationY;
    final double time = 0.02;
    
    public NavX() {
        navx = new AHRS(SPI.Port.kMXP);
    }

    public void velocityNullification() {

        accelerationX = navx.getWorldLinearAccelX();
        accelerationY = navx.getWorldLinearAccelY();

        if (Math.abs(accelerationY) > 0) {
            accelerationX = 0;
        }

        else if (Math.abs(accelerationX) > 0) {
            accelerationY = 0;
        }
    }

    public void velocityDiagnostics() {
        SmartDashboard.putNumber("LeftRight velocty", navx.getWorldLinearAccelX());
        SmartDashboard.putNumber("ForwardBackward velocity", navx.getWorldLinearAccelY());
    }
}