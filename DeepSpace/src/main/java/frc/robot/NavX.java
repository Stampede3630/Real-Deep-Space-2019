package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class NavX {

    AHRS navx;
    
    double alphaX;
    double alphaY;
    final double time = 0.02;
    
    public NavX() {
        navx = new AHRS(SPI.Port.kMXP);
    }

    public void angularAccelerationCalculations() {
        alphaX = (navx.getRawGyroX() * (Math.PI / 180)) / time;
        alphaY = (navx.getRawGyroY() * (Math.PI / 180)) / time;
    }

    public void accelerationNullification() {
        if (alphaX > alphaY) {
            alphaY = 0;
        }

        else if (alphaY > alphaX) {
            alphaX = 0;
        }
    }

    public void accelerationDiagnostics() {
        angularAccelerationCalculations();
        accelerationNullification();
        SmartDashboard.putNumber("ForwardBackward acceleration", alphaY);
        SmartDashboard.putNumber("LeftRight acceleration", alphaX);
    }
}