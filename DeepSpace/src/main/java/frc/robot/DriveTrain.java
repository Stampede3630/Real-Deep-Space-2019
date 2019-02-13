/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.PID.Ypid;
import frc.robot.PID.Zpid;
import frc.robot.PID.Xpid;

public class DriveTrain {

    RobotMap robotMap;
    DriveMode driveMode;
    public double turnSetpoint;
    public Zpid turnPID;
    public Xpid xpid;
    public Ypid ypid;
    public boolean intake;

    public DriveTrain()
    {
        robotMap = RobotMap.getRobotMap();
        turnPID = new Zpid();
        turnPID.zpidSetup();

        xpid = new Xpid();
        xpid.xpidSetup();

        ypid = new Ypid();
        ypid.ypidSetup();
    }

    public void drive()
    {
        driveMode.driveRobot();
    }


    

}
