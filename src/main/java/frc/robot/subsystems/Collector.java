package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Collector {
    
    private CANSparkMax pivotMotor;
    private CANSparkMax rollerMotor;
    private RelativeEncoder pivotEncoder;
    private SparkMaxPIDController pidController;

    public double kP, kI, kD, kFF, kMaxOutput, kMinOutput, kInput, maxRPM;

    public Collector() {

        pivotMotor = new CANSparkMax(41, MotorType.kBrushless);
        rollerMotor = new CANSparkMax(42, MotorType.kBrushless);

        pivotMotor.restoreFactoryDefaults();    

        pidController = pivotMotor.getPIDController();

        pivotEncoder = pivotMotor.getEncoder();

        kP = 0.1;
        kI = 1e-4;
        kD = 1;
        kFF = 0;
        kMaxOutput = 1;
        kMinOutput = -1;

        pidController.setP(kD);
        pidController.setI(kD);
        pidController.setD(kD);
        pidController.setFF(kD);
        pidController.setOutputRange(kMinOutput, kMaxOutput);

        SmartDashboard.putNumber("P Gain", kP);
        SmartDashboard.putNumber("D Gain", kD);
        SmartDashboard.putNumber("I Gain", kI);
        SmartDashboard.putNumber("Feed Forward", kFF);
        SmartDashboard.putNumber("Minimum Output", kMinOutput);
        SmartDashboard.putNumber("Maximum Output", kMaxOutput);
        SmartDashboard.putNumber("Set Rotations", 0);
    }

    public void collectorIntake() {
        rollerMotor.setVoltage(6);
    }

    public void collectorOuttake() {
        rollerMotor.setVoltage(-6);
    }

    public void collectorStop() {
        rollerMotor.setVoltage(0);
    }

    public void deployMotorForward() {
        pivotMotor.setVoltage(6);
    }

    public void deployMotorBackward() {
        pivotMotor.setVoltage(-6);
    }

    public void deployMotorStop() {
        pivotMotor.setVoltage(0);
    }

    public void setIntakePosition(double encoderTicks) { //takes in certain number of encoder ticks
        pidController.setReference(encoderTicks, ControlType.kPosition);
    }

    public void receiveValues() {

        double p = SmartDashboard.getNumber("P Gain", 0);
        double i = SmartDashboard.getNumber("I Gain", 0);
        double d = SmartDashboard.getNumber("D Gain", 0);
        double ff = SmartDashboard.getNumber("Feed Forward", 0);
        double min = SmartDashboard.getNumber("MinOutput", 0);
        double max = SmartDashboard.getNumber("MaxOutput", 0);

        if (p != kP) {
            pidController.setP(kP = p);
        }

        if (i != kI) {
            pidController.setI(kI = i);
        }

        if (d != kD) {
            pidController.setD(kD = d);
        }

        if (ff != kFF) {
            pidController.setFF(kFF = ff);
        }

        if (min != kMinOutput) {
            pidController.setOutputRange(min, max);
            kMinOutput = min;
            kMaxOutput = max;
        }

        if  (max != kMaxOutput) {
            pidController.setOutputRange(min, max);
            kMinOutput = min;
            kMaxOutput = max;
        }
    }

    public void configCollector() {
        pivotMotor.restoreFactoryDefaults();    
        pivotMotor.setIdleMode(IdleMode.kCoast);
        pivotMotor.setInverted(false);
        pivotMotor.burnFlash();

        rollerMotor.restoreFactoryDefaults();
        rollerMotor.setIdleMode(IdleMode.kCoast);
        rollerMotor.setInverted(false);
        rollerMotor.burnFlash();
    }

}