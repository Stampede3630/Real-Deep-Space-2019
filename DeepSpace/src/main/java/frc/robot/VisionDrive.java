package frc.robot;

import edu.wpi.first.wpilibj.Timer;

public class VisionDrive implements DriveMode{

    RobotMap robotMap;
    DriveTrain driveTrain;
    Choosers turnChooser;
    double xValue, yValue, zValue;
    Timer driveFw;

    public VisionDrive(RobotMap robotMap, DriveTrain driveTrain) //change limelight before entering VisionDrive!
    {
        this.robotMap = robotMap;
        this.driveTrain = driveTrain;

        driveTrain.strafePID.strafeController.enable();
        driveTrain.forwardPID.forwardController.enable();
        driveTrain.turnPID.zController.enable();
    }

    public boolean getAutoRotate() 
    {
        return true;
    }
    
    public void driveRobot()
    {
        zValue = driveTrain.turnPID.getTurnOutput();
        xValue = driveTrain.strafePID.getStrafeOutput();

        if (Constants.ballFollowerOn)
        {
            searchTarget();
        }
        else
        {
            driveAuto();
        }

        if(driveTrain.forwardPID.forwardController.isEnabled())
        {
            yValue = driveTrain.forwardPID.getForwardOutput();
        }
        else
        {
            yValue = robotMap.getLeftY();
        }
        
        switch(Constants.limelight)
        {
            case "limelight-two": robotMap.drive.driveCartesian(-xValue, yValue, zValue);

            case "limelight-one": 
                if(Constants.ballFollowerOn)
                {
                    robotMap.drive.driveCartesian(xValue, yValue, zValue);
                }
                else 
                {
                    robotMap.drive.driveCartesian(xValue, -yValue, zValue);
                }
            break;
        }
        
    }

    public void searchTarget()
    {;
        driveTrain.turnPID.zController.disable();
        if(robotMap.ballStop.getVoltage()>4)
        {
            driveTrain.forwardPID.forwardController.disable();
            driveTrain.strafePID.strafeController.disable();
            driveTrain.turnPID.zController.disable();
        }
        if(Constants.tv>0&&Constants.fullTargetTa - Constants.ta<=2)
        {
            driveTrain.forwardPID.forwardController.disable();
            driveTrain.strafePID.strafeController.disable();
            Robot.manipulator.manipulatorMode.intakeAuto();
        }
        if (Constants.tv>0)
        {
            driveTrain.forwardPID.forwardController.setSetpoint(0);
            driveTrain.strafePID.strafeController.setSetpoint(0);
            driveTrain.turnPID.zController.disable();
            driveTrain.strafePID.strafeController.enable();
            driveTrain.forwardPID.forwardController.enable();

        }
        else 
        {
            driveTrain.turnPID.zController.disable();
            driveTrain.forwardPID.forwardController.disable();
            driveTrain.strafePID.strafeController.disable();
            Robot.manipulator.manipulatorMode.intakeAuto();
        }
    }

    public void driveAuto()
    {
        if(Constants.tv==0)
        {
            Robot.driveTrain.strafePID.strafeController.disable();
            Constants.lostTarget = true;
        }
        else
        {
            Robot.driveTrain.strafePID.strafeController.setSetpoint(0);
            Robot.driveTrain.turnPID.zController.setSetpoint(Constants.robotAngle);
            Robot.driveTrain.turnPID.zController.enable();
            Robot.driveTrain.strafePID.strafeController.enable();
            Robot.driveTrain.forwardPID.forwardController.disable();
            Constants.lostTarget = false;
        }   
    }
}