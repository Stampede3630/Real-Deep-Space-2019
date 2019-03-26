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
    static WPI_TalonSRX talon1 = new WPI_TalonSRX(6);
    static WPI_TalonSRX talon2 = new WPI_TalonSRX(3);
    static WPI_TalonSRX talon3 = new WPI_TalonSRX(5);
    static WPI_TalonSRX talon4 = new WPI_TalonSRX(2);

    static SpeedControllerGroup leftSide = new SpeedControllerGroup (talon1, talon2);
    static SpeedControllerGroup rightSide = new SpeedControllerGroup (talon3, talon4);

    static DifferentialDrive driveFancy = new DifferentialDrive (leftSide, rightSide);
    static XboxController controller = new XboxController(0);

    double xValue;
    double yValue;

    public static void drive() {
        driveFancy.arcadeDrive(controller.getY(GenericHID.Hand.kLeft), controller.getX(GenericHID.Hand.kRight));
    }
}


