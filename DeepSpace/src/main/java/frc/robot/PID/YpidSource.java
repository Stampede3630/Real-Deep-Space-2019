package frc.robot.PID;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.networktables.*;
import frc.robot.Choosers;
import frc.robot.Constants;
import frc.robot.Robot;

public class YpidSource implements PIDSource{

    double yDist, yInput;
    private Choosers yesTa = Robot.choosers;


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
        if (!Robot.choosers.getBallTarget())
        {
        yInput = NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("ty").getDouble(0);
       // yInput = Math.tan(degreesToRadians(75 - NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("ty").getDouble(0))) * 13;
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