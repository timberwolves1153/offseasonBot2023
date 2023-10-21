package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.CAN;

public class Collector {
    
    private CANSparkMax rollerMotor;
    private CANSparkMax deployMotor;

    public Collector () {
        rollerMotor = new CANSparkMax(41, MotorType.kBrushless);
        deployMotor = new CANSparkMax(42, MotorType.kBrushless);
    }

    public void collectorIntake() {
        rollerMotor.setVoltage(6);
    }

    public void collectorOutake() {
        rollerMotor.setVoltage(-6);
    }

    public void collectorStop() {
        rollerMotor.setVoltage(0.0);
    }

    // public void initialize {
    // deployMotor.setSmartCurrentLimit(currentLimitExtend);
    // }
}
