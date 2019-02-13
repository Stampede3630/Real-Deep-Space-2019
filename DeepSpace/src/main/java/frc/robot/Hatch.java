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
    Timer coneTime;

    public Hatch(Manipulator manipulator) 
    {
        robotMap = RobotMap.getRobotMap();
        this.manipulator = manipulator;
        manipulatorOut = false;

        coneTime = new Timer();
    }

    public void engage () 
    {
        //extend hatch deploy thing
        if(robotMap.bumperR.get())
        {
            manipulatorOut = true;
            robotMap.hatchExtend.set(DoubleSolenoid.Value.kReverse);
        }
    }

    public void intake () 
    {
        //nothing, for now - just drive to the loading station in LineDrive
    }

    public void deploy (boolean rocketMode) 
    {
        if(robotMap.getTrigger()>=0.75)
        {
            coneTime.reset();
            coneTime.start();
            robotMap.hatchDeploy.set(DoubleSolenoid.Value.kForward);
            robotMap.hatchExtend.set(DoubleSolenoid.Value.kForward);
        }
        if(coneTime.get()>=1)
        {
            coneTime.stop();
            robotMap.hatchDeploy.set(DoubleSolenoid.Value.kReverse);
        }
    }

    public void disengage () 
    {
        if(robotMap.bumperL.get())
        {
            robotMap.hatchExtend.set(DoubleSolenoid.Value.kForward);
        }
    }

}
