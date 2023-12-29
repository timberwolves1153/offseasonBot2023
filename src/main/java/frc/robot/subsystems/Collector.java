package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Collector extends SubsystemBase{
    
    private CANSparkMax pivotMotor;
    private CANSparkMax rollerMotor;
    private RelativeEncoder pivotEncoder;
    private SparkMaxPIDController pidController;

    public double kP, kI, kD, kFF, kMaxOutput, kMinOutput, kInput, maxRPM;
    //public final double kp = 0.001;

    private final double IntakeSetpoint = -13.00;

    public Collector() {
        pivotMotor = new CANSparkMax(41, MotorType.kBrushless);
        rollerMotor = new CANSparkMax(42, MotorType.kBrushless);

        pidController = pivotMotor.getPIDController();

        pivotEncoder = pivotMotor.getEncoder();

        kP = 0.01;
        kI = 0;
        kD = 0;
        kFF = 0;
        kMaxOutput = 1;
        kMinOutput = -1;

        SmartDashboard.putNumber("Collector P Gain", kP);
        //SmartDashboard.putNumber("D Gain", kD);
        //SmartDashboard.putNumber("I Gain", kI);
        SmartDashboard.putNumber("Feed Forward", kFF);
        SmartDashboard.putNumber("Minimum Output", kMinOutput);
        SmartDashboard.putNumber("Maximum Output", kMaxOutput);

        configCollector();
    }

    public void collectorIntake() {
        rollerMotor.setVoltage(-8);
    }

    public void collectorOuttake() {
        rollerMotor.setVoltage(8);
        
    }

    public void collectorStop() {
        rollerMotor.setVoltage(0);
    }

    public void deployMotorForward() {
        pivotMotor.setVoltage(-1);
    }

    public void deployMotorBackward() {
        pivotMotor.setVoltage(1);
    }

    public void deployMotorStop() {
        pivotMotor.setVoltage(0.25);
    }

    public double getPivotEncoder() {
        return pivotEncoder.getPosition();
    }

    public void resetPivotEncoder() {
        pivotEncoder.setPosition(0);
    }

    public void deployIntake() {
        pidController.setReference(IntakeSetpoint, ControlType.kPosition);
    }

    public void retractIntake() {
        pidController.setReference(IntakeSetpoint - IntakeSetpoint, ControlType.kPosition);
    }

    @Override
    public void periodic() {

        double p = SmartDashboard.getNumber("Intake P value", 0);
       // double i = SmartDashboard.getNumber("I Gain", 0);
       // double d = SmartDashboard.getNumber("D Gain", 0);
        double ff = SmartDashboard.getNumber("Intake FF", 0);
        SmartDashboard.putNumber("intake enocder", getPivotEncoder());
        // double min = SmartDashboard.getNumber("MinOutput", 0);
        // double max = SmartDashboard.getNumber("MaxOutput", 0);

        if (p != kP) {
            pidController.setP(kP = p);
        }

        // if (i != kI) {
        //     pidController.setI(kI = i);
        // }

        // if (d != kD) {
        //     pidController.setD(kD = d);
        // }

        if (ff != kFF) {
            pidController.setFF(kFF = ff);
        }

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

    public void setPivotToCoast() {
        pivotMotor.setIdleMode(IdleMode.kCoast);
    }

    public void setPivotToBrake() {
        pivotMotor.setIdleMode(IdleMode.kBrake);
    }

    public void configCollector() {
        pivotMotor.restoreFactoryDefaults();    
        pivotMotor.setIdleMode(IdleMode.kBrake);
        pivotMotor.setInverted(false);
        pivotMotor.burnFlash();

        pidController.setP(kP);
        pidController.setI(kI);
        pidController.setD(kD);

        rollerMotor.restoreFactoryDefaults();
        rollerMotor.setIdleMode(IdleMode.kCoast);
        rollerMotor.setInverted(false);
        rollerMotor.burnFlash();
    }

}