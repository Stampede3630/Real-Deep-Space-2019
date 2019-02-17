package frc.robot;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Choosers
{

    RobotMap robotMap;
    boolean toRocket, ballManipulator;
    Timer buttonTime, ballTime, manipulatorModeTime;
    DriveTrain driveTrain;
    Manipulator manipulator;
    Diagnostics diagnostics;
    boolean chooserEnable = true;
    String currentManipulator = "";
    String currentAction = "";
    boolean ballFollowerOn = false;
    boolean ball = false;
    SendableChooser intakeDeploy;

    public Choosers(DriveTrain driveTrain, Manipulator manipulator, Diagnostics diagnostics)
    {
        robotMap = RobotMap.getRobotMap();

        this.driveTrain = driveTrain;
        driveTrain.driveMode = new ManualDrive(robotMap, driveTrain);
        this.manipulator = manipulator;
        manipulator.manipulatorMode = new Ball(manipulator);
        this.diagnostics = diagnostics;

        toRocket = false;
        ballManipulator = true;

        buttonTime = new Timer();
        buttonTime.start();
        ballTime = new Timer();
        ballTime.start();
        manipulatorModeTime = new Timer();
        manipulatorModeTime.start();

        intakeDeploy = new SendableChooser();
        intakeDeploy.addDefault("deploy", "deploy");
        intakeDeploy.addOption("intake", "intake");
        intakeDeploy.addOption("driving", "driving");
        SmartDashboard.putData("intake/deploy", intakeDeploy);
    }

    public void chooserAngle(double angle) {
        if (driveTrain.driveMode.getAutoRotate()) {
            if (flipOrientation()){
                angle = angle + 180;
            }
            driveTrain.turnPID.zController.setSetpoint(angle);
            driveTrain.turnSetpoint = angle;
            driveTrain.turnPID.zController.enable();
        }

        else {
            driveTrain.turnPID.zController.disable();
        }
    }

    public double reverseAngle(double angle) {
        double outputAngle = angle;
        if (flipOrientation()){
            outputAngle = angle + 180;
        }
        return outputAngle;
    }


    public void chooserState(boolean value) {
        chooserEnable = value;
    }

    public boolean flipOrientation()
    {
        if (Robot.manipulatorChooser.getSelected().equals("Ball"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void setDriveMode()
    {
        if(robotMap.buttonA.get())
        {
            driveTrain.driveMode = new ManualDrive(robotMap, driveTrain);
            manipulator.autonomous = false;
        }
        if(robotMap.buttonB.get())
        {
            driveTrain.driveMode = new VisionDrive(robotMap, driveTrain);
            manipulator.autonomous = true;
        }
    }

    public void angleSwitch() //implement this later in chooserAngle
    {
        if(robotMap.buttonX.get()&&(buttonTime.get()>=0.25))
        {
            buttonTime.reset();
            buttonTime.start();
            toRocket=!toRocket;
        }
        manipulator.toRocket = this.toRocket;
        diagnostics.toRocket = this.toRocket;
    }

    public void automatedTurnToAngle (){
        angleSwitch();
        if(!toRocket){
            if((robotMap.controller.getPOV()) == 0) { 
                driveTrain.turnPID.zController.setSetpoint(0);
                driveTrain.turnPID.zController.enable();
                driveTrain.turnSetpoint = 0;
            }
            else if((robotMap.controller.getPOV()) == 90) { 
                driveTrain.turnPID.zController.setSetpoint(90);
                driveTrain.turnPID.zController.enable();
                driveTrain.turnSetpoint = 90;
            }
            else if ((robotMap.controller.getPOV()) == 180) { 
                driveTrain.turnPID.zController.setSetpoint(180);
                driveTrain.turnPID.zController.enable();
                driveTrain.turnSetpoint = 180;
            }
            else if((robotMap.controller.getPOV()) == 270) { 
                driveTrain.turnPID.zController.setSetpoint(-90);
                driveTrain.turnPID.zController.enable();
                driveTrain.turnSetpoint = -90;
            }
        }
        else if(toRocket){
            if(robotMap.controller.getPOV() == 45) { 
                driveTrain.turnPID.zController.setSetpoint(29);
                driveTrain.turnPID.zController.enable();
                driveTrain.turnSetpoint = 29;
            }
            else if(robotMap.controller.getPOV() == 315) { 
                driveTrain.turnPID.zController.setSetpoint(-29);
                driveTrain.turnPID.zController.enable();
                driveTrain.turnSetpoint = -29;
            }
            else if(robotMap.controller.getPOV() == 135){
                driveTrain.turnPID.zController.setSetpoint(151);
                driveTrain.turnPID.zController.enable();
                driveTrain.turnSetpoint = 151;
            }
            else if(robotMap.controller.getPOV() == 225) {
                driveTrain.turnPID.zController.setSetpoint(-151);
                driveTrain.turnPID.zController.enable();
                driveTrain.turnSetpoint = -151;
            }
        }
    }

    public void setManipulatorMode()
    {
        if(!currentManipulator.equals(Robot.manipulatorChooser.getSelected()))
        {
            currentManipulator = Robot.manipulatorChooser.getSelected().toString();
            
            if(currentManipulator.equals("Ball"))
            {
                manipulator.manipulatorMode = new Ball(manipulator);
                NetworkTableInstance.getDefault().getTable("limelight-two").getEntry("camMode").setNumber(1);
                NetworkTableInstance.getDefault().getTable("limelight-two").getEntry("ledMode").setNumber(1);
                Constants.limelight = "limelight-one";
                ball = true;
            }
            else 
            {
                manipulator.manipulatorMode = new Hatch(manipulator);
                NetworkTableInstance.getDefault().getTable("limelight-one").getEntry("camMode").setNumber(1);
                NetworkTableInstance.getDefault().getTable("limelight-one").getEntry("ledMode").setNumber(1);
                Constants.limelight = "limelight-two";
                ball = false;
            }

        }
        diagnostics.ballManipulator = this.ballManipulator;
    }

    public boolean getBallTarget()
    {
        return ballFollowerOn;
    }

    public void setAction()
    {
        if(!intakeDeploy.getSelected().equals(currentAction))
        {
            currentAction = intakeDeploy.getSelected().toString();
            if(currentAction.equals("intake"))
            {
                NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("pipeline").setNumber(0);
                NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("ledMode").setNumber(0);
                NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("camMode").setNumber(0);
                Constants.pipeline = 0;
                if(ball)
                {
                    ballFollowerOn = true;
                }
                else
                {
                    ballFollowerOn = false;
                }
            }
            else if(currentAction.equals("deploy"))
            {
                ballFollowerOn = false;
                NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("pipeline").setNumber(1);
                NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("ledMode").setNumber(0);
                NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("camMode").setNumber(0);
                Constants.pipeline = 1;
            }
            else
            {
                ballFollowerOn = false;
                NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("camMode").setNumber(1);
                NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("ledMode").setNumber(1);
            }
        }
    }

}