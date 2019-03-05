package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot 
{
 

  public static DriveTrain driveTrain;
  public static Manipulator manipulator;
  public Diagnostics diagnostics;
  public static Choosers choosers;
  public static SendableChooser manipulatorChooser;
  public static PathChooser pathChooser = new PathChooser();
  public static GrassHopper magicWillHappen = new GrassHopper();

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
  }
  
  @Override
  public void robotPeriodic() 
  {
    diagnostics.toSmartDashboard();
    pathChooser.stringToPath(SmartDashboard.getString("Path Selected", ""));
    diagnostics.getForwardMode();
  }

  
  @Override
  public void autonomousInit() 
  {
    
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
    SmartDashboard.putString("Path Selected", "");
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
   magicWillHappen.hopUpTest();



  }
}