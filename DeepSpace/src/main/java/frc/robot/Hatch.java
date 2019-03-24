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

    /**
     * Pushes the Hatch manipulator slides out.
     */
    public void engage() //slide out
    {
        if(robotMap.bumperR.get())
        {
            manipulatorOut = true;
            robotMap.hatchExtend.set(DoubleSolenoid.Value.kReverse);
        }
    }

    /**
     * doesn't do anything
     */
    public void intake() 
    {

    }

    /**
     * Deploys the hatch from the manipulator.
     * @param rocketMode isn't used for Hatch manipulator
     */
    public void deploy(boolean rocketMode) //left trigger
    {
        if(robotMap.getTrigger()<=-0.75)
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
        }
    }

    /**
     * Pulls the Hatch manipulator slides in.
     */
    public void disengage() //slide in
    {
        if(robotMap.bumperL.get())
        {
            robotMap.hatchExtend.set(DoubleSolenoid.Value.kForward);
        }
    }

    /**
     * Doesn't do anything
     */
    public void intakeAuto()
    {

    }

}
