package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
//import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;
import frc.lib.util.NEOSwerveConstants;
import frc.lib.util.SwerveModuleConstants;

public final class Constants {

    public static final boolean collectorTuningMode = false;
    public static final double stickDeadband = 0.1;


    public static final class Swerve {
        public static final int pigeonID = 5;
        public static final boolean invertGyro = false; // Always ensure Gyro is CCW+ CW-

        public static final NEOSwerveConstants chosenModule =  //TODO: This must be tuned to specific robot
            NEOSwerveConstants.SDSMK4i(NEOSwerveConstants.driveGearRatios.SDSMK4i_L2);

        /* Drivetrain Constants */
        public static final double trackWidth = Units.inchesToMeters(20.98); //TODO: This must be tuned to specific robot
        public static final double wheelBase = Units.inchesToMeters(20.98); //TODO: This must be tuned to specific robot
        public static final double wheelCircumference = chosenModule.wheelCircumference;
        public static final double wheelDiameter = chosenModule.wheelDiameter;

        /* Swerve Kinematics 
         * No need to ever change this unless you are not doing a traditional rectangular/square 4 module swerve */
         public static final SwerveDriveKinematics swerveKinematics = new SwerveDriveKinematics(
            new Translation2d(wheelBase / 2.0, trackWidth / 2.0),
            new Translation2d(wheelBase / 2.0, -trackWidth / 2.0),
            new Translation2d(-wheelBase / 2.0, trackWidth / 2.0),
            new Translation2d(-wheelBase / 2.0, -trackWidth / 2.0));

        public static final double voltageComp = 12.0;


        /* Module Gear Ratios */
        public static final double driveGearRatio = chosenModule.driveGearRatio;
        public static final double angleGearRatio = chosenModule.angleGearRatio;

        /* Motor Inverts */
        public static final boolean angleMotorInvert = chosenModule.angleMotorInvert;
        public static final boolean driveMotorInvert = chosenModule.driveMotorInvert;

        /* Angle Encoder Invert */
        public static final boolean absoluteEncoderPortsInvert = chosenModule.absoluteEncoderPortsInvert;

        /* Swerve Current Limiting */
        public static final int angleContinuousCurrentLimit = 40;
        public static final int anglePeakCurrentLimit = 20;
        public static final double anglePeakCurrentDuration = 0.1;
        public static final boolean angleEnableCurrentLimit = true;

        public static final int driveContinuousCurrentLimit = 80;
        public static final int drivePeakCurrentLimit = 60;
        public static final double drivePeakCurrentDuration = 0.1;
        public static final boolean driveEnableCurrentLimit = true;

        /* These values are used by the drive falcon to ramp in open loop and closed loop driving.
         * We found a small open loop ramp (0.25) helps with tread wear, tipping, etc */
        public static final double openLoopRamp = 0.25;
        public static final double closedLoopRamp = 0.0;

        /* Angle Motor PID Values */
        // public static final double angleKP = chosenModule.angleKP;
        // public static final double angleKI = chosenModule.angleKI;
        // public static final double angleKD = chosenModule.angleKD;
        // public static final double angleKFF = chosenModule.angleKFF;

        public static final double angleKP = 0.01;
        public static final double angleKI = 0.00;
        public static final double angleKD = 0.00;
        public static final double angleKFF = 0.00;


        /* Drive Motor PID Values */
        public static final double driveKP = 0.1; //TODO: This must be tuned to specific robot
        public static final double driveKI = 0.0;
        public static final double driveKD = 0.0;
        public static final double driveKFF = 0.0;


        /* Drive Motor Characterization Values 
         * Divide SYSID values by 12 to convert from volts to percent output for CTRE */
        public static final double driveKS = (0.667); //TODO: This must be tuned to specific robot
        public static final double driveKV = (2.44);
        public static final double driveKA = (0.27);

        public static final double driveConversionPositionFactor = 
            (wheelDiameter * Math.PI) / driveGearRatio;
        public static final double driveConversionVelocityFactor = driveConversionPositionFactor / 60.0;
        public static final double angleConversionFactor = 360.0 / angleGearRatio;

        /* Swerve Profiling Values */
        /** Meters per Second */
        
        public static final double maxSpeed = 4.5; //TODO: This must be tuned to specific robot
        /** Radians per Second */
        public static final double maxAngularVelocity = 11.5; //TODO: This must be tuned to specific robot

        /* Neutral Modes */
        public static final IdleMode angleNeutralMode = IdleMode.kCoast;
        public static final IdleMode driveNeutralMode = IdleMode.kBrake;
       // public static final double angleConversionFactor = 0;

        /* Module Specific Constants */
        /* Front Left Module - Module 0 */
        public static final class Mod0 { //TODO: This must be tuned to specific robot
            public static final int driveMotorID = 01;
            public static final int angleMotorID = 02;
            public static final int absoluteEncoderPorts = 0;
            public static final Rotation2d angleOffset = Rotation2d.fromDegrees(165.70+180);
            public static final SwerveModuleConstants constants = 
                new SwerveModuleConstants(driveMotorID, angleMotorID, absoluteEncoderPorts, angleOffset);
        }

        /* Front Right Module - Module 1 */
        public static final class Mod1 { //TODO: This must be tuned to specific robot
            public static final int driveMotorID = 11;
            public static final int angleMotorID = 12;
            public static final int absoluteEncoderPorts = 1;
            public static final Rotation2d angleOffset = Rotation2d.fromDegrees(47.77+180);
            public static final SwerveModuleConstants constants = 
                new SwerveModuleConstants(driveMotorID, angleMotorID, absoluteEncoderPorts, angleOffset);
        }
        
        /* Back Left Module - Module 2 */
        public static final class Mod2 { //TODO: This must be tuned to specific robot
            public static final int driveMotorID = 21;
            public static final int angleMotorID = 22;
            public static final int absoluteEncoderPorts = 2;
            public static final Rotation2d angleOffset = Rotation2d.fromDegrees(50.71+180);
            public static final SwerveModuleConstants constants = 
                new SwerveModuleConstants(driveMotorID, angleMotorID, absoluteEncoderPorts, angleOffset);
        }

        /* Back Right Module - Module 3 */
        public static final class Mod3 { //TODO: This must be tuned to specific robot
            public static final int driveMotorID = 31;
            public static final int angleMotorID = 32;
            public static final int absoluteEncoderPorts = 3; //MIGHT SWAP WITH 1
            public static final Rotation2d angleOffset = Rotation2d.fromDegrees(213.53+180);
            public static final SwerveModuleConstants constants = 
                new SwerveModuleConstants(driveMotorID, angleMotorID, absoluteEncoderPorts, angleOffset);
        }
    }

    public static final class AutoConstants { //TODO: The below constants are used in the example auto, and must be tuned to specific robot
        public static final double kMaxSpeedMetersPerSecond = 3;
        public static final double kMaxAccelerationMetersPerSecondSquared = 3;
        public static final double kMaxAngularSpeedRadiansPerSecond = Math.PI;
        public static final double kMaxAngularSpeedRadiansPerSecondSquared = Math.PI;
    
        public static final double kPXController = 1;
        public static final double kPYController = 1;
        public static final double kPThetaController = 1;
    
        /* Constraint for the motion profilied robot angle controller */
        public static final TrapezoidProfile.Constraints kThetaControllerConstraints =
            new TrapezoidProfile.Constraints(
                kMaxAngularSpeedRadiansPerSecond, kMaxAngularSpeedRadiansPerSecondSquared);
    }

    public static final class DeployIntakeConstants {
        
    public static final double deployMotorGearRatio = 39.6/1;
    public static final int ExtensionCurrentLimit = 40;
    public static final double ExtensionSpeed = 0.25;
    public static final double RetractionSpeed = -0.25;
    public static final double HighCurrentMinimumTime = 0.25;

    public static final double smartMotionMaxVelocity = 1000;
    public static final double smartmotionMaxAccel = 1000;
    public static final double smartMotionAllowableError = 0.0001;

    //NEEDS TO BE TUNED
    public static final double extensionPoint = 0;

    
    }
}
