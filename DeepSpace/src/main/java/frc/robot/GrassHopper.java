package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Timer;

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

    GrassHopper()
    {
       // Numbers are stand ins
        robotMap = RobotMap.getRobotMap();
        solenoidBack = new DoubleSolenoid(1,2,3);
        solenoidFront = new DoubleSolenoid(1,2,3);
        slideTalon = new WPI_TalonSRX (7);
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
        slideTalon.set(0.4);
    }

    public void slideBack()
    {
        slideTalon.set(-0.4);
    }

    public void hopUp ()
    {
        climbTimer.reset();
        climbTimer.start();
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
            slideForward();
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