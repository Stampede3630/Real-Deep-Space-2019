package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
<<<<<<< HEAD

=======
>>>>>>> master

public class Robot extends TimedRobot 
{
 

  public static DriveTrain driveTrain;
  public static Manipulator manipulator;
  public Diagnostics diagnostics;
<<<<<<< HEAD
  public static Choosers choosers;
  public static SendableChooser manipulatorChooser;
  public static PathChooser pathChooser = new PathChooser();
  public static GrassHopper magicWillHappen = new GrassHopper();
=======
  public Choosers choosers;
  Vision vision;
  SendableChooser manipulatorChooser;
  PathChooser pathChooser = new PathChooser();
>>>>>>> master

  @Override
  public void robotInit() 
  {
    driveTrain = new DriveTrain();
    manipulator = new Manipulator();
    diagnostics = new Diagnostics();
<<<<<<< HEAD
    choosers = new Choosers(driveTrain, manipulator);
=======
    choosers = new Choosers(driveTrain, manipulator, diagnostics);
    vision = new Vision();
>>>>>>> master
    manipulatorChooser = new SendableChooser();
    manipulatorChooser.addDefault("Hatch Forward", "Hatch");
    manipulatorChooser.addObject("Ball Forward", "Ball");
    SmartDashboard.putData("Forward Chooser", manipulatorChooser);
<<<<<<< HEAD
    SmartDashboard.putString("Path Selected", "");
=======
>>>>>>> master
  }
  
  @Override
  public void robotPeriodic() 
  {
    diagnostics.toSmartDashboard();
    pathChooser.stringToPath(SmartDashboard.getString("Path Selected", ""));
  }

  
  @Override
  public void autonomousInit() 
  {
    
  }

  
  @Override
  public void autonomousPeriodic() 
  {
    
  }

  @Override
  public void teleopInit() 
  {

  }

  @Override
  public void teleopPeriodic() 
  {
    choosers.setDriveMode();
    //choosers.automatedTurnToAngle();
    choosers.setManipulatorMode();

<<<<<<< HEAD
//    choosers.chooserAngle(pathChooser.angle);

//    choosers.angleSwitch();

//    choosers.setAction();
=======
    choosers.chooserAngle(pathChooser.angle);
>>>>>>> master

    driveTrain.drive();

    manipulator.manipulatorPeriodic();

    choosers.letterButtons();

//    diagnostics.toSmartDashboard();
  }

 
  @Override
  public void testPeriodic() 
  {
    magicWillHappen.hopUpTest();
  }
}