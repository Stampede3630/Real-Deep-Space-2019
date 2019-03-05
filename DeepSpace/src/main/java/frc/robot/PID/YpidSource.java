package frc.robot.PID;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import frc.robot.Constants;

public class YpidSource implements PIDSource 
{

    double yInput, ty;

    public YpidSource() 
    {

    }

    //necessary PID code
    public PIDSourceType getPIDSourceType() 
    {
        return PIDSourceType.kDisplacement;
    }

    //why is this still here?
    public void setPIDSourceType(PIDSourceType source) 
    {

    }

    //tells us our Y PID
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