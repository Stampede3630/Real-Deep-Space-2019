package frc.robot;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class Robot extends TimedRobot 
{
 
  public static ShuffleboardTab tab;
  public static DriveTrain driveTrain;
  public static Manipulator manipulator;
  public static Diagnostics diagnostics;
  public static Choosers choosers;
  public static SendableChooser manipulatorChooser, grassHopperChooser;

  public static PathChooser pathChooser = new PathChooser();
  public static GrassHopper magicWillHappen = new GrassHopper();
  public static NetworkTableEntry pathSelected, hatchBallSelected;

  @Override
  public void robotInit() 
  {
    driveTrain = new DriveTrain();
    manipulator = new Manipulator();
    diagnostics = new Diagnostics();
    choosers = new Choosers(driveTrain, manipulator);
    manipulatorChooser = new SendableChooser();
    manipulatorChooser.addDefault("Hatch Forward", "Hatch");
    manipulatorChooser.addObject("Ball Forward", "Ball");
    SmartDashboard.putData("Forward Chooser", manipulatorChooser);
    SmartDashboard.putString("Test Forward Chooser", "");
    SmartDashboard.putString("Path Selected", "");

    tab = Shuffleboard.getTab("driverTab");
    pathSelected = tab.add("PathSelected", "").withWidget("PathSelector").getEntry();
    hatchBallSelected = tab.add("hatchBallSelected","").withWidget("BigButtonsWidget").getEntry();


    grassHopperChooser = new SendableChooser();
    grassHopperChooser.addDefault("E Stop", "E stop");
    grassHopperChooser.addObject("Pistons Extend", "PistonExtend");
    grassHopperChooser.addObject("Pistons Retract", "PistonRetract");
    grassHopperChooser.addObject("Front Piston Retract", "FrontPistonRetract");
    grassHopperChooser.addObject("Back Piston Retract", "BackPistonRetract");
    grassHopperChooser.addObject("Slide Hatch", "SlideHatch");
    grassHopperChooser.addObject("Slide Cargo", "SlideCargo");
    SmartDashboard.putData("Grasshopper Chooser" , grassHopperChooser);
    

  }
  
  @Override
  public void robotPeriodic() 
  {
    if(!pathSelected.exists())
    {
      pathSelected = tab.add("PathSelected", "").withWidget("PathSelector").getEntry();
    }
    else if (pathSelected.getString("").equals(""))
    {
      pathSelected = tab.add("PathSelected", "turtle").withWidget("PathSelector").getEntry();

    }


    if(!hatchBallSelected.exists())
    {
      hatchBallSelected = tab.add("hatchBallSelected","").withWidget("BigButtonsWidget").getEntry();
    }
/*    else if (hatchBallSelected.getString("").equals(""))
    {
      hatchBallSelected = tab.add("hatchBallSelected","").withWidget("BigButtonsWidget").getEntry();

    }
*/
    diagnostics.toSmartDashboard();
    pathChooser.stringToPath(pathSelected.getString(""));
    //System.out.println(pathSelected.getString("defaultdata"));
    diagnostics.getForwardMode();
//    pathChooser.stringToPath(SmartDashboard.getString("Path Selected", ""));
  }

  
  @Override
  public void autonomousInit() 
  {
    manipulator.robotMap.ahrs.reset();
//    diagnostics.SolenoidReset();
  }

  
  @Override
  public void autonomousPeriodic() 
  {
    choosers.setDriveMode();
    //choosers.automatedTurnToAngle();
    choosers.setManipulatorMode();

//    choosers.chooserAngle(pathChooser.angle);

//    choosers.angleSwitch();

//    choosers.setAction();

    driveTrain.drive();

    manipulator.manipulatorPeriodic();

    choosers.letterButtons();

    diagnostics.limelightValues();

    diagnostics.getForwardMode();

//    diagnostics.toSmartDashboard();
    
  }

  @Override
  public void teleopInit() 
  {
    SmartDashboard.putData("Forward Chooser", manipulatorChooser);
  }

  @Override
  public void teleopPeriodic() 
  {
    choosers.setDriveMode();
    //choosers.automatedTurnToAngle();
    choosers.setManipulatorMode();

//    choosers.chooserAngle(pathChooser.angle);

//    choosers.angleSwitch();

//    choosers.setAction();

    driveTrain.drive();

    manipulator.manipulatorPeriodic();

    choosers.letterButtons();

    diagnostics.limelightValues();

//    diagnostics.toSmartDashboard();
  }

 
  @Override
  public void testPeriodic() 
  {



  }
}