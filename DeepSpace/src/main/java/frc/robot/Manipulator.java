package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Manipulator
{
    ManipulatorMode manipulatorMode;
    RobotMap robotMap;
    boolean toRocket, autonomous, taskRunning, startDeploy, isLaunched;

    public Manipulator()
    {
        robotMap = RobotMap.getRobotMap();
        autonomous = false;
        taskRunning = false;
        startDeploy = true;
    }

    public void manipulatorExecute()
    {
        if(!autonomous)
        {
            SmartDashboard.putBoolean("autonomous manipulator", autonomous);
            manipulatorMode.disengage();//left bumper
            manipulatorMode.engage(); //right bumper
            if(robotMap.getTrigger()<=0)
            {
                manipulatorMode.deploy(toRocket); //right trigger
            }
            if(robotMap.getTrigger()>=0)
            {
                manipulatorMode.intake(); //left trigger
            }
        }
    }
}