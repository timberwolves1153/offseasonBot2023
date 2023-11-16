// package frc.robot.autos;

// import com.pathplanner.lib.path.PathPlannerTrajectory;

// import edu.wpi.first.math.controller.PIDController;
// import edu.wpi.first.wpilibj2.command.InstantCommand;
// import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
// import frc.robot.Constants;
// import frc.robot.subsystems.Swerve;

// public class PPAutoBase extends SequentialCommandGroup {
//     private Swerve swerve;
    
//     public PIDController thetaController = new PIDController(Constants.AutoConstants.kPThetaController, 
//         0, 0);

//     public PPAutoBase(Swerve swerve) {
//         this.swerve = swerve;
//         addRequirements(swerve);
//     }

//     public SequentialCommandGroup followTrajectoryCommand(PathPlannerTrajectory path1, boolean isFirstPath) {
//         PIDController thetaController = new PIDController(3, 0, 0);
//         PIDController xController = new PIDController(3, 0, 0);
//         PIDController yController = new PIDController(3, 0, 0);
//         thetaController.enableContinuousInput(-Math.PI, Math.PI);
//             return new SequentialCommandGroup(
//                 new InstantCommand(() -> {
//                     if(isFirstPath) {
//                         swerve.resetOdometry(transformed.getInitialHolonomicMode());
//                     }
//                 })),
//                 new PPSwerveControllerCommand(
//                  path1,
//                  swerve::getPose, 
//                  Constants.Swerve.swerveKinematics, 
//                  xController, 
//                  yController, 
//                  thetaController, 
//                  swerve::setModuleStates,  
//                  true, 
//                  swerve 
//              )
//              .andThen(() -> swerve.stop());
//      }
// }

