/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import com.ctre.phoenix.*;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID;


/**
 * Add your crocs here.
 */
public class DriveTrain {
    WPI_TalonSRX talon1;
    WPI_TalonSRX talon2;
    WPI_TalonSRX talon3;
    WPI_TalonSRX talon4;
    DifferentialDrive driveFancy;
    XboxController controller;
    SpeedControllerGroup left;
    SpeedControllerGroup right;

    public DriveTrain()
    {
        talon1 = new WPI_TalonSRX(2);
        talon2 = new WPI_TalonSRX(5);
        talon3 = new WPI_TalonSRX(6);
        talon4 = new WPI_TalonSRX(3);
        talon2.setInverted(true);
        talon3.setInverted(true);
        left = new SpeedControllerGroup(talon1, talon4);
        right = new SpeedControllerGroup(talon3, talon2);
        left.setInverted(true);
        //talon1.follow(talon2);
       // talon1.setControlMode();
        //talon3.follow(talon4);

        driveFancy = new DifferentialDrive(left, right);
        controller = new XboxController(0);
    } 


    public void drive() {
        driveFancy.arcadeDrive(0.7*controller.getY(GenericHID.Hand.kLeft), 0.7*controller.getX(GenericHID.Hand.kRight));
        //driveFancy.arcadeDrive(1,1);    
    }
}


