package frc.robot;

import com.ctre.phoenix.ILoopable;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableType;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.CANLed.*;

public class Robot extends TimedRobot 
{
 
  public static ShuffleboardTab tab;
  public static DriveTrain driveTrain;
  public static Manipulator manipulator;
  public static Diagnostics diagnostics;
  public static Choosers choosers;
  public static PathChooser pathChooser = new PathChooser();
  public static NetworkTableEntry pathSelected, hatchBallSelected, hatchInBoolean;
  public static boolean isDeleted = false;

  @Override
  public void robotInit() 
  {
    driveTrain = new DriveTrain();
    manipulator = new Manipulator();
    diagnostics = new Diagnostics();
    choosers = new Choosers(driveTrain, manipulator);

    tab = Shuffleboard.getTab("driverTab");
    pathSelected = tab.add("PathSelected", "").withWidget("PathSelector").withSize(7,6).withPosition(3, 0).getEntry();
    hatchBallSelected = tab.add("hatchBallSelected","").withWidget("BigButtonsWidget").withSize(3,2).withPosition(0,0).getEntry();

    hatchInBoolean = tab.add("Hatch In",false).withSize(3,2).withPosition(0,3).getEntry();



    for (ILoopable loop : TaskList.FullList) {
			Schedulers.PeriodicTasks.add(loop);
    }
    

  }
  
  @Override
  public void robotPeriodic() 
  {
    choosers.letterButtons();


    diagnostics.toSmartDashboard();
    pathChooser.stringToPath(pathSelected.getString(""));
    diagnostics.getForwardMode();
    diagnostics.ultrasonicSensorReading();

    

    Schedulers.PeriodicTasks.process();

//    System.out.println(DriverStation.getInstance().getAlliance().toString());
  }

  
  @Override
  public void autonomousInit() 
  {
    manipulator.robotMap.ahrs.reset();

    //untested
    
  }

  
  @Override
  public void autonomousPeriodic() 
  {
    choosers.setDriveMode();

    choosers.setManipulatorMode();

    driveTrain.drive();

    manipulator.manipulatorPeriodic();

    choosers.letterButtons();

    diagnostics.limelightValues();

    diagnostics.getForwardMode();

    choosers.updatePath();
    
  }

  @Override
  public void teleopInit() 
  {
    
  }

  @Override
  public void teleopPeriodic() 
  {
    choosers.setDriveMode();
    
    choosers.setManipulatorMode();

    driveTrain.drive();

    manipulator.manipulatorPeriodic();

    choosers.letterButtons();

    diagnostics.limelightValues();

    choosers.updatePath();
  }

 
  @Override
  public void testPeriodic() 
  {



  }


  //Untested
  @Override
  public void disabledPeriodic()
  {
    //Schedulers.PeriodicTasks.removeAll();

    SmartDashboard.putBoolean("Big Red Button", isDeleted);
    if (isDeleted) 
    {
      pathSelected.delete();
      hatchBallSelected.delete();

      pathSelected = tab.add("PathSelected", "").withWidget("PathSelector").withSize(7,6).withPosition(3, 0).getEntry();
      hatchBallSelected = tab.add("hatchBallSelected","").withWidget("BigButtonsWidget").withSize(3,2).withPosition(0,0).getEntry();

      isDeleted = false;
    }
  }
}