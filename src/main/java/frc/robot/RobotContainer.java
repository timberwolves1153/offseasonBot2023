package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

//import frc.robot.autos.*;
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
    private final Joystick atari = new Joystick(1);


    /* Drive Controls */
    private final int translationAxis = XboxController.Axis.kLeftY.value;
    private final int strafeAxis = XboxController.Axis.kLeftX.value;
    private final int rotationAxis = XboxController.Axis.kRightX.value;

    /* Driver Buttons */
    private final JoystickButton zeroGyro = new JoystickButton(driver, XboxController.Button.kY.value);
    private final JoystickButton robotCentric = new JoystickButton(driver, XboxController.Button.kLeftBumper.value);

    private final JoystickButton IntakeButton7 = new JoystickButton(atari, 7);//SUBJECT TO CHANGE
    private final JoystickButton OuttakeButton8 = new JoystickButton(atari, 8);//SUBJECT TO CHANGE
    private final JoystickButton PivotForward11 = new JoystickButton(atari, 11);//SUBJECT TO CHANGE
    private final JoystickButton PivotBack12 = new JoystickButton(atari, 12);//SUBJECT TO CHANGE



    /* Subsystems */
    private final Swerve s_Swerve = new Swerve();
    private final Collector collector = new Collector();
   


    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        s_Swerve.setDefaultCommand(
            new TeleopSwerve(
                s_Swerve, 
                () -> -driver.getRawAxis(translationAxis), 
                () -> -driver.getRawAxis(strafeAxis), 
                () -> -driver.getRawAxis(rotationAxis), 
                () -> robotCentric.getAsBoolean()
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

        IntakeButton7.onTrue(new InstantCommand(() -> collector.collectorIntake()));//SUBJECT TO CHANGE
        IntakeButton7.onFalse(new InstantCommand(() -> collector.collectorStop()));//SUBJECT TO CHANGE

        OuttakeButton8.onTrue(new InstantCommand(() -> collector.collectorOuttake()));//SUBJECT TO CHANGE
        OuttakeButton8.onFalse(new InstantCommand(() -> collector.collectorStop()));//SUBJECT TO CHANGE

        PivotForward11.onTrue(new InstantCommand(() -> collector.pivotForward()));//SUBJECT TO CHANGE
        PivotForward11.onFalse(new InstantCommand(() -> collector.pivotStop()));//SUBJECT TO CHANGE
       
        PivotBack12.onTrue(new InstantCommand(() -> collector.pivotBack()));//SUBJECT TO CHANGE    
        PivotBack12.onFalse(new InstantCommand(() -> collector.pivotStop()));//SUBJECT TO CHANGE
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        return null;
    }
}
