/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Timer;

public class Ball implements ManipulatorMode {
    
    RobotMap robotMap;
    Manipulator manipulator;
    boolean sensorIntake, manualIntake;
    Timer intakeTimer;

    public Ball (Manipulator manipulator) 
    {
        robotMap = RobotMap.getRobotMap();
        this.manipulator = manipulator;
        sensorIntake = true;
        manualIntake = false;
        intakeTimer = new Timer();
    }

    /**
     * Overrides normal intake method, isn't stopped by the limit switch
     */
    public void engage () 
    {
        if(robotMap.bumperR.get())
        {
            robotMap.talonBallIntake.set(-0.7);
        }
        else
        {
            robotMap.talonBallIntake.set(0);
        }
    }

    /**
     * Cargo intaking method, intake is stopped when the limit switch is trigered.
     */
    public void intake () //right trigger
    {
/*        if(robotMap.getTrigger()>0.2&&robotMap.ballStop.getVoltage()<4.0)
        {
            sensorIntake = false;
            manualIntake = true;
            robotMap.talonBallIntake.set(-0.8);
            robotMap.talonBallShooter.set(1);
        }
        else if(robotMap.ballStop.getVoltage()>4.0&&manualIntake)
        {
            sensorIntake = true;
            manualIntake = false;
            intakeTimer.start();
        }
        else if(intakeTimer.get()<0.6&&sensorIntake)
        {
            robotMap.talonBallIntake.set(-0.8);
            robotMap.talonBallShooter.set(1);
        }
        else 
        {
            manualIntake = false;
            sensorIntake = false;
            intakeTimer.stop();
            intakeTimer.reset();
            robotMap.talonBallIntake.set(0);
            robotMap.talonBallShooter.set(0);
        }
        */
        
        
        if(robotMap.getTrigger()>0.2&&robotMap.ballButton.get() == true && Constants.ballBottom) 
        {
            robotMap.talonBallIntake.set(-1);
            robotMap.talonBallShooter.set(0);
//            System.out.println("Ball is going through the bottom");
        }
//        else if (robotMap.getTrigger()>0.2&&robotMap.ballStopBottom.getVoltage()<4.0 && Constants.ballBottom)
        else if(robotMap.getTrigger()>0.2&&robotMap.ballButton.get() == true && Constants.ballTop)
        {
            robotMap.talonBallIntake.set(0);
            robotMap.talonBallShooter.set(1);
//            System.out.println("Ball is going through the top");
        }
     /*   else if(robotMap.getTrigger()>0.2&&robotMap.ballStopBottom.getVoltage()<4.0)
        {
            robotMap.talonBallIntake.set(1);
            robotMap.talonBallShooter.set(-1);
        }*/
        else
        {
            robotMap.talonBallIntake.set(0);
            robotMap.talonBallShooter.set(0);
        } 
    }

    /**
     * Deploys the cargo
     * @param rocketMode - are we deploying into rocket
     */
    public void deploy (boolean rocketMode) //left trigger
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
                robotMap.talonBallShooter.set(0.6*robotMap.getTrigger());
                robotMap.talonBallIntake.set(-0.3);
            }
        }   
        else
        {
            robotMap.talonBallIntake.set(0);
            robotMap.talonBallShooter.set(0);
        }
    }

    /**
     * Deploys the cargo through the intake - prevents us from geting suck on cargo
     */
    public void disengage () 
    {
        if(robotMap.bumperL.get())
        {
            robotMap.talonBallIntake.set(0.7);
        }
        else
        {
            robotMap.talonBallIntake.set(0);
        }
    }

    /**
     * Logic for autonomous ball intaking
     */
    public void intakeAuto()
    {
        if(robotMap.ballStop.getVoltage()>=4)
        {
            robotMap.talonBallIntake.set(0);
//            System.out.println("stop intaking");
        }
        else if(Constants.ta>=60||Constants.tv==0)
        {
            robotMap.talonBallIntake.set(-1);
//            System.out.println("intaking");
        }
    }
}
