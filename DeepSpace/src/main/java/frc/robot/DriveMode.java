/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
 

public interface DriveMode {

    /**
     * Driving method for the robot. The driving is executed here
     */
    public void driveRobot();

    /**
     * Gives feedback if the autonomous rotation should be enabled
     * @return is the robot turning autonomously
     */
    public boolean getAutoRotate();

}
