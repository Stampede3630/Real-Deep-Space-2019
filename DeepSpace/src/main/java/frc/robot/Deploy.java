package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;

public class Deploy {

    DigitalInput limitSwitch;
    Manipulator manipulator = new Manipulator();
    Ball ball = new Ball(manipulator);

    public void robotInit() {
    	limitSwitch = new DigitalInput(1); //we can change the port later
    }

    public void limitSwitchChecker() {
    	while (limitSwitch.get()) {
    		ball.deploy(true);
    	}
    }
}