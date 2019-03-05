package frc.robot;

import edu.wpi.first.wpilibj.Timer;

public class VisionDrive implements DriveMode{

    RobotMap robotMap;
    DriveTrain driveTrain;
    Choosers turnChooser;
    double xValue, yValue, zValue, tempTX;
    Timer driveFw;

    public VisionDrive(RobotMap robotMap, DriveTrain driveTrain) //change limelight before entering VisionDrive!
    {
        this.robotMap = robotMap;
        this.driveTrain = driveTrain;

        driveTrain.strafePID.strafeController.enable();
        driveTrain.forwardPID.forwardController.enable();
        driveTrain.turnPID.turnController.enable();
    }

    public boolean getAutoRotate() 
    {
        return true;
    }
    
    public void driveRobot()
    {

        if (Constants.ballFollowerOn)
        {
            searchTarget();
            Constants.ballFollowerExecuting = true;
        }
        else
        {
            driveAuto();
            Constants.ballFollowerExecuting = false;
        }

        zValue = driveTrain.turnPID.getTurnOutput();
        xValue = driveTrain.strafePID.getStrafeOutput();

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
            case "limelight-two": robotMap.drive.driveCartesian(-xValue, 0.6*yValue, zValue);
            break;

            case "limelight-one": 
                if(Constants.ballFollowerOn)
                {
                    robotMap.drive.driveCartesian(xValue, yValue, zValue);
                }
                else 
                {
                    robotMap.drive.driveCartesian(xValue, -0.6*yValue, zValue);
                }
            break;
        }
        
    }

    public void searchTarget()
    {
        driveTrain.turnPID.turnController.disable();
        if(robotMap.ballStopTop.getVoltage()>4)
        {
            driveTrain.forwardPID.forwardController.disable();
            driveTrain.strafePID.strafeController.disable();
            driveTrain.turnPID.turnController.disable();
            Robot.manipulator.manipulatorMode.intakeAuto();
        }
        else if(Constants.tv>0&&Constants.ta>=90)
        {
            driveTrain.forwardPID.forwardController.disable();
            driveTrain.strafePID.strafeController.disable();
            Robot.manipulator.manipulatorMode.intakeAuto();
            System.out.println("intaking");
        }
        else if (Constants.tv>0)
        {
            tempTX = Constants.tx;
            driveTrain.forwardPID.forwardController.setSetpoint(0);
            driveTrain.strafePID.strafeController.setSetpoint(0);
            driveTrain.turnPID.turnController.enable();
            driveTrain.strafePID.strafeController.enable();
            driveTrain.forwardPID.forwardController.enable();
        }
        else 
        {
/*            if(tempTX<0) {
                driveTrain.turnPID.turnController.setSetpoint(robotMap.ahrs.getAngle()-10);
            }
            else
            {
                driveTrain.turnPID.turnController.setSetpoint(robotMap.ahrs.getAngle()+10);
            }
            */
            driveTrain.turnPID.turnController.disable();
            driveTrain.forwardPID.forwardController.disable();
            driveTrain.strafePID.strafeController.disable();
            robotMap.talonBallIntake.set(-1);
            Robot.manipulator.manipulatorMode.intakeAuto();
            System.out.println("no target");
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
            Robot.driveTrain.turnPID.turnController.setSetpoint(Constants.robotAngle);
            Robot.driveTrain.turnPID.turnController.enable();
            Robot.driveTrain.strafePID.strafeController.enable();
            Robot.driveTrain.forwardPID.forwardController.disable();
            Constants.lostTarget = false;
        }   
    }
}