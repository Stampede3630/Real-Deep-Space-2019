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
    

    //generic construction of our objects
    public Ball (Manipulator manipulator) {
        robotMap = RobotMap.getRobotMap();
        this.manipulator = manipulator;
        sensorIntake = true;
        manualIntake = false;
        intakeTimer = new Timer();
    }

    //why is this still here?
    public void engage() {

    }

    //teleop ball intake
    public void intake() { //right trigger 
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
        if(robotMap.getTrigger()>0.2&&robotMap.ballStopTop.getVoltage()<4.0 && Constants.ballBottom) {
                
            robotMap.talonBallIntake.set(-1);
            robotMap.talonBallShooter.set(0);
        }

//        else if (robotMap.getTrigger()>0.2&&robotMap.ballStopBottom.getVoltage()<4.0 && Constants.ballBottom)
        else if(robotMap.getTrigger()>0.2&&robotMap.ballStopTop.getVoltage()<4.0 && Constants.ballTop) {
            robotMap.talonBallIntake.set(0);
            robotMap.talonBallShooter.set(1);
        }
     /*   else if(robotMap.getTrigger()>0.2&&robotMap.ballStopBottom.getVoltage()<4.0)
        {
            robotMap.talonBallIntake.set(1);
            robotMap.talonBallShooter.set(-1);
        }*/
        else {
            robotMap.talonBallIntake.set(0);
            robotMap.talonBallShooter.set(0);
        } 
    }


    //teleop ball shot
    public void deploy (boolean rocketMode) { //left trigger 
        if(robotMap.getTrigger()<-0.2) {
            if(rocketMode) {
                robotMap.talonBallShooter.set(-robotMap.getTrigger());
                robotMap.talonBallIntake.set(robotMap.getTrigger());
            }
            else {
                robotMap.talonBallShooter.set(0.5*robotMap.getTrigger());
                robotMap.talonBallIntake.set(-0.25);
            }
        }   
        else {
            robotMap.talonBallIntake.set(0);
            robotMap.talonBallShooter.set(0);
        }
    }


    //why is this still here
    public void disengage() {

    }

    //autonomous ball intake
    public void intakeAuto() {
        if(robotMap.ballStopTop.getVoltage()>=4) {
            robotMap.talonBallIntake.set(0);
            System.out.println("stop intaking");
        }
        else if(Constants.ta>=10||Constants.tv==0) {
            robotMap.talonBallIntake.set(-1);
            System.out.println("intaking");
        }
    }
}
