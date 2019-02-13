package frc.robot;


public class Manipulator
{
    ManipulatorMode manipulatorMode;
    RobotMap robotMap;
    boolean toRocket, autonomous;

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
            manipulatorMode.deploy(toRocket,autonomous); //right trigger
        }
        else if(robotMap.getTrigger()>=0) //try removing these if-blocks...
        {
            manipulatorMode.intake(autonomous); //left trigger
        }
    }
}