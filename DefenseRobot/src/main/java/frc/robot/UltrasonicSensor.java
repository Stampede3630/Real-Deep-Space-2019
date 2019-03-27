/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.*;
    

/**
 * Add your docs here.
 */
public class UltrasonicSensor {
    Ultrasonic ultrasonicSensor;
    AnalogInput dUltrasonicSensor;
    double sonicDistance;
    double digitalSonicDistance;
    boolean hatchIn;

    public UltrasonicSensor(){
        ultrasonicSensor = new Ultrasonic(1,2);//input ping, output echo (9,8)
       // vftgvy = new Ultrasonic(pingChannel, echoChannel)
        dUltrasonicSensor = new AnalogInput(0);
        ultrasonicSensor.setAutomaticMode(true);
    }

    public void sonicValue(){
        sonicDistance = ultrasonicSensor.getRangeInches();
        digitalSonicDistance = dUltrasonicSensor.getValue();
        SmartDashboard.putNumber("Distance from vex", sonicDistance);
        SmartDashboard.putNumber("Distance from analog2", digitalSonicDistance);

        if (sonicDistance < 2.5) {
            hatchIn = true;
            SmartDashboard.putBoolean("Hatch In", hatchIn);

        }

        else {
            hatchIn = false;
            SmartDashboard.putBoolean("Hatch In", hatchIn);
        }
        
    }
   
    



    
}