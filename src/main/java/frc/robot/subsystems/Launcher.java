package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Launcher extends SubsystemBase{

    private CANSparkMax topRoller;
    private CANSparkMax bottomRoller;

    private SparkMaxPIDController topLauncherPID;
    private SparkMaxPIDController bottomLauncherPID;


    private RelativeEncoder topLauncherEncoder;
    private RelativeEncoder bottomLauncherEncoder;

    private double kP, kI, kD, kIZ, kFF, kMinOutput, kMaxOutput, maxRPM;
    private double topKP, bottomKP;

    public Launcher(){

        topRoller = new CANSparkMax(61, MotorType.kBrushless);
        bottomRoller = new CANSparkMax(62, MotorType.kBrushless);

        topLauncherPID = topRoller.getPIDController();
        bottomLauncherPID = bottomRoller.getPIDController();

        topLauncherEncoder = topRoller.getEncoder();
        bottomLauncherEncoder = bottomRoller.getEncoder();

        topKP = 0.01;
        bottomKP = 0.01;

        kIZ = 0.0;
        kFF = 0.0;
        kMinOutput = -1.0;
        kMaxOutput = 1.0;
        maxRPM = 5700;

        topLauncherPID.setP(topKP);
        topLauncherPID.setI(0);
        topLauncherPID.setD(0);
        topLauncherPID.setIZone(kIZ);
        topLauncherPID.setFF(kFF);
        topLauncherPID.setOutputRange(kMinOutput, kMaxOutput);

        bottomLauncherPID.setP(bottomKP);
        bottomLauncherPID.setI(0);
        bottomLauncherPID.setD(0);
        bottomLauncherPID.setIZone(kIZ);
        bottomLauncherPID.setFF(kFF);
        bottomLauncherPID.setOutputRange(kMinOutput, kMaxOutput);

        configLauncherMotors();

        SmartDashboard.putNumber("top P Gain", topKP);
        SmartDashboard.putNumber("bottom P Gain", bottomKP);
    }

    public void runLauncher(){
        topRoller.setVoltage(-4);
        bottomRoller.setVoltage(10);
    }
    public void stopLauncher(){
        topRoller.setVoltage(0);
        bottomRoller.setVoltage(0);
    }

    /**
   *
   * @param topSetpoint RPM setpoint for top roller
   * @param botttomSetpoint RPM setpoint for bottom roller
   */
    public void setLauncherSetpoint(double topSetpoint, double bottomSetpoint){
       
        topLauncherPID.setReference(topSetpoint, CANSparkMax.ControlType.kVelocity);
        bottomLauncherPID.setReference(bottomSetpoint, CANSparkMax.ControlType.kVelocity);
    }

    public double getTopRPM() {
        return topLauncherEncoder.getVelocity();
    }

    
    public double getBottomRPM() {
        return bottomLauncherEncoder.getVelocity();
    }

    

    public void configLauncherMotors() {
        topRoller.restoreFactoryDefaults();
        topRoller.setIdleMode(IdleMode.kCoast);
        topRoller.setInverted(false);
        topRoller.burnFlash();

        bottomRoller.restoreFactoryDefaults();
        bottomRoller.setIdleMode(IdleMode.kCoast);
        bottomRoller.setInverted(false);
        bottomRoller.burnFlash();
    }

    @Override
    public void periodic() {

        double topP = SmartDashboard.getNumber("top P Gain", topKP);
        double bottomP = SmartDashboard.getNumber("bottom P gain", bottomKP);

        SmartDashboard.putNumber("top RPM", getTopRPM());
        SmartDashboard.putNumber("bottom RPM", getBottomRPM());

        // double i = SmartDashboard.getNumber("I Gain", 0);
        // double d = SmartDashboard.getNumber("D Gain", 0);
        // double topFF = SmartDashboard.getNumber(" top FF", top);
        // double bottomFF = SmartDashboard.getNumber("bottom FF", )
        // double min = SmartDashboard.getNumber("MinOutput", 0);
        // double max = SmartDashboard.getNumber("MaxOutput", 0);

        if (topP != topKP) {
            topLauncherPID.setP(topKP = topP);
        }

        if (bottomP != bottomKP) {
            bottomLauncherPID.setP(bottomKP = bottomP);
        }

        // if (i != kI) {
        //     pidController.setI(kI = i);
        // }

        // if (d != kD) {
        //     pidController.setD(kD = d);
        // }

        // if (ff != kFF) {
        //     pidController.setFF(kFF = ff);
        // }

        // if (min != kMinOutput) {
        //     pidController.setOutputRange(min, max);
        //     kMinOutput = min;
        //     kMaxOutput = max;
        // }

        // if  (max != kMaxOutput) {
        //     pidController.setOutputRange(min, max);
        //     kMinOutput = min;
        //     kMaxOutput = max;
        // }
    }
}
