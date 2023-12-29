package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Indexer {

    private CANSparkMax indexMotor1;
    private CANSparkMax indexMotor2;
    /* Asigning Motors */
    public Indexer(){
        //rename indexer motors when cad is done
        indexMotor1 = new CANSparkMax(51, MotorType.kBrushless);
    }


    /* Index Motor 1 Motion Control */
    public void runIndexMotor1(){
        indexMotor1.setVoltage(8);
    }

    public void stopIndexMotor1(){
        indexMotor1.setVoltage(0);
    }

    public void reverseIndexMotor1(){
        indexMotor1.setVoltage(-8);
    }

    public void indexslow(){
        indexMotor1.setVoltage(1);
    }

    public void reverseindexslow(){
        indexMotor1.setVoltage(-1);
    }


   
}
