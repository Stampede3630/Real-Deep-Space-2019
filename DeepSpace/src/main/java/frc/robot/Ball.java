/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

public class Ball implements ManipulatorMode {
    
    RobotMap robotMap;
    Manipulator manipulator;
    
    public Ball (Manipulator manipulator) 
    {
        robotMap = RobotMap.getRobotMap();
        this.manipulator = manipulator;
    }

    public void engage () 
    {

    }

    public void intake () //right trigger
    {
        if(robotMap.getTrigger()>0.2&&robotMap.ballStop.getVoltage()<4.0)
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
        if(robotMap.ballStop.getVoltage()>=4)
        {
            robotMap.talonBallIntake.set(0);
        }
        else if(Constants.ta>=45||Constants.tv==0)
        {
            robotMap.talonBallIntake.set(-1);
        }
    }
}
