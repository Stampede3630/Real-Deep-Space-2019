package frc.robot;

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
        if (SmartDashboard.getData("Foreward Chooser").toString() == "Ball")
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

    public void angleSwitch()
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
        if(robotMap.buttonY.get()&&manipulatorModeTime.get()>=0.25)
        {
            ballManipulator = !ballManipulator;
            manipulatorModeTime.reset();
            manipulatorModeTime.start();
            if(ballManipulator)
            {
                manipulator.manipulatorMode = new Ball(manipulator);
            }
            else if(!ballManipulator)
            {
                manipulator.manipulatorMode = new Hatch(manipulator);
            }
        }
        diagnostics.ballManipulator = this.ballManipulator;
    }

    public void chooseAuto(String autoCase, String autoPiece) //we should use this thing for autonomous turning before separate auto parts run
    {
/*        switch(autoCase) 
        {
            case "LeftFarCS": driveTrain.setpoint = //SmartDashboard.putString("autoMode", "left far cs");
            
            case "LeftMidCS": SmartDashboard.putString("autoMode", "left mid cs");

            case "LeftNearCS": SmartDashboard.putString("autoMode", "left near cs");

            case "leftFarRS": SmartDashboard.putString("autoMode", "left far rs");

            case "LeftMidRS":SmartDashboard.putString("autoMode", "left mid rs");

            case "LeftNearRS": SmartDashboard.putString("autoMode", "left near rs");

            case "RightFarRS": SmartDashboard.putString("autoMode", "right far rs");

            case "RightMidRS": SmartDashboard.putString("autoMode", "right mid rs");

            case "RightNearRS": SmartDashboard.putString("autoMode", "right near rs");

            case "RightFarCS": SmartDashboard.putString("autoMode", "right far cs");

            case "RightMidCS": SmartDashboard.putString("autoMode", "right mid cs");

            case "RightNearCS": SmartDashboard.putString("autoMode", "right near cs");
        }
*/
        switch(autoPiece)
        {
            case "hatch": 
                switch(autoCase)  //0 -> leftmost, 1-> closest, 2-> rightmost
                {
                    case "LeftFarCS": driveTrain.turnSetpoint = 90;
                
                    case "LeftMidCS": driveTrain.turnSetpoint = 90;
    
                    case "LeftNearCS": driveTrain.turnSetpoint = 90;
    
                    case "leftFarRS": driveTrain.turnSetpoint = -151;
    
                    case "LeftNearRS": driveTrain.turnSetpoint = -29;
    
                    case "RightFarRS": driveTrain.turnSetpoint = 151;
    
                    case "RightNearRS": driveTrain.turnSetpoint = 29;
    
                    case "RightFarCS": driveTrain.turnSetpoint = -90;
    
                    case "RightMidCS": driveTrain.turnSetpoint = -90;
    
                    case "RightNearCS": driveTrain.turnSetpoint = -90;

                    case "LeftFaceCS": driveTrain.turnSetpoint = 0;

                    case "RightFaceCS": driveTrain.turnSetpoint = 0;

                    case "RightMidRS": System.out.println("no hatches here");

                    case "LeftMidRS": System.out.println("no hatches here");
                }

            case "ball":
                switch(autoCase) 
                {
                    case "LeftFarCS": driveTrain.turnSetpoint = -90;
                
                    case "LeftMidCS": driveTrain.turnSetpoint = -90;
    
                    case "LeftNearCS": driveTrain.turnSetpoint = -90;
    
                    case "LeftMidRS": driveTrain.turnSetpoint = 90;
    
                    case "RightMidRS": driveTrain.turnSetpoint = -90;
    
                    case "RightFarCS": driveTrain.turnSetpoint = 90;
    
                    case "RightMidCS": driveTrain.turnSetpoint = 90;
    
                    case "RightNearCS": driveTrain.turnSetpoint = 90;

                    case "LeftFaceCS": driveTrain.turnSetpoint = 180;

                    case "RightFaceCS": driveTrain.turnSetpoint = 180;

                    case "RightFarRS": System.out.println("no balls here");

                    case "RightNearRS": System.out.println("no balls here");

                    case "LeftFarRS": System.out.println("no balls here");

                    case "LeftNearRS": System.out.println("no balls here");
                }
            }
    }

}