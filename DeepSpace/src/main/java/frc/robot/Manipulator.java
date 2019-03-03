package frc.robot;

public class Manipulator
{
    ManipulatorMode manipulatorMode;
    RobotMap robotMap;
    boolean autonomous;

    public Manipulator()
    {
        robotMap = RobotMap.getRobotMap();
    }

    public void manipulatorPeriodic()
    {
        manipulatorMode.disengage();//left bumper
        manipulatorMode.engage(); //right bumper
        if(robotMap.getTrigger()<=0)
        {
            manipulatorMode.deploy(Constants.toRocket); //left trigger
        }
        if(robotMap.getTrigger()>=0)
        {
            manipulatorMode.intake(); //right trigger
        }
    }
}