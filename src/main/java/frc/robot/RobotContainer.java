package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import frc.robot.autos.*;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    /* Controllers */
    private final Joystick driver = new Joystick(0);
    private final Joystick operator = new Joystick(1);

    /* Drive Controls */
    private final int translationAxis = XboxController.Axis.kLeftY.value;
    private final int strafeAxis = XboxController.Axis.kLeftX.value;
    private final int rotationAxis = XboxController.Axis.kRightX.value;

    /* Driver Buttons */
    private final JoystickButton zeroGyro = new JoystickButton(driver, XboxController.Button.kRightStick.value);
    private final JoystickButton robotCentric = new JoystickButton(driver, XboxController.Button.kLeftBumper.value);
    private final JoystickButton rightBumper = new JoystickButton(driver, XboxController.Button.kRightBumper.value);
    private final JoystickButton leftBumper = new JoystickButton(driver, XboxController.Button.kLeftBumper.value);

    private final JoystickButton atariJoystickButton2 = new JoystickButton(operator, 2);
    private final JoystickButton atariJoystickButton3 = new JoystickButton(operator, 3);
    private final JoystickButton atariJoystickButton7 = new JoystickButton(operator, 7);
    private final JoystickButton atariJoystickButton8 = new JoystickButton(operator, 8);
    private final JoystickButton atariJoystickButton6 = new JoystickButton(operator, 6);
    private final JoystickButton atariJoystickButton5 = new JoystickButton(operator, 5);

    /* Subsystems */
    private final Swerve s_Swerve = new Swerve();
    private final Collector collector = new Collector();
    private final Indexer indexer = new Indexer();
    private final Launcher launcher = new Launcher();


    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        s_Swerve.setDefaultCommand(
            new TeleopSwerve(
                s_Swerve, 
                () -> -driver.getRawAxis(translationAxis), 
                () -> -driver.getRawAxis(strafeAxis), 
                () -> -driver.getRawAxis(rotationAxis), 
                () -> robotCentric.getAsBoolean(), 
                leftBumper,
                rightBumper
            )
        );

        // Configure the button bindings
        configureButtonBindings();
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        /* Driver Buttons */
        zeroGyro.onTrue(new InstantCommand(() -> s_Swerve.zeroGyro()));
// to score low, we might just want to poop cubes out of the feeder/indexer
        atariJoystickButton2.onTrue(new InstantCommand(() -> launcher.scoreL2()));
        atariJoystickButton2.onFalse(new InstantCommand(() -> launcher.launcherStop()));

        atariJoystickButton3.onTrue(new InstantCommand(() -> launcher.scoreL3()));
        atariJoystickButton3.onFalse(new InstantCommand(() -> launcher.launcherStop()));

       atariJoystickButton7.onTrue(new InstantCommand(() -> collector.collectorIntake()));
       atariJoystickButton7.onFalse(new InstantCommand(() -> collector.collectorStop()));

    //    atariJoystickButton7.onTrue(Commands.runOnce(() -> collector.deployIntake(), collector));
    //    atariJoystickButton7.onFalse(Commands.runOnce(() -> collector.retractIntake(), collector));
    
       atariJoystickButton7.onTrue(new InstantCommand(() -> indexer.indexerCollect()));
       atariJoystickButton7.onFalse(new InstantCommand(() -> indexer.indexerStop()));

       atariJoystickButton8.onTrue(new InstantCommand(() -> collector.collectorOutake()));
       atariJoystickButton8.onFalse(new InstantCommand(() -> collector.collectorStop()));

    //    atariJoystickButton8.onTrue(Commands.runOnce(() -> collector.deployIntake(), collector));
    //    atariJoystickButton8.onFalse(Commands.runOnce(() -> collector.retractIntake(), collector));

       atariJoystickButton6.onTrue(new InstantCommand(() -> collector.runIntakeDeployForward()));
       atariJoystickButton6.onFalse(new InstantCommand(() -> collector.stopIntake()));

       atariJoystickButton5.onTrue(new InstantCommand(() -> collector.runIntakeDeployBackward()));
       atariJoystickButton5.onFalse(new InstantCommand(() -> collector.stopIntake()));

       
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        return new exampleAuto(s_Swerve);
    }
}
