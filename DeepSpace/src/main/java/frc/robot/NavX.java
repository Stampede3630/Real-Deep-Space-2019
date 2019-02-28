package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class NavX {

    AHRS navxAhrs = new AHRS(SPI.Port.kMXP);

    double alphaX = navxAhrs.getWorldLinearAccelX();
    double alphaY = navxAhrs.getWorldLinearAccelY();
    double jerkX;
    double jerkY;
    double time = 0.02;
    double jerkThreshold = 0.4;

    boolean hasCrashed = false;

    public void crashTest() {
        jerkX = alphaX / time;
        jerkY = alphaY / time;

        if ((Math.abs(jerkX) > jerkThreshold || Math.abs(jerkY) > jerkThreshold)) {
            hasCrashed = true;
            SmartDashboard.putBoolean("Crash detected", hasCrashed);
        }
    }
}