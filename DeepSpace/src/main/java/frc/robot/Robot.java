/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.*;



public class Robot extends TimedRobot {
 

  public DriveTrain driveTrain;
  public Manipulator manipulator;
  public Diagnostics diagnostics;
  Choosers choosers;
  Vision vision;
  AnalogInput UltrasonicInput;

  @Override
  public void robotInit() {
    driveTrain = new DriveTrain();
    manipulator = new Manipulator();
    diagnostics = new Diagnostics();
    choosers = new Choosers(driveTrain, manipulator, diagnostics);
    vision = new Vision();
    UltrasonicInput = new AnalogInput(4);
  }
  
  @Override
  public void robotPeriodic() {
    diagnostics.toSmartDashboard();
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
    choosers.automatedTurnToAngle();
    choosers.setManipulatorMode();

    driveTrain.drive();

    vision.execute();

    manipulator.manipulatorExecute();

    diagnostics.toSmartDashboard();
  }

 
  @Override
  public void testPeriodic() {
    double 
    public double getUSVoltage ()
    {
        double UltrasonicVoltage = UltrasonicInput.getVoltage();
        double UltrasonicCalculated = 250*(UltrasonicVoltage / 5) - 25;
        psi = Math.round(UltrasonicCalculated);
        return psi;
    }
    SmartDashboard.putNumber("Ultrasonic Voltage", UltrasonicCalculated);
  }
}
