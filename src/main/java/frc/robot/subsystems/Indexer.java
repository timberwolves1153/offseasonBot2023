package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Indexer {

    private CANSparkMax indexMotor1;
    private CANSparkMax indexMotor2;

    /* Asigning Motors */
    public Indexer(){
        indexMotor1 = new CANSparkMax(51, MotorType.kBrushless);
        indexMotor2 = new CANSparkMax(52, MotorType.kBrushless);
    }


    /* Index Motor 1 Motion Control */
    public void runindexMotor1(){
        indexMotor1.setVoltage(6);
    }

    public void stopindexMotor1(){
        indexMotor1.setVoltage(0);
    }

    public void reverseindexMotor1(){
        indexMotor1.setVoltage(-6);
    }

    /* Index Motor 2 Motion Control */
    public void runindexMotor2(){
        indexMotor2.setVoltage(6);
    }

    public void stopindexMotor2(){
        indexMotor2.setVoltage(0);
    }

    public void reveseindexMotor2(){
        indexMotor2.setVoltage(-6);
    }
}