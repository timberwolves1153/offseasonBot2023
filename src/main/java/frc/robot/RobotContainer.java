package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.lib.util.AxisButton;
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
    private final JoystickButton driveRightBumper = new JoystickButton(driver, XboxController.Button.kRightBumper.value);
    private final JoystickButton driveLeftBumper = new JoystickButton(driver, XboxController.Button.kLeftBumper.value);

    private final AxisButton leftTrigger = new AxisButton(operator, XboxController.Axis.kLeftTrigger.value, 0.5);
    private final AxisButton rightTrigger = new AxisButton(operator, XboxController.Axis.kRightTrigger.value, 0.5);

    private final JoystickButton opLeftBumper = new JoystickButton(operator, XboxController.Button.kLeftBumper.value);
    private final JoystickButton opRightBumper = new JoystickButton(operator, XboxController.Button.kRightBumper.value);

    private final JoystickButton opY = new JoystickButton(operator, XboxController.Button.kY.value);
    private final JoystickButton opA = new JoystickButton(operator, XboxController.Button.kA.value);
    private final JoystickButton opX = new JoystickButton(operator, XboxController.Button.kX.value);
    private final JoystickButton opB = new JoystickButton(operator, XboxController.Button.kB.value);
    private final POVButton DownDPad = new POVButton(operator, 180);
    private final POVButton UpDPad = new POVButton(operator, 0);

    //private final JoystickButton OP = new JoystickButton(operator, XboxController.Button.kRightBumper.value);

    
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

        opA.onTrue(new InstantCommand(() -> collector.deployMotorForward()));
        opA.onFalse(new InstantCommand(() -> collector.deployMotorStop()));

        opLeftBumper.onTrue(new InstantCommand(() -> collector.collectorIntake()));
        opLeftBumper.onFalse(new InstantCommand(() -> collector.collectorStop()));

        leftTrigger.onTrue(new InstantCommand(() -> indexer.runIndexMotor1()));
        leftTrigger.onFalse(new InstantCommand(() -> indexer.stopIndexMotor1()));


        opX.onTrue(new InstantCommand(() -> collector.deployMotorBackward()));
        opX.onFalse(new InstantCommand(() -> collector.deployMotorStop()));

        opRightBumper.onTrue(new InstantCommand(() -> collector.collectorOuttake()));
        opRightBumper.onFalse(new InstantCommand(() -> collector.collectorStop()));

        rightTrigger.onTrue(new InstantCommand(() -> indexer.reverseIndexMotor1()));
        rightTrigger.onFalse(new InstantCommand(() -> indexer.stopIndexMotor1()));

       // opY.onTrue(new InstantCommand(() -> launcher.setLauncherSetpoint(0, 0)));

       opY.onTrue(new InstantCommand(() -> launcher.runLauncher()));
       opY.onFalse(new InstantCommand(() -> launcher.stopLauncher()));

       UpDPad.onTrue(new InstantCommand(() -> indexer.indexslow()));
       UpDPad.onFalse(new InstantCommand(() -> indexer.stopIndexMotor1()));

       DownDPad.onTrue(new InstantCommand(() -> indexer.reverseindexslow()));
       DownDPad.onFalse(new InstantCommand(() -> indexer.stopIndexMotor1()));
       
        // change to setting setpoints later
       
        // UpDPad.onTrue(Commands.runOnce(() -> collector.retractIntake(), collector));
        // DownDPad.onTrue(Commands.runOnce(() -> collector.deployIntake(), collector));
        // opB.onTrue(new InstantCommand(() -> collector.resetPivotEncoder()));

        
       
    }

    public Collector getCollector() {
        return collector;
    }

    public Joystick getDriveController(){
        return driver;
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
