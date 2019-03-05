/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.PID.TurnPid;
import frc.robot.PID.StrafePid;
import frc.robot.PID.ForwardPid;

public class DriveTrain { //you actually put the bracket in the right place this time! Yay you!

    //this code is for all of our driving code (driving, strafing, ball following (?))

    RobotMap robotMap;
    DriveMode driveMode;
    public double turnSetpoint;
    public TurnPid turnPID;
    public StrafePid strafePID;
    public ForwardPid forwardPID;
    public boolean intake;


    //construction of our objects
    public DriveTrain() {
        robotMap = RobotMap.getRobotMap();
        turnPID = new TurnPid();
        turnPID.turnPidSetup();

        strafePID = new StrafePid();
        strafePID.strafePidSetup();

        forwardPID = new ForwardPid();
        forwardPID.forwardPidSetup();
    }

    //generic drive code
    public void drive() {
        driveMode.driveRobot();
    }
}