package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;

public class Collector {
    
    private CANSparkMax pivotMotor;
    private CANSparkMax rollerMotor;
    private RelativeEncoder pivotEncoder;
    private SparkMaxPIDController pidController;

    public double kP, kI, kD, kIz, kFF, kMaxOutput, kInput, maxRPM;

    public Collector() {

        pivotMotor = new CANSparkMax(41, MotorType.kBrushless);
        rollerMotor = new CANSparkMax(42, MotorType.kBrushless);

      //  pivotEncoder = 
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

    

}
