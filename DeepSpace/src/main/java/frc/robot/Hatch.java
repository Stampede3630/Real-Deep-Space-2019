/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;

public class Hatch implements ManipulatorMode {
    RobotMap robotMap;
    boolean manipulatorOut;
    Manipulator manipulator;
    Timer circleTimer;

    public Hatch(Manipulator manipulator) 
    {
        robotMap = RobotMap.getRobotMap();
        this.manipulator = manipulator;
        manipulatorOut = false;

        circleTimer = new Timer();
    }

    public void engage() //slide out
    {
        if(robotMap.bumperR.get())
        {
            manipulatorOut = true;
            robotMap.hatchExtend.set(DoubleSolenoid.Value.kReverse);
        }
    }

    public void intake() 
    {
        if(robotMap.getTrigger()<0.2&&!robotMap.hatchButton.get())
        {
            robotMap.talonHatch.set(.5);
        }
        else{
            robotMap.talonHatch.set(0);
        }

    }

    public void deploy(boolean rocketMode) //left trigger
    {
        if(robotMap.getTrigger()<0.2)
        {
            robotMap.talonHatch.set(-.5);
        }
        else
        {
            robotMap.talonHatch.set(0);
        }


        /*if(robotMap.getTrigger()<=-0.75)
        {
            circleTimer.start();
            robotMap.hatchDeploy.set(DoubleSolenoid.Value.kForward);
            robotMap.hatchExtend.set(DoubleSolenoid.Value.kForward);
        }
        if(circleTimer.get()>=1)
        {
            circleTimer.stop();
            circleTimer.reset();
            robotMap.hatchDeploy.set(DoubleSolenoid.Value.kReverse);
        }*/
    }

    public void disengage() //slide in
    {
        if(robotMap.bumperL.get())
        {
            robotMap.hatchExtend.set(DoubleSolenoid.Value.kForward);
        }
    }

    public void intakeAuto()
    {

    }

}
