package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Indexer extends SubsystemBase {
    public CANSparkMax indexer;

    public Indexer() {
        indexer = new CANSparkMax(51, MotorType.kBrushless);

        
        indexer.setInverted(true);
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
