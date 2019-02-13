package frc.robot;


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
        if(robotMap.getTrigger()<=0)
        {
            manipulatorMode.deploy(toRocket); //right trigger
        }
        else if(robotMap.getTrigger()>=0) //try removing these if-blocks...
        {
            manipulatorMode.intake(); //left trigger
        }
    }
}