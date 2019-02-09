/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ManualDrive implements DriveMode {

    RobotMap robotMap;
    DriveTrain driveTrain;
    double xSpeed;
    double ySpeed;
    double zRotation;
    boolean autoRotateEnable;
    
    public ManualDrive(RobotMap robotMap, DriveTrain driveTrain)
    {
        this.robotMap = robotMap;
        this.driveTrain=driveTrain;

//        NetworkTableInstance.getDefault().getTable("limelight-one").getEntry("camMode").setNumber(1);
//        NetworkTableInstance.getDefault().getTable("limelight-two").getEntry("camMode").setNumber(1);
//        driveTrain.turnPID.zController.enable();
    }

    public boolean getAutoRotate() {
        return autoRotateEnable;
    }

    public void driveRobot()
    {
        xSpeed = robotMap.getLeftX();
        ySpeed = robotMap.getLeftY();

        if (robotMap.buttonA.get()) {
            autoRotateEnable = true;
        }

        if(Math.abs(robotMap.getRightX())>0.2)
        {
            driveTrain.turnPID.zController.disable();
            zRotation = robotMap.getRightX();
            autoRotateEnable = false;
        }
        else
        {
            driveTrain.turnPID.zController.setSetpoint(driveTrain.turnSetpoint);
            zRotation = driveTrain.turnPID.getZOutput();
        }
        SmartDashboard.putNumber("ahrs", robotMap.ahrs.getYaw());
        SmartDashboard.putNumber("ManualDrive zpid setpoint", driveTrain.turnPID.zController.getSetpoint());
        robotMap.drive.driveCartesian(xSpeed, ySpeed, zRotation);
    }

}
