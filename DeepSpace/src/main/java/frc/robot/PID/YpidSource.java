package frc.robot.PID;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.networktables.*;
import frc.robot.Constants;

public class YpidSource implements PIDSource{

    double yDist, ty;

    public YpidSource()
    {

    }

    public PIDSourceType getPIDSourceType()
    {
        return PIDSourceType.kDisplacement;
    }

    public void setPIDSourceType(PIDSourceType source)
    {

    }

    public double degreesToRadians(double theta) {
        double radians = theta * (Math.PI / 180);
        return radians;
    }

    public double pidGet()
    {
        ty = NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry(Constants.yPIDsource).getDouble(0);

//        yDist = (Constants.h2 - Constants.h1) / Math.tan(0 + ty);
//        SmartDashboard.putNumber("yDistance", yDist);
        return ty;
    }
}