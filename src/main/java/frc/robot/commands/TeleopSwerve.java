package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.Swerve;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;


public class TeleopSwerve extends CommandBase {    
    private Swerve s_Swerve;    
    private DoubleSupplier translationSup;
    private DoubleSupplier strafeSup;
    private DoubleSupplier rotationSup;
    private BooleanSupplier robotCentricSup;
    private BooleanSupplier halfSpeed;
    private BooleanSupplier quarterSpeed;

    public TeleopSwerve(
        Swerve s_Swerve, 
        DoubleSupplier translationSup, 
        DoubleSupplier strafeSup, 
        DoubleSupplier rotationSup, 
        BooleanSupplier robotCentricSup,
        BooleanSupplier quarterSpeed,
        BooleanSupplier halfSpeed) {
        this.s_Swerve = s_Swerve;
        addRequirements(s_Swerve);

        this.translationSup = translationSup;
        this.strafeSup = strafeSup;
        this.rotationSup = rotationSup;
        this.robotCentricSup = robotCentricSup;
        this.quarterSpeed = quarterSpeed;
        this.halfSpeed = halfSpeed;
    }

    @Override
    public void execute() {
        /* Get Values, Deadband*/
        double translationVal = MathUtil.applyDeadband(translationSup.getAsDouble(), Constants.stickDeadband);
        double strafeVal = MathUtil.applyDeadband(strafeSup.getAsDouble(), Constants.stickDeadband);
        double rotationVal = MathUtil.applyDeadband(rotationSup.getAsDouble(), Constants.stickDeadband);

        if(halfSpeed.getAsBoolean()) {
        
            translationVal = translationVal * 0.5;
            strafeVal = strafeVal * 0.5;
            rotationVal = rotationVal * 0.5;
        } else if (quarterSpeed.getAsBoolean()) {
            
            translationVal = translationVal * 0.25;
            strafeVal = strafeVal * 0.25;
            rotationVal = rotationVal * 0.25;
        } else {
            translationVal = translationVal * 1;
            strafeVal = strafeVal * 1;
            rotationVal = rotationVal * 1;
        }
        /* Drive */
        s_Swerve.drive(
            new Translation2d(translationVal, strafeVal).times(Constants.Swerve.maxSpeed), 
            rotationVal * Constants.Swerve.maxAngularVelocity, 
            false, 
            true
        );
    }
}