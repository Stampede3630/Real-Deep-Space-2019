package frc.robot;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import frc.robot.Constants;
import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.Timer;

/**
 * Add your docs here.
 */
public class TargetFinder {
    DriveTrain driveTrain;
    RobotMap robotMap;
    Timer targetTimer;
    boolean rotationDone, ballGrabbed;

    public TargetFinder(RobotMap robotMap, DriveTrain driveTrain)
    {
        this.robotMap = robotMap;
        this.driveTrain = driveTrain;  
        rotationDone = false;
        ballGrabbed = false;
    }
    
    
    public void searchTarget()
    {
        double tv = NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("tv").getDouble(0);
    
        if (tv == 1)
        {
        //ball follower
            driveTrain.xpid.xController.setSetpoint(0);
            driveTrain.ypid.yController.setSetpoint(100);

            driveTrain.xpid.xController.enable();
            driveTrain.ypid.yController.enable();

            if(!driveTrain.xpid.xController.onTarget()&&!rotationDone)
            {
                robotMap.drive.driveCartesian(0, 0, driveTrain.xpid.getXOutput());
            }
            else if(!ballGrabbed)
            {
                rotationDone = true;
                robotMap.drive.driveCartesian(driveTrain.xpid.getXOutput(), driveTrain.ypid.getYOutput(), 0);//drives x and y
                if(driveTrain.ypid.yController.onTarget()&&driveTrain.xpid.xController.onTarget());
                {
                    rotationDone = false;
                    ballGrabbed = true;
                }
            }
            
        }
        else 
        {
            driveTrain.turnPID.zController.setSetpoint(robotMap.ahrs.getAngle() + 3); //can we make this non-PID?
        }
    }

    


}
