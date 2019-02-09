package frc.robot;

public class autoDrive
{

    String autoCase, autoPiece;
    RobotMap robotMap;
    DriveTrain driveTrain;

    public autoDrive(RobotMap robotMap, DriveTrain driveTrain)
    {
        this.robotMap = robotMap;
        this.driveTrain = driveTrain;
    }

    public void drive() //each case should contain limelight settings, driveMode
    {
        switch(autoPiece)
        {
            case "hatch": 
                switch(autoCase)  //0 -> leftmost, 1-> closest, 2-> rightmost
                {
                    case "LeftFarCS": hatchDeploy(0);
                
                    case "LeftMidCS": hatchDeploy(1);
    
                    case "LeftNearCS": hatchDeploy(2);
    
                    case "leftFarRS": hatchDeploy(1);
    
                    case "LeftNearRS": hatchDeploy(1);
    
                    case "RightFarRS": hatchDeploy(1);
    
                    case "RightNearRS": hatchDeploy(1);
    
                    case "RightFarCS": hatchDeploy(2);
    
                    case "RightMidCS": hatchDeploy(1);
    
                    case "RightNearCS": hatchDeploy(0);

                    case "LeftFaceCS": hatchDeploy(0);

                    case "RightFaceCS": hatchDeploy(2);

                    case "RightMidRS": System.out.println("no hatches here");

                    case "LeftMidRS": System.out.println("no hatches here");
                }

            case "ball":
                switch(autoCase) 
                {
                    case "LeftFarCS": ballDeploy();
                
                    case "LeftMidCS": ballDeploy();
    
                    case "LeftNearCS": ballDeploy();
    
                    case "LeftMidRS": ballDeploy();
    
                    case "RightMidRS": ballDeploy();
    
                    case "RightFarCS": ballDeploy();
    
                    case "RightMidCS": ballDeploy();
    
                    case "RightNearCS": ballDeploy();

                    case "LeftFaceCS": ballDeploy();

                    case "RightFaceCS": ballDeploy();

                    case "RightFarRS": System.out.println("no balls here");

                    case "RightNearRS": System.out.println("no balls here");

                    case "LeftFarRS": System.out.println("no balls here");

                    case "LeftNearRS": System.out.println("no balls here");
                }
        }
    }

    public void ballDeploy()
    {

    }

    public void hatchDeploy(int pipeline)
    {

    }


}