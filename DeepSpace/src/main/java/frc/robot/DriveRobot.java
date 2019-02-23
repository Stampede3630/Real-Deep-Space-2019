package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.MecanumDrive;


public class DriveRobot{

    public  Talon talonFL =  new Talon (0); 
    public  Talon talonFR =  new Talon (2); //3
    public  Talon talonBL =  new Talon (1); //2
	public  Talon talonBR =  new Talon (3); //1

    SpeedControllerGroup right = new SpeedControllerGroup(talonFR, talonBR);
    SpeedControllerGroup left = new SpeedControllerGroup(talonFL, talonBL);

    DifferentialDrive drive = new DifferentialDrive(left, right);

    public XboxController controller = new XboxController(0);

    /*public double getLeftY()
	{
		return (controller.getY(Hand.kLeft));
	}
	
	public double getLeftX()
	{
		return (controller.getX(Hand.kLeft));
	}
	
	public double getRightY()
	{
		return (controller.getY(Hand.kRight));
	}
	
	public double getRightX()
	{
        return (controller.getX(Hand.kRight));
    }*/
    
    public void drivePeriodic()
    {
        drive.arcadeDrive(0.5 *(controller.getY(Hand.kLeft)), 0.5 *(controller.getX(Hand.kRight)));


    }
	











}