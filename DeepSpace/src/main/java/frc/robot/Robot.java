/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends TimedRobot {
 

  public DriveTrain driveTrain;
  public Manipulator manipulator;
  public Diagnostics diagnostics;
  public Choosers choosers;
  Vision vision;
  SendableChooser manipulatorChooser;
  PathChooser pathChooser = new PathChooser();

  @Override
  public void robotInit() {
    driveTrain = new DriveTrain();
    manipulator = new Manipulator();
    diagnostics = new Diagnostics();
    choosers = new Choosers(driveTrain, manipulator, diagnostics);
    vision = new Vision();
    manipulatorChooser = new SendableChooser();
    manipulatorChooser.addDefault("Hatch Forward", "Hatch");
    manipulatorChooser.addObject("Ball Forward", "Ball");
    SmartDashboard.putData("Forward Chooser", manipulatorChooser);
  }
  
  @Override
  public void robotPeriodic() {
    diagnostics.toSmartDashboard();
    pathChooser.stringToPath(SmartDashboard.getString("Path Selected", ""));
  }

  
  @Override
  public void autonomousInit() {
    
  }

  
  @Override
  public void autonomousPeriodic() 
  {
    
  }

  @Override
  public void teleopInit() {

  }

  @Override
  public void teleopPeriodic() 
  {
    choosers.setDriveMode();
    //choosers.automatedTurnToAngle();
    choosers.setManipulatorMode();

    choosers.chooserAngle(pathChooser.angle);

    driveTrain.drive();

    vision.execute();

    manipulator.manipulatorExecute();

    diagnostics.toSmartDashboard();
  }

 
  @Override
  public void testPeriodic() {
  }
}
