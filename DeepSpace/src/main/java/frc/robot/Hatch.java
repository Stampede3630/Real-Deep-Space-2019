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
    //this code is for deploying 
    
    RobotMap robotMap;
    boolean manipulatorOut;
    Manipulator manipulator;
    Timer circleTimer;

    //generic construction of objects
    public Hatch(Manipulator manipulator) {
        robotMap = RobotMap.getRobotMap();
        this.manipulator = manipulator;
        manipulatorOut = false;

        circleTimer = new Timer();
    }

    //slide out
    public void engage() {
        if(robotMap.bumperR.get()) {
            manipulatorOut = true;
            robotMap.hatchExtend.set(DoubleSolenoid.Value.kReverse);
        }
    }

    //I don't know why we still have this here
    public void intake() {

    }

    //left trigger
    public void deploy(boolean rocketMode) {
        if(robotMap.getTrigger()<=-0.75) {
            circleTimer.start();
            robotMap.hatchDeploy.set(DoubleSolenoid.Value.kForward);
            robotMap.hatchExtend.set(DoubleSolenoid.Value.kForward);
        }
        if(circleTimer.get()>=1) {
            circleTimer.stop();
            circleTimer.reset();
            robotMap.hatchDeploy.set(DoubleSolenoid.Value.kReverse);
        }
    }

    //slide in
    public void disengage() {
        if(robotMap.bumperL.get())
        {
            robotMap.hatchExtend.set(DoubleSolenoid.Value.kForward);
        }
    }

    //I don't know why we still have this here
    public void intakeAuto() {

    }

}
