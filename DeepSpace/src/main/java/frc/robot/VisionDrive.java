package frc.robot;

import edu.wpi.first.networktables.NetworkTableInstance;

public class VisionDrive implements DriveMode{

    RobotMap robotMap;
    DriveTrain driveTrain;
    Choosers turnChooser;
    TargetFinder targetFinder;
    double xValue, yValue, zValue;

    public VisionDrive(RobotMap robotMap, DriveTrain driveTrain) //change limelight before entering VisionDrive!
    {
        this.robotMap = robotMap;
        this.driveTrain = driveTrain;

//        NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("camMode").setNumber(0);
    }

    public boolean getAutoRotate() {
        return true;
    }

    public void driveRobot()
    {
        if (!turnChooser.getBallTarget()){
            driveTrain.xpid.xController.enable();
            driveTrain.ypid.yController.enable();
            driveTrain.turnPID.zController.enable();

            driveTrain.xpid.xController.setSetpoint(0);
            driveTrain.ypid.yController.setSetpoint(0);
            driveTrain.turnPID.zController.setSetpoint(driveTrain.turnSetpoint);
        }
        else
        {
            searchTarget();
        }

        xValue = driveTrain.xpid.getXOutput();
        yValue = driveTrain.ypid.getYOutput();

        if (driveTrain.turnPID.zController.isEnabled())
        {
            zValue = driveTrain.turnPID.getZOutput();
        }
        else
        {
            zValue = 0;
        }
        

        switch(Constants.limelight)
        {
            case "limelight-one": robotMap.drive.driveCartesian(-xValue, yValue, zValue);

            case "limelight-two": robotMap.drive.driveCartesian(xValue, -yValue, zValue); //ball follower
        }
        
    }

    public void searchTarget()
    {
        double tv = NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("tv").getDouble(0);
        if (tv == 1)
        {
            driveTrain.ypid.yController.setSetpoint(0);
            driveTrain.xpid.xController.setSetpoint(0);
            driveTrain.turnPID.zController.disable();
        }
        else 
        {
            driveTrain.turnPID.zController.enable();
            driveTrain.turnPID.zController.setSetpoint(robotMap.ahrs.getAngle() + 3);
        }
    }

}