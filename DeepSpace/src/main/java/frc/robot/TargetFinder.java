/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

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

    public TargetFinder()
    {
        this.robotMap = robotMap;
        this.driveTrain = driveTrain;  
    }
    
    
    public void searchTarget()
    {
        double tv = NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("tv").getDouble(0);
        if (tv == 1)
        {
            driveTrain.ypid.yController.setSetpoint(0);
            driveTrain.xpid.xController.setSetpoint(0);
            driveTrain.turnPID.zController.disable();
        }
        else 
        {
            driveTrain.turnPID.zController.enable();
            driveTrain.turnPID.zController.setSetpoint(robotMap.ahrs.getAngle() + 3);
        }
    }

    


}

