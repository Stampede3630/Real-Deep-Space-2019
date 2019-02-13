/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * Add your docs here.
 */
public interface ManipulatorMode {
    public void intake(boolean autonomous);
    public void deploy(boolean rocketMode, boolean autonomous);
    public void engage();
    public void disengage();
}
