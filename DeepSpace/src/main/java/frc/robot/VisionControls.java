package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.Timer;

public class VisionControls // just a testing class, will be implemented in a different way
//limelight-one: 15 degrees, 43in from ground
{
    
    Timer doublePress = new Timer();
	SendableChooser cameraMode, lightMode;
	String camMode = "";


	
    public VisionControls()
    {
		doublePress.start();
		cameraMode = new SendableChooser();
		cameraMode.addDefault("driver's camera", "driving");
		cameraMode.addObject("vision processing", "processing");
    }

    public void execute()
    {
		if(!cameraMode.getSelected().equals(camMode))
		{
			camMode = cameraMode.getSelected().toString();
			if(camMode.equals("processing"))
			{
				NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("camMode").setNumber(0);
			}
			else
			{
				NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("camMode").setNumber(1);
			}
		}
    }
}