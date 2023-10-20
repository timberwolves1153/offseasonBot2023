package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Indexer {
    public CANSparkMax indexer;

    public Indexer() {
        indexer = new CANSparkMax(51, MotorType.kBrushless);

        
    }
    public void indexerCollect () {
        indexer.setVoltage(6);
    }

    public void indexerReject () {
        indexer.setVoltage(-6);
    }

    public void indexerStop () {
        indexer.setVoltage(0);
    }
}
