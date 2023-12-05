package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;

public class Launcher extends Subsystem{

    private CANSparkMax topRoller;
    private CANSparkMax bottomRoller;

    private SparkMaxPIDController launcherPID;

    private RelativeEncoder launcherEncoder;

    private double kP, kI, kD, kIZ, kFF, kMinOutput, kMaxOutput, maxRPM;

    public Launcher(){

        PIDController(50., 0.001, 0);

        getController().setTolerance(0);

        topRoller = new CANSparkMax(61, MotorType.kBrushless);
        bottomRoller = new CANSparkMax(62, MotorType.kBrushless);

        launcherPID = topRoller.getPIDController();
        launcherPID = bottomRoller.getPIDController();

        launcherEncoder = topRoller.getEncoder();
        launcherEncoder = bottomRoller.getEncoder();

        kIZ = 0.0;
        kFF = 0.0;
        kMinOutput = -1.0;
        kMaxOutput = 1.0;
        maxRPM = 5700;

        launcherPID.setP(kP);
        launcherPID.setI(kI);
        launcherPID.setD(kD);
        launcherPID.setIZone(kIZ);
        launcherPID.setFF(kFF);
        launcherPID.setOutputRange(kMinOutput, kMaxOutput);
    }

    public void launch(){
        topRoller.setVoltage(6);
        bottomRoller.setVoltage(6);
    }
    public void stopLauncher(){
        topRoller.setVoltage(0);
        bottomRoller.setVoltage(0);
    }

    public void setLauncherSetpoint(double maxRPM){
        this.maxRPM = maxRPM;
        topRoller.setReference(maxRPM, CANSparkMax.ControlType.kVoltage);
        bottomRoller.setReference(maxRPM, CANSparkMax.ControlType.kVoltage);
    }

    

    public void configLauncherMotors() {
        topRoller.restoreFactoryDefaults();
        topRoller.setIdleMode(IdleMode.kCoast);
        topRoller.setInverted(true);
        topRoller.burnFlash();

        bottomRoller.restoreFactoryDefaults();
        bottomRoller.setIdleMode(IdleMode.kCoast);
        bottomRoller.setInverted(false);
        bottomRoller.burnFlash();
    }
}
