package frc.robot;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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

        driveTrain.xpid.xController.enable();
        driveTrain.ypid.yController.enable();
        driveTrain.turnPID.zController.enable();

        NetworkTableInstance.getDefault().getTable("limelight-one").getEntry("camMode").setNumber(0);
        NetworkTableInstance.getDefault().getTable("limelight-two").getEntry("camMode").setNumber(0);
        NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("ledMode").setNumber(0);

        Constants.autoDriveFw = false;
        driveFw = new Timer();
        driveFw.reset();

        Robot.manipulator.startDeploy = true;
        Robot.manipulator.isLaunched = false;
    }

    public boolean getAutoRotate() {
        return true;
    }
    
    public void driveRobot()
    {
        if (!Robot.choosers.getBallTarget()){
            driveTrain.xpid.xController.setOutputRange(-0.6, 0.6);
            driveTrain.ypid.yController.setOutputRange(-0.6, 0.6);

//            driveTrain.ypid.yController.disable();
//            driveTrain.turnPID.zController.disable();

//            driveTrain.ypid.yController.setSetpoint(0);
//            driveTrain.xpid.xController.setSetpoint(0);
//            driveTrain.turnPID.zController.setSetpoint(Robot.pathChooser.angle);

            driveAuto();
        }
        else
        {
            searchTarget();
        }

        if(driveTrain.xpid.xController.isEnabled())
        {
            xValue = driveTrain.xpid.getXOutput();
        }
        else
        {
            xValue = 0;
        }

        if(driveTrain.ypid.yController.isEnabled())
        {
            yValue = driveTrain.ypid.getYOutput();
        }
        else
        {
//            yValue = 0;
        }
        
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
            case "limelight-two": robotMap.drive.driveCartesian(-xValue, -yValue, zValue);
//            System.out.println("driving to hatch");
            break;

            case "limelight-one": 
            if(Robot.choosers.getBallTarget())
            {
                robotMap.drive.driveCartesian(xValue, yValue, zValue);
            }
            else 
            {
                robotMap.drive.driveCartesian(xValue, -yValue, zValue);
            }
        }
        
    }

    public void searchTarget()
    {
        double tv = NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("tv").getDouble(0);
        driveTrain.turnPID.zController.disable();
        if(robotMap.ballStop.getVoltage()>4)
        {
            driveTrain.ypid.yController.disable();
            driveTrain.xpid.xController.disable();
            driveTrain.turnPID.zController.disable();
//            Robot.manipulator.manipulatorMode.intakeAuto();
        }
        if(tv>0&&Constants.fullTargetTa - NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("ta").getDouble(0)<=2)
        {
            driveTrain.ypid.yController.disable();
            driveTrain.xpid.xController.disable();
            Robot.manipulator.manipulatorMode.intakeAuto();
        }
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
            driveTrain.ypid.yController.disable();
            driveTrain.xpid.xController.disable();
            Robot.manipulator.manipulatorMode.intakeAuto();
            zValue = 0;
            xValue = 0;
            yValue = 0;
        }
    }

    public void driveAuto()
    {
        Robot.driveTrain.xpid.xController.setSetpoint(0);
        Robot.driveTrain.ypid.yController.setSetpoint(0);
        Robot.driveTrain.turnPID.zController.setSetpoint(Robot.pathChooser.angle);

        System.out.println(Constants.limelight + "  " + Robot.manipulator.manipulatorMode.getClass());
        
        if(Robot.driveTrain.xpid.xController.onTarget()&&Robot.driveTrain.ypid.yController.onTarget())
//        if(true)
        {
            Robot.driveTrain.xpid.xController.disable();
            Robot.driveTrain.ypid.yController.disable();
            Robot.driveTrain.turnPID.zController.disable();

            if(!Constants.autoDriveFw)
            {
                driveFw.start();
                Constants.autoDriveFw = true;
                System.out.println("starting a timer");
            }

            if(Robot.manipulator.toRocket&&Constants.limelight.equals("limelight-one"))
            {
                if(driveFw.get()>=0.72)
                {
                    yValue = 0;
                    Robot.manipulator.manipulatorMode.deployAuto(true);
                }
                else
                {
                    yValue = -0.6;
                }
            }
            else if((!Robot.manipulator.toRocket)&&Constants.limelight.equals("limelight-one")) //-13in
            {
                if(driveFw.get()>0)
                {
                    yValue = 0;
                    Robot.manipulator.manipulatorMode.deployAuto(false);
                }
                else
                {
                    yValue = 0.6;
                }
            }
            else if(Constants.limelight.equals("limelight-two")&&Constants.pipeline==0) //intake
            {
                if(driveFw.get()>0.55) //64
                {
                    yValue = 0;
                    Robot.manipulator.manipulatorMode.intakeAuto();
                }
                else
                {
                    yValue = -0.6;
                }
            }
            else if(Constants.limelight.equals("limelight-two")&&Constants.pipeline==1)//deploy
            {
                if(driveFw.get()>0.57) //66
                {
                    yValue = 0;
                    Robot.manipulator.manipulatorMode.deployAuto(false);
                }
                else
                {
                    yValue = -0.6;
                }
            }
        }

    }
}