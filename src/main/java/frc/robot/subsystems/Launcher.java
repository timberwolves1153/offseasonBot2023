package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Launcher {
    private CANSparkMax topRoller;
    private CANSparkMax bottomRoller;

    public Launcher(){
        topRoller = new CANSparkMax(61, MotorType.kBrushless);
        bottomRoller = new CANSparkMax(62, MotorType.kBrushless);
    }

    public void ScoreTop() {
        topRoller.setVoltage(6);
        bottomRoller.setVoltage(6);
    }

    public void ScoreMiddle() {
        topRoller.setVoltage(4);
        bottomRoller.setVoltage(4);
    }

    public void launcherStop() {
        topRoller.setVoltage(0);
        bottomRoller.setVoltage(0);
    }

}
