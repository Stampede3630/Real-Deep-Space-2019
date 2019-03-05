package frc.robot;

public class Manipulator {
    ManipulatorMode manipulatorMode;
    RobotMap robotMap;


    //sets up our robot map
    public Manipulator() {
        robotMap = RobotMap.getRobotMap();
    }

    //checks to see what we're doing with the manipulator
    public void manipulatorPeriodic() {
        if(!Constants.ballFollowerExecuting) {
            manipulatorMode.disengage();//left bumper
            manipulatorMode.engage(); //right bumper
            if(robotMap.getTrigger()<=0) {
                manipulatorMode.deploy(Constants.toRocket); //left trigger
            }
            if(robotMap.getTrigger()>=0) {
                manipulatorMode.intake(); //right trigger
            }
        }
    }
}