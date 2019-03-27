package frc.robot;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableType;
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
    choosers.letterButtons();
    if(!pathSelected.exists() || pathSelected.getValue().equals(NetworkTableType.kUnassigned))
    {
      pathSelected = tab.add("PathSelected", "").withWidget("PathSelector").getEntry();
    }
    else if (!pathSelected.isValid())
    {
      pathSelected = tab.add("PathSelected", "turtle").withWidget("PathSelector").getEntry();
//      System.out.println("setting a turtle");
    }


    if(!hatchBallSelected.exists() || hatchBallSelected.getValue().equals(NetworkTableType.kUnassigned))
    {
      hatchBallSelected = tab.add("hatchBallSelected","").withWidget("BigButtonsWidget").getEntry();
    }
    else if (!hatchBallSelected.isValid())
    {
      hatchBallSelected = tab.add("hatchBallSelected","anotherTurtle").withWidget("BigButtonsWidget").getEntry();
//      System.out.println("setting another turtle");
    }

    diagnostics.toSmartDashboard();
    pathChooser.stringToPath(pathSelected.getString(""));
    //System.out.println(pathSelected.getString("defaultdata"));
    diagnostics.getForwardMode();
//    pathChooser.stringToPath(SmartDashboard.getString("Path Selected", ""));
//    System.out.println(pathSelected.getString("")+"|||||"+pathSelected.getValue().toString());
    diagnostics.ultrasonicSensorReading();
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
//    diagnostics.periodicVisionChange();
    
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

//    diagnostics.periodicVisionChange();
  }

 
  @Override
  public void testPeriodic() 
  {



  }
}