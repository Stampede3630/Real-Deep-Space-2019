package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.GenericHID.Hand;

public class GrassHopper {/*
    // Create and initialize 4 double soleniods, 2 things that slide the pistons, and a Timer 
    //Front refers to the front 2 legs
    //back refers to the back 2 legs
    // hopUp is the execute method

    Timer climbTimer = new Timer();
    RobotMap robotMap;
    XboxController controllerTwo;

    boolean stopItAllNow;

    boolean extendAllPistonsComplete = false;
    boolean slideCargoComplete = false;
    boolean frontRetractComplete = false;
    boolean backRetractComplete = false;
    boolean robotIsStillAlive = false;
    boolean miracleOccurred = false;
    Timer grasshopperTimeBomb;
    

    public GrassHopper()
    {
        controllerTwo = new XboxController(1);

        stopItAllNow = false;
        robotMap = RobotMap.getRobotMap();
        grasshopperTimeBomb = new Timer();
    }

    public void frontExtend()
    {
        robotMap.solenoidFront.set(DoubleSolenoid.Value.kForward);
    }

    public void frontRetract()
    {
        robotMap.solenoidFront.set(DoubleSolenoid.Value.kReverse);
        Timer.delay(3);//change later w/ timing
        frontRetractComplete = true;
    
    }
    public void backExtend()
    {
        robotMap.solenoidBack.set(DoubleSolenoid.Value.kForward);
    }

    public void backRetract()
    {
        robotMap.solenoidBack.set(DoubleSolenoid.Value.kReverse);
    }

    public void slideForward()
    {
        robotMap.slideTalon.set(0.4); //we can set this higher, snowBlowers aren't that fast...
    }

    public void slideBack()
    {
        robotMap.slideTalon.set(-0.4);
    }

    public void extendAllPistons() 
    {
        frontExtend();
        Timer.delay(3);//change later as well
        backExtend();
        Timer.delay(5);//also change later
        extendAllPistonsComplete = true;
    }

    public void slideCargo()
    {
        robotMap.slideTalon.set(-0.2);
        if (robotMap.cargoPositionLimitSwitch.get() == true) 
        {
            slideCargoComplete = true;
        }
    }

    public boolean slideHatch()
    {

        if (robotMap.hatchPositionLimitSwitch.get() == true)
        {
            robotMap.slideTalon.set(0);
            return true;
        }

        else
        {
            robotMap.slideTalon.set(-0.2); //We don't know if speed should be negative or positive.
            return false;
        }
    }
    /*
    public boolean slideMiddle()
    {
        if (robotMap.middlePositionLimitSwitch.get() == true)
        {
            robotMap.slideTalon.set(0);
            return true;
        }

        else
        {
            robotMap.slideTalon.set(0.2); //We don't know if speed should be negative or positive.
            return false;
        }
        
    }
    *//*
    public void hopUp ()
    {
        //climbTimer.reset(); //we are reseting this timer every time we call this method
        //climbTimer.start(); //.start() method restarts the timer...

        if (!extendAllPistonsComplete)
        {
           extendAllPistons();
        }
        else if (extendAllPistonsComplete && !slideCargoComplete && !stopItAllNow)
        {
            slideCargo();
        }
        else if (extendAllPistonsComplete && slideCargoComplete && !frontRetractComplete && !stopItAllNow)
        {
            frontRetract();
        }
        else if (extendAllPistonsComplete && slideCargoComplete && frontRetractComplete && !robotIsStillAlive && !stopItAllNow)
        {
           robotMap.drive.driveCartesian(0, 0.4, 0);
           Timer.delay(1); //hella dangerous
           robotMap.drive.driveCartesian(0, 0, 0);
           robotIsStillAlive = true;
        }
        else if (extendAllPistonsComplete && slideCargoComplete && frontRetractComplete && robotIsStillAlive && !backRetractComplete && !stopItAllNow )
        {
            backRetract();
            Timer.delay(5); //hella dangerous
            backRetractComplete = true;
        }
        else if (extendAllPistonsComplete && slideCargoComplete && frontRetractComplete && robotIsStillAlive && backRetractComplete  && !miracleOccurred && !stopItAllNow )
        {
            robotMap.drive.driveCartesian(0, 0.4, 0);
           Timer.delay(1); //hella dangerous
           robotMap.drive.driveCartesian(0, 0, 0);
           miracleOccurred = true;
        }
        if(controllerTwo.getStartButton()||controllerTwo.getBackButton())
        {
            stopItAllNow = true;
        }
    }
    public void hopUpTest()
    {
//        System.out.println(Robot.grassHopperChooser.getSelected().toString());
/*        if(Robot.grassHopperChooser.getSelected().equals("SlideCargo") && robotMap.cargoPositionLimitSwitch.get())
        {
            robotMap.slideTalon.set(0.8);
            //robotMap.drive.driveCartesian(0, controllerTwo.getY(Hand.kLeft)*0.6, 0);
        }
        else if( Robot.grassHopperChooser.getSelected().equals("SlideHatch") && robotMap.hatchPositionLimitSwitch.get())
        {
            robotMap.slideTalon.set(-0.8);
           // robotMap.drive.driveCartesian(0, controllerTwo.getY(Hand.kLeft)*0.6, 0);
        }
        else
        {
            robotMap.slideTalon.set(0);
            //robotMap.drive.driveCartesian(0, 0, 0);
        }
        if(Robot.grassHopperChooser.getSelected().toString().equals("PistonExtend"))
        {
            frontExtend();
            Timer.delay(0.15);
            backExtend();
        }    
        if(Robot.grassHopperChooser.getSelected().toString().equals("PistonRetract"))
        {
            frontRetract();
            backRetract();
        }
        if(Robot.grassHopperChooser.getSelected().toString().equals("FrontPistonRetract"))
        {
            frontRetract();
        }        
        if(Robot.grassHopperChooser.getSelected().toString().equals("BackPistonRetract"))
        {
            backRetract();
        }
        if(controllerTwo.getStartButton()||controllerTwo.getBackButton())
        {
            robotMap.solenoidBack.set(Value.kOff);
            robotMap.solenoidFront.set(Value.kOff);
            stopItAllNow = true;
        }
*/        
  //  }*/
}