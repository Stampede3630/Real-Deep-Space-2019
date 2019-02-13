/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Ball implements ManipulatorMode {
    
    RobotMap robotMap;
    Manipulator manipulator;
    Timer shootOut;

    
    //not actually talons
    public Ball (Manipulator manipulator) 
    {
        robotMap = RobotMap.getRobotMap();
        this.manipulator = manipulator;

        shootOut = new Timer();
        shootOut.reset();
    }

    public void engage () 
    {
        //get to ball - driver/limelight?
    }

    public void intake (boolean autonomous) //left trigger
    {
        if(autonomous)
        {
            if(NetworkTableInstance.getDefault().getTable("limelight-one").getEntry("ta").getDouble(0)>=96)
            {
                robotMap.talonBallIntake.set(-0.8);
            }
            if(robotMap.ballStop.getVoltage()>=4)
            {
                robotMap.talonBallIntake.set(0);
            }
        }
        else if(!autonomous)
        {
            if(robotMap.getTrigger()>=0&&robotMap.ballStop.getVoltage()<4.0) //change ultrasonic number
            {
                robotMap.talonBallIntake.set(-0.8);
                robotMap.talonBallShooter.set(1);
            }
            else
            {
                robotMap.talonBallIntake.set(0);
                robotMap.talonBallShooter.set(0);
            }
        }
    }

    public void deploy (boolean rocketMode, boolean autonomous) //right trigger
    {
        if(!autonomous)
        {
            if(robotMap.getTrigger()<0)
            {
                SmartDashboard.putNumber("trigger", robotMap.getTrigger()); //works
                if(rocketMode)
                {
                    robotMap.talonBallShooter.set(-robotMap.getTrigger());
                    robotMap.talonBallIntake.set(robotMap.getTrigger());
                }
                else
                {
                    robotMap.talonBallShooter.set(robotMap.getTrigger());
                    robotMap.talonBallIntake.set(-0.3);
                }
            }   
            else
            {
                robotMap.talonBallIntake.set(0);
                robotMap.talonBallShooter.set(0);
            }
        }
        else if(autonomous)
        {
            shootOut.start();
            if(rocketMode&&shootOut.get()<1.5)
            {
                robotMap.talonBallShooter.set(-robotMap.getTrigger());
                robotMap.talonBallIntake.set(robotMap.getTrigger());
            }
            if(!rocketMode&&shootOut.get()<1.5)
            {
                robotMap.talonBallShooter.set(robotMap.getTrigger());
                robotMap.talonBallIntake.set(-0.3);
            }
            if(shootOut.get()>=1.5)
            {
                robotMap.talonBallIntake.set(0);
                robotMap.talonBallShooter.set(0);
            }
        }
    }

    public void disengage () 
    {

    }
}
