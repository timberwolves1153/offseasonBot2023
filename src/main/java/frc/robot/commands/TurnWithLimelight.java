package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Swerve;

public class TurnWithLimelight extends CommandBase{
    
    private Swerve swerve;
    private Limelight limelight;
    private double maxRunTimeSeconds;
    private double startTime;

    private double degrees;
    private double sign;
    private double startingPosition; // better name might be StartingX or StartingY - look and limelight values

    public TurnWithLimelight(double maxRunTimeSeconds, Swerve swerve, Limelight limelight) {

        this.swerve = swerve;
        this.limelight = limelight;
        this.maxRunTimeSeconds = maxRunTimeSeconds;

        addRequirements(swerve);
    }

    @Override
    public void initialize() {
        degrees = limelight.getTargetValues().x; //  need to check if this is the correct variable to look at

        startTime = System.currentTimeMillis();

        NetworkTableInstance.getDefault().getTable("limelight").getEntry("snapshot").setValue(1);
    }

    @Override
    public void execute() {

        double speed;
    System.out.println(limelight.targetExists());

    /* Logic for limelight alignment
     * 1. check limelight X values relative to robot
     * 2. set x = 0 as setpoint for drive 
     * 3. move robot until current position is x = 0
     * 4. 
     */

    if(limelight.targetExists()) {
      if(!finishedTurning()) {
        if(Math.abs(limelight.getTargetValues().x) > 10) {
          speed = 0.5;
        } else if(Math.abs(limelight.getTargetValues().x) > 5) {
          speed = 0.4;
        } else {
          speed = 0.32;
        }
    
        }
    }
}

private boolean finishedMoving() {
    if(sign < 0) {
        // need to check logic
        // want to change degrees to x/y distance
      return swerve.getHeading() >= startingDegrees - degrees;
    } else {
      return swerve.getHeading() <= startingDegrees - degrees;
    }
  }

}
