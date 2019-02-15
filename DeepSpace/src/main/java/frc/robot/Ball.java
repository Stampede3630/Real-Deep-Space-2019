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
    boolean isLaunched;
    
    //not actually talons
    public Ball (Manipulator manipulator) 
    {
        robotMap = RobotMap.getRobotMap();
        this.manipulator = manipulator;

        shootOut = new Timer();
        shootOut.reset();
        isLaunched = false;

    }

    public void engage () 
    {
        //get to ball - driver/limelight?
    }

    public void intake () //left trigger
    {
            SmartDashboard.putBoolean("debug intake", robotMap.getTrigger()>0.8&&robotMap.ballStop.getVoltage()<4.0);
            if(robotMap.getTrigger()>0.8&&robotMap.ballStop.getVoltage()<4.0) //change ultrasonic number
            {
                
                System.out.println("intake running");
                robotMap.talonBallIntake.set(-0.8);
                robotMap.talonBallShooter.set(1);
            }
            else
            {
                System.out.println("intake stopped");
                robotMap.talonBallIntake.set(0);
                robotMap.talonBallShooter.set(0);
            }
    }

    public void deploy (boolean rocketMode) //right trigger
    {
            if(robotMap.getTrigger()<-0.2)
            {
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

    public void disengage () 
    {

    }

    public void intakeAuto()
    {
        System.out.println("autonomous intake");
        if(robotMap.ballStop.getVoltage()>=4)
        {
            robotMap.talonBallIntake.set(0);
        }
        else if(NetworkTableInstance.getDefault().getTable("limelight-one").getEntry("ta").getDouble(0)>=45||NetworkTableInstance.getDefault().getTable("limelight-one").getEntry("tv").getDouble(0)==0)
        {
            robotMap.talonBallIntake.set(-1);
        }
    }

    public void deployAuto(boolean rocketMode)
    {
        if(!isLaunched)
        {
            shootOut.reset();
            shootOut.start();
            isLaunched = true;
            
        }

        if(rocketMode&&shootOut.get()<1.5)
        {
            robotMap.talonBallShooter.set(Constants.rocketBallLaunchDownSpeed);
            robotMap.talonBallIntake.set(Constants.rocketBallLaunchUpSpeed);//check
            
        }
        if(!rocketMode&&shootOut.get()<1.5)
        {
            robotMap.talonBallShooter.set(Constants.rocketBallLaunchUpSpeed);
            robotMap.talonBallIntake.set(-0.3);  //why is this 0.3? Please explain yourself. Thank you :).
            
        }
        if(shootOut.get()>=1.5)
        {
            robotMap.talonBallIntake.set(0);
            robotMap.talonBallShooter.set(0);
            
        }
    }
}
