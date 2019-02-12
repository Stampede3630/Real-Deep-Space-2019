/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Ball implements ManipulatorMode {
    
    RobotMap robotMap;
    Manipulator manipulator;

    
    //not actually talons
    public Ball (Manipulator manipulator) 
    {
        robotMap = RobotMap.getRobotMap();
        this.manipulator = manipulator;

    }

    public void engage () 
    {
        //get to ball - driver/limelight?
    }

    public void intake () //left trigger
    {
        if(robotMap.getTrigger()<=-0.2&&robotMap.ballStop.getVoltage()<4.0) //change ultrasonic number
        {
            robotMap.talonBallIntake.set(-1);
            robotMap.talonBallShooter.set(1);
        }
        else
        {
            robotMap.talonBallIntake.set(0);
            robotMap.talonBallShooter.set(0);
        }
    }

    public void deploy (boolean rocketMode) //right trigger
    {

        if(robotMap.getTrigger()>0)
        {
            SmartDashboard.putNumber("trigger", robotMap.getTrigger()); //works
            if(rocketMode)
            {
                robotMap.talonBallShooter.set(robotMap.getTrigger());
                robotMap.talonBallIntake.set(-robotMap.getTrigger()); //does not work
            }
            else
            {
                robotMap.talonBallShooter.set(-robotMap.getTrigger());
                robotMap.talonBallIntake.set(-0.3);
            }
        }
        else
        {
            robotMap.talonBallIntake.set(0);
            robotMap.talonBallShooter.set(0);
        }
    }

    public void disengage () 
    {

    }
}
