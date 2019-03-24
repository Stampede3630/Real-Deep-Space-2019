package frc.robot.PID;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import frc.robot.Constants;

public class YpidSource implements PIDSource{

    double yInput, ty;

    public YpidSource()
    {

    }

    @Override
    public PIDSourceType getPIDSourceType()
    {
        return PIDSourceType.kDisplacement;
    }

    @Override
    public void setPIDSourceType(PIDSourceType source)
    {

    }

    @Override
    public double pidGet()
    {
        if (Constants.ballFollowerOn)
        {
            yInput = Constants.fullTargetTa - Constants.ta;
        }
        else
        {
            yInput = Constants.ty;
        }
        
        return yInput;
    }
}
/*
    public double degreesToRadians(double theta) {
        double radians = theta * (Math.PI / 180);
        return radians;
    }
*/