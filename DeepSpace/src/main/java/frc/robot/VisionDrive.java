package frc.robot;

import edu.wpi.first.networktables.NetworkTableInstance;

public class VisionDrive implements DriveMode{

    RobotMap robotMap;
    DriveTrain driveTrain;
    Choosers turnChooser;
    double xValue, yValue, zValue;

    public VisionDrive(RobotMap robotMap, DriveTrain driveTrain) //change limelight before entering VisionDrive!
    {
        this.robotMap = robotMap;
        this.driveTrain = driveTrain;
        Constants.yPIDsource = "ty";

//        NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("camMode").setNumber(0);
    }

    public boolean getAutoRotate() {
        return true;
    }
    
    public void driveRobot()
    {
        driveTrain.xpid.xController.enable();
        driveTrain.ypid.yController.enable();
        driveTrain.turnPID.zController.enable();

        driveTrain.xpid.xController.setSetpoint(0);
        driveTrain.ypid.yController.setSetpoint(0);
        driveTrain.turnPID.zController.setSetpoint(driveTrain.turnSetpoint);

        xValue = driveTrain.xpid.getXOutput();
        yValue = driveTrain.ypid.getYOutput();
        zValue = driveTrain.turnPID.getZOutput();

        switch(Constants.limelight)
        {
            case "limelight-one": robotMap.drive.driveCartesian(-xValue, yValue, zValue);

            case "limelight-two": robotMap.drive.driveCartesian(0, -0.5*yValue, -0.5*xValue); //no sideways motion
        }
        
    }

}