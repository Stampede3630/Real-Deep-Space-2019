package frc.robot;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;
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
        }
        if(robotMap.buttonB.get())
        {
            driveTrain.driveMode = new VisionDrive(robotMap, driveTrain);
        }
        if(robotMap.buttonX.get())
        {
            driveTrain.driveMode =  new LineDrive(robotMap);
        }
    }

    public void angleSwitch() //to be replaced
    {
        if(robotMap.backB.get()&&(buttonTime.get()>=0.25))
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
                Constants.limelight = "limelight-one";
            }
            else 
            {
                manipulator.manipulatorMode = new Hatch(manipulator);
                NetworkTableInstance.getDefault().getTable("limelight-one").getEntry("camMode").setNumber(1);
                Constants.limelight = "limelight-two";
            }

        }
        diagnostics.ballManipulator = this.ballManipulator;
    }

}