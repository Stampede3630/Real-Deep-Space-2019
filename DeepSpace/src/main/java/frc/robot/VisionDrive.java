package frc.robot;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VisionDrive implements DriveMode{

    RobotMap robotMap;
    DriveTrain driveTrain;
    Choosers turnChooser;
    double xValue, yValue, zValue;

    public VisionDrive(RobotMap robotMap, DriveTrain driveTrain) //change limelight before entering VisionDrive!
    {
        this.robotMap = robotMap;
        this.driveTrain = driveTrain;

        NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("camMode").setNumber(0);
    }

    public boolean getAutoRotate() {
        return true;
    }
    
    public void driveRobot()
    {

        if (!Robot.choosers.getBallTarget()){
            driveTrain.xpid.xController.setOutputRange(-1, 1);
            driveTrain.ypid.yController.setOutputRange(-1, 1);
            driveTrain.xpid.xController.enable();
            driveTrain.ypid.yController.enable();
            driveTrain.turnPID.zController.enable();

            driveTrain.xpid.xController.setSetpoint(0);
            driveTrain.ypid.yController.setSetpoint(40);
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
            case "limelight-two": robotMap.drive.driveCartesian(-xValue, yValue, zValue);

            case "limelight-one": robotMap.drive.driveCartesian(xValue, -yValue, zValue);
        }
        
    }

    public void searchTarget()
    {
        double tv = NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("tv").getDouble(0);
        if (tv>0)
        {
            driveTrain.ypid.yController.setOutputRange(-0.6, 0.6); 
            driveTrain.xpid.xController.setOutputRange(-0.6, 0.6);
            driveTrain.ypid.yController.setSetpoint(0);
            driveTrain.xpid.xController.setSetpoint(0);
            driveTrain.turnPID.zController.disable();
            driveTrain.xpid.xController.enable();
            driveTrain.ypid.yController.enable();
        }
        else 
        {
            driveTrain.turnPID.zController.disable();
            zValue = 0.6;
            xValue = 0;
            yValue = 0;
        }
    }
}