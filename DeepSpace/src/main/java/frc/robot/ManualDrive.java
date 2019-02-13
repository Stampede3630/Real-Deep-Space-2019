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
        this.driveTrain = driveTrain;

//        NetworkTableInstance.getDefault().getTable("limelight-one").getEntry("camMode").setNumber(1);
//        NetworkTableInstance.getDefault().getTable("limelight-two").getEntry("camMode").setNumber(1);
//        driveTrain.turnPID.zController.enable();
    }

    public boolean getAutoRotate() {
        return autoRotateEnable;
    }

    public void driveRobot()
    {
         if (Robot.manipulatorChooser.getSelected().equals( "Ball"))
         {
            xSpeed = -1 * robotMap.getLeftX();
            ySpeed = -1 * robotMap.getLeftY();
         }
         else
         {
            xSpeed = robotMap.getLeftX();
            ySpeed = robotMap.getLeftY();
         }

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

        if (robotMap.leftStickB.get())
        {
            robotMap.drive.driveCartesian(xSpeed, ySpeed, zRotation);
        }

        else
        {
            robotMap.drive.driveCartesian(xSpeed * Constants.multiplierScaleDown, ySpeed * Constants.multiplierScaleDown, zRotation * Constants.multiplierScaleDown);
        }
    }

}