package frc.robot.PID;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import frc.robot.Constants;

public class XpidSource implements PIDSource {

    static double xDist, tx;

    public XpidSource() {

    }

    //some necessary code for setting up PID
    public PIDSourceType getPIDSourceType() {
        return PIDSourceType.kDisplacement;
    }

    //why is this here?
    public void setPIDSourceType(PIDSourceType source) {

    }

    //gets our X PID
    public double pidGet() {
        return Constants.tx;
    }
}

//PID calculations
//        double dist = Math.tan(degreesToRadians(tx))*((Constants.h2 - Constants.h1) / Math.tan(degreesToRadians(Constants.alphaYOne + ty)));
//        SmartDashboard.putNumber("xDistance", dist);
/* 
public static double radiansToDegrees(double theta) {
    double degrees = theta * (180 / Math.PI);
    return degrees;
}

public static double degreesToRadians(double theta) {
    double radians = theta * (Math.PI / 180);
    return radians;
}
*/
