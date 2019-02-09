package frc.robot;

import javax.lang.model.util.ElementScanner6;

public class Manipulator
{
    ManipulatorMode manipulatorMode;
    RobotMap robotMap;
    boolean toRocket;

    public Manipulator()
    {
        robotMap = RobotMap.getRobotMap();
    }

    public void manipulatorExecute()
    {
        manipulatorMode.disengage();//left bumper
        manipulatorMode.engage(); //right bumper
        if(robotMap.getTrigger()>=0)
        {
            manipulatorMode.deploy(toRocket); //right trigger
        }
        else if(robotMap.getTrigger()<=0)
        {
            manipulatorMode.intake(); //left trigger
        }
    }
}