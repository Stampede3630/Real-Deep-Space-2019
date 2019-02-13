package frc.robot.PID;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.networktables.*;
import frc.robot.Choosers;
import frc.robot.Constants;
import frc.robot.Robot;

public class YpidSource implements PIDSource{

    double yDist, yInput;
    boolean yesTa = Robot.choosers.getBallTarget();


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

    public double pidGet(Object Robot)
    {
        if (!yesTa)
        {
        yInput = NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("ty").getDouble(0);
        }
        else
        {
        yInput = Constants.fullTargetTa - NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("ta").getDouble(0);
        }

//        yDist = (Constants.h2 - Constants.h1) / Math.tan(0 + ty);
//        SmartDashboard.putNumber("yDistance", yDist);
        return yInput;
    }
}