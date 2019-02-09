/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class LineDrive implements DriveMode {

    RobotMap robotMap;
    double tEncoderLValue, tEncoderRValue, tEncoderMValue;

    public LineDrive(RobotMap robotMap)
    {
        this.robotMap = robotMap;
//        NetworkTableInstance.getDefault().getTable("limelight-one").getEntry("camMode").setNumber(1);
//        NetworkTableInstance.getDefault().getTable("limelight-two").getEntry("camMode").setNumber(1);
    }

    public void driveRobot()
    {
        tEncoderLValue = robotMap.encoder0.getVoltage();
        SmartDashboard.putNumber("Encoder 0", tEncoderLValue);

        tEncoderMValue = robotMap.encoder1.getVoltage();
        SmartDashboard.putNumber("Encoder 1", tEncoderMValue);
    
        tEncoderRValue = robotMap.encoder2.getVoltage();
        SmartDashboard.putNumber("Encoder 2", tEncoderRValue);
       
        if (tEncoderMValue > Constants.midVoltage) {
           robotMap.drive.driveCartesian(0, Constants.driveForwardSpeed, 0);
            System.out.println("I am driving forward.");
        }
    
       else if (tEncoderLValue > Constants.midVoltage) {
            robotMap.drive.driveCartesian(Constants.strafeLeftSpeed, Constants.driveForwardSpeed, Constants.rotateLeftSpeed);
            System.out.println("I am driving left."); //are these prints stil necessary here?
        }
    
     
       else if (tEncoderRValue > Constants.midVoltage) {
            robotMap.drive.driveCartesian(Constants.strafeLeftSpeed, Constants.driveForwardSpeed, Constants.rotateRightSpeed);
            System.out.println("I am driving right.");
        }
    
        else{
            robotMap.drive.driveCartesian(0, 0, 0);
            System.out.println("I am stopping.");
        }
     }
}
