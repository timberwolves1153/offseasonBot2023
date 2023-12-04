package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Indexer {

    private CANSparkMax indexMotor1;
    private CANSparkMax indexMotor2;
    private CANSparkMax singulatorMotor;
    /* Asigning Motors */
    public Indexer(){
        indexMotor1 = new CANSparkMax(51, MotorType.kBrushless);
        indexMotor2 = new CANSparkMax(52, MotorType.kBrushless);
        singulatorMotor = new CANSparkMax(53, MotorType.kBrushless);
    }


    /* Index Motor 1 Motion Control */
    public void runIndexMotor1(){
        indexMotor1.setVoltage(6);
    }

    public void stopIndexMotor1(){
        indexMotor1.setVoltage(0);
    }

    public void reverseIndexMotor1(){
        indexMotor1.setVoltage(-6);
    }

    /* Index Motor 2 Motion Control */
    public void runIndexMotor2(){
        indexMotor2.setVoltage(6);
    }

    public void stopIndexMotor2(){
        indexMotor2.setVoltage(0);
    }

    public void reverseIndexMotor2(){
        indexMotor2.setVoltage(-6);
    }

        /* Singulator */
    public void singulatorPushIn(){
        singulatorMotor.setVoltage(6);
    }
    public void singulatorStop(){
        singulatorMotor.setVoltage(0);
    }
    public void singulatorPushout(){
        singulatorMotor.setVoltage(-6);
    }
}