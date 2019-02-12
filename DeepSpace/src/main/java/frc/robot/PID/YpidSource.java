package frc.robot.PID;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.*;
import frc.robot.Constants;

public class YpidSource implements PIDSource{

    double yDist;
    double ty = NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("ty").getDouble(0);

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

    public double pidGet() {
        yDist = (Constants.h2 - Constants.h1) / Math.tan(degreesToRadians(Constants.cameraMountAngle + ty));
        SmartDashboard.putNumber("Y error", yDist);
        return yDist;
    }
}