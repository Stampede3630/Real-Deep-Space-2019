package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Timer;

public class VisionControls // just a testing class, will be implemented in a different way
//limelight-one: 15 degrees, 43in from ground
{
    
    Timer doublePress = new Timer();
	String camMode = "";


	
    public VisionControls()
    {
		doublePress.start();
    }

}