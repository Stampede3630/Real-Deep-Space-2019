package frc.robot;

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
    double angle;
    String right = "";
    String left = "";
    String center = "";
    String currentPipeline = "";

    public PathChooser() {
        
    }

    public void stringToPath(String mode) {
        switch (mode) {
            case "LeftFarRS":
                angle = 45;
                currentPipeline = left;
                break;
            case "LeftMidRS":
                angle = -90;
                currentPipeline = left;
                break;
            case "LeftNearRS":
                angle = 135;
                currentPipeline = left;
                break;
            case "LeftFarCS":
                angle = 90;
                currentPipeline = left;
                break;
            case "LeftMidCS":
                angle = 90;
                currentPipeline = left;
                break;
            case "LeftNearCS":
                angle = 90;
                currentPipeline = left;
                break;
            case "RightFarCS":
                angle = -90;
                currentPipeline = right;
                break;
            case "RightMidCS":
                angle = -90;
                currentPipeline = right;
                break;
            case "RightNearCS":
                angle = -90;
                currentPipeline = right;
                break;
            case "LeftFaceCS":
                angle = 0;
                currentPipeline = left;
                break;
            case "RightFaceCS":
                angle = 0;
                currentPipeline = right;
                break;
            case "RightFarRS":
                angle = -45;
                currentPipeline = right;
                break;
            case "RightMidRS":
                angle = -90;
                currentPipeline = right;
                break;
            case "RightNearRS":
                angle = -135;
                currentPipeline = right;
                break;
            case "getBall":
                break;
            case "getHatch":
                break;
            default:
                angle = 0;
                break;      
        }
        // set limelight pipeline
    }
}