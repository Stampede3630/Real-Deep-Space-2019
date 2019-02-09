package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;

public class Vision // just a testing class, will be implemented in a different way
//limelight-one: 15 degrees, 43in from ground
{
    
    Timer doublePress = new Timer();
    double camMode = 0;
    double lightMode = 0;

    public Vision()
    {
        doublePress.start();
    }

    public void execute()
    {
        if(getPressed(buttonA))
        {
            if(camMode==0)
            {
                camMode = 1;
            }
            else
            {
                camMode = 0;
            }
            NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("camMode").setNumber(camMode);
        }

        if(getPressed(buttonB))
        {
            if(lightMode==0)
            {
                lightMode = 1;
            }
            else if(lightMode==1)
            {
                lightMode = 2;
            }
            else if(lightMode==2)
            {
                lightMode = 3;
            }
            else if(lightMode==3)
            {
                lightMode = 0;
            }
            NetworkTableInstance.getDefault().getTable("limelight-one").getEntry("ledMode").setNumber(lightMode);
		}
		changeCamera();
    }

    public boolean getPressed(JoystickButton button)
    {
        if(button.get()&&doublePress.get()>=0.25)
        {
            doublePress.reset();
            doublePress.start();
            return true;
        }
        else
        {
            return false;
        }
	}
	
	public void changeCamera()
	{
		if(getPressed(bumperL))
		{
			Constants.limelight = "limelight-one";
		}
		if(getPressed(bumperR))
		{
			Constants.limelight = "limelight-two";
		}
	}

    XboxController controller = new XboxController(1);
	
	public final JoystickButton buttonA = new JoystickButton(controller, Constants.aButton);
	public final JoystickButton buttonB = new JoystickButton(controller, Constants.bButton);
	public final JoystickButton buttonX = new JoystickButton(controller, Constants.xButton);
	public final JoystickButton buttonY = new JoystickButton(controller, Constants.yButton);
	public final JoystickButton bumperL = new JoystickButton(controller, Constants.leftBumper);
	public final JoystickButton bumperR = new JoystickButton(controller, Constants.rightBumper);
	public final JoystickButton backB = new JoystickButton(controller, Constants.backButton);	
	public final JoystickButton startB = new JoystickButton(controller, Constants.startButton);
	public final JoystickButton leftStickB = new JoystickButton(controller, Constants.lStickButton);
	public final JoystickButton rightStickB = new JoystickButton(controller, Constants.rStickButton);
	
	public double deadzone(double input)
	{
		if(Math.abs(input)>Constants.deadzone)
		{
			return input;
		}
		else
		{
			return 0;
		}
	}
	
	public double getLeftY()
	{
		return -deadzone(controller.getY(Hand.kLeft));
	}
	
	public double getLeftX()
	{
		return -deadzone(controller.getX(Hand.kLeft));
	}
	
	public double getRightY()
	{
		return -deadzone(controller.getY(Hand.kRight));
	}
	
	public double getRightX()
	{
		return deadzone(controller.getX(Hand.kRight));
	}
	
	public double getTrigger()
	{
		if(controller.getTriggerAxis(Hand.kRight)>0)
		{
			return deadzone(controller.getTriggerAxis(Hand.kRight));
		}
		else if(controller.getTriggerAxis(Hand.kLeft)>0)
		{
			return -deadzone(controller.getTriggerAxis(Hand.kLeft));
		}
		else
		{
			return 0.0;
        }
    }
}