package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;

public class GrassHopper {
    // Create and initialize 4 double soleniods, 2 things that slide the pistons, and a Timer 
    //Front refers to the front 2 legs
    //back refers to the back 2 legs
    // hopUp is the execute method

    Timer climbTimer = new Timer();
    DoubleSolenoid solenoidBack;
    DoubleSolenoid solenoidFront;
    public final WPI_TalonSRX slideTalon;
    RobotMap robotMap;
    AnalogInput lowReedSwitch;
    AnalogInput medReedSwitch;
    AnalogInput highReedSwitch;
    DigitalInput hatchPositionLimitSwitch;
    DigitalInput middlePositionLimitSwitch;
    DigitalInput cargoPositionLimitSwitch;

    public GrassHopper()
    {
       // Numbers are stand ins
        robotMap = RobotMap.getRobotMap();
        solenoidBack = new DoubleSolenoid(1,2,3);
        solenoidFront = new DoubleSolenoid(1,2,3);
        slideTalon = new WPI_TalonSRX (7);
        lowReedSwitch = new AnalogInput(0);
        medReedSwitch = new AnalogInput(1);
        highReedSwitch = new AnalogInput(2);
        hatchPositionLimitSwitch = new DigitalInput(3);
        middlePositionLimitSwitch = new DigitalInput(4);
        cargoPositionLimitSwitch = new DigitalInput(5);

        //these port numbers are made up and do not mean anything
    }

    public void frontExtend()
    {
        solenoidFront.set(DoubleSolenoid.Value.kForward);
    }

    public void frontRetract()
    {
        solenoidFront.set(DoubleSolenoid.Value.kReverse);
    }
    public void backExtend()
    {
        solenoidBack.set(DoubleSolenoid.Value.kForward);
    }

    public void backRetract()
    {
        solenoidBack.set(DoubleSolenoid.Value.kReverse);
    }

    public void slideForward()
    {
        slideTalon.set(0.4); //we can set this higher, snowBlowers aren't that fast...
    }

    public void slideBack()
    {
        slideTalon.set(-0.4);
    }

    public boolean extendAllPistons() 
    {
        frontExtend();
        backExtend();

        if (highReedSwitch.getVoltage() == 4.5) {
            return true;
        }
        else return false;
    }

    public boolean slideCargo()
    {

        if (cargoPositionLimitSwitch.get() == true) 
        {
            slideTalon.set(0);
            return true;
        }
        else
        {
            slideTalon.set(0.2); //We don't know if speed should be negative or positive.
            return false;
        }
    }

    public boolean slideHatch()
    {

        if (hatchPositionLimitSwitch.get() == true)
        {
            slideTalon.set(0);
            return true;
        }

        else
        {
            slideTalon.set(-0.2); //We don't know if speed should be negative or positive.
            return false;
        }
    }

    public boolean slideMiddle()
    {
        if (middlePositionLimitSwitch.get() == true)
        {
            slideTalon.set(0);
            return true;
        }

        else
        {
            slideTalon.set(0.2); //We don't know if speed should be negative or positive.
            return false;
        }
    }
    public void hopUp ()
    {
        climbTimer.reset(); //we are reseting this timer every time we call this method
        climbTimer.start(); //.start() method restarts the timer...
        frontExtend();
        backExtend();
        if (climbTimer.get()>=2)
        {
            slideBack();
        }
        else if (climbTimer.get()>=4)
        {
            frontRetract();
        }
        else if (climbTimer.get()>=6)
        {
            robotMap.drive.driveCartesian(0, 0.4, 0);
        }
        else if (climbTimer.get()>=7.5)
        {
            backRetract();
            slideForward(); //shall we move this one step higher?
        }
        else if (climbTimer.get()>=9.5)
        {
            robotMap.drive.driveCartesian(0, 0.4, 0);
        }
        else if (climbTimer.get()>11)
        {
            climbTimer.reset();
            climbTimer.stop();
        }
    }
}
