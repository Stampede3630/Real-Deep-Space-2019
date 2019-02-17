package frc.robot;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/*import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.PID.Zpid;*/

public class PathChooser{

    /*RobotMap robotMap;
    Zpid turnPID;
    boolean toRocket;
    Timer buttonTime;
    boolean rocketShuttleChooser;
    boolean hatchBallChooser;
    boolean toggleOnRocket = false;
    boolean togglePressedY = false;
    boolean toggleOnHatch = false;
    boolean togglePressedX = false;*/
    public double angle;
    String right = "";
    String left = "";
    String center = "";
    String currentPipeline = "";
    Timer driveFw;

    public PathChooser() {
        driveFw = new Timer();
    }

    public void stringToPath(String mode) { //0 - closest(intake), 1-leftmost 2-rightmost 3-closest(deploy)
        switch (mode) {
            case "LeftLS":
                angle = Robot.choosers.reverseAngle(-180);
                NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("pipeline").setNumber(0);
                Constants.pipeline = 0;
//                NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("ledMode").setNumber(0);
                Robot.choosers.ballFollowerOn = false;
                break;
            case "LeftFarRS":
                angle = Robot.choosers.reverseAngle(-150);
                NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("pipeline").setNumber(2);
                Constants.pipeline = 2;
//                NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("ledMode").setNumber(0);
                Robot.choosers.ballFollowerOn = false;
                break;
            case "LeftMidRS":
                angle = Robot.choosers.reverseAngle(-90);
                Constants.pipeline = 3;
                NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("pipeline").setNumber(3);
//                NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("ledMode").setNumber(0);
                Robot.choosers.ballFollowerOn = false;
                break;
            case "LeftNearRS":
                angle = Robot.choosers.reverseAngle(-30);
                Constants.pipeline = 1;
                NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("pipeline").setNumber(1);
//                NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("ledMode").setNumber(0);
                currentPipeline = left;
                Robot.choosers.ballFollowerOn = false;
                break;
            case "LeftFarCS":
                angle = Robot.choosers.reverseAngle(90);
                Constants.pipeline = 1;
                NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("pipeline").setNumber(1);
//                NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("ledMode").setNumber(0);
                currentPipeline = left;
                Robot.choosers.ballFollowerOn = false;
                break;
            case "LeftMidCS":
                angle = Robot.choosers.reverseAngle(90);
                Constants.pipeline = 3;
                NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("pipeline").setNumber(3);
//                NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("ledMode").setNumber(0);
                currentPipeline = left;
                Robot.choosers.ballFollowerOn = false;
                break;
            case "LeftNearCS":
                angle = Robot.choosers.reverseAngle(90);
                Constants.pipeline = 2;
                NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("pipeline").setNumber(2);
//                NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("ledMode").setNumber(0);
                currentPipeline = left;
                Robot.choosers.ballFollowerOn = false;
                break;
            case "RightFarCS":
                angle = Robot.choosers.reverseAngle(-90);
                Constants.pipeline = 2;
                NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("pipeline").setNumber(2);
//                NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("ledMode").setNumber(0);
                currentPipeline = right;
                Robot.choosers.ballFollowerOn = false;
                break;
            case "RightMidCS":
                angle = Robot.choosers.reverseAngle(-90);
                Constants.pipeline = 3;
                NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("pipeline").setNumber(3);
//                NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("ledMode").setNumber(0);
                currentPipeline = right;
                Robot.choosers.ballFollowerOn = false;
                break;
            case "RightNearCS":
                angle = Robot.choosers.reverseAngle(-90);
                Constants.pipeline = 1;
                NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("pipeline").setNumber(1);
//                NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("ledMode").setNumber(0);
                currentPipeline = right;
                Robot.choosers.ballFollowerOn = false;
                break;
            case "LeftFaceCS":
                angle = Robot.choosers.reverseAngle(0);
                Constants.pipeline = 1;
                NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("pipeline").setNumber(1);
//                NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("ledMode").setNumber(0);
                currentPipeline = left;
                Robot.choosers.ballFollowerOn = false;
                break;
            case "RightFaceCS":
                angle = Robot.choosers.reverseAngle(0);
                Constants.pipeline = 2;
                NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("pipeline").setNumber(2);
//                NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("ledMode").setNumber(0);
                currentPipeline = right;
                Robot.choosers.ballFollowerOn = false;
                break;
            case "RightFarRS":
                angle = Robot.choosers.reverseAngle(150);
                Constants.pipeline = 1;
                NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("pipeline").setNumber(1);
//                NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("ledMode").setNumber(0);
                currentPipeline = right;
                Robot.choosers.ballFollowerOn = false;
                break;
            case "RightMidRS":
                angle = Robot.choosers.reverseAngle(90);
                Constants.pipeline = 3;
                NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("pipeline").setNumber(3);
//                NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("ledMode").setNumber(0);
                currentPipeline = right;
                Robot.choosers.ballFollowerOn = false;
                break;
            case "RightNearRS":
                angle = Robot.choosers.reverseAngle(30);
                Constants.pipeline = 2;
                NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("pipeline").setNumber(2);
//                NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("ledMode").setNumber(0);
                currentPipeline = right;
                Robot.choosers.ballFollowerOn = false;
                break;
            case "RightLS":
                angle = Robot.choosers.reverseAngle(-180);
                Constants.pipeline = 0;
                NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("pipeline").setNumber(0);
//                NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("ledMode").setNumber(0);
                Robot.choosers.ballFollowerOn = false;
                break;
            case "GrabBall":
                Constants.pipeline = 0;
                NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("pipeline").setNumber(0);
//                NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("ledMode").setNumber(0);
                Robot.choosers.ballFollowerOn = true;
                break;
            default:
                angle = Robot.choosers.reverseAngle(0);
                break;      
        }
        // set limelight pipeline
    }

}