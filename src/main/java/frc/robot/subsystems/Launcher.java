package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Launcher extends SubsystemBase{
    private CANSparkMax topRoller;
    private CANSparkMax bottomRoller;

    public Launcher(){
        topRoller = new CANSparkMax(61, MotorType.kBrushless);
        bottomRoller = new CANSparkMax(62, MotorType.kBrushless);


    }
// NEED TO TUNE ALL SHOOTING METHODS
    public void scoreL2() {
        topRoller.setVoltage(4);
        bottomRoller.setVoltage(4);
    }

    public void scoreL3() {
        topRoller.setVoltage(6);
        bottomRoller.setVoltage(6);
    }


    public void scoreFromCS() {
        topRoller.setVoltage(10);
        bottomRoller.setVoltage(10);
    }

    public void launcherStop() {
        topRoller.setVoltage(0);
        bottomRoller.setVoltage(0);
    }

    public void configLauncherMotors() {
        topRoller.restoreFactoryDefaults();
        topRoller.setIdleMode(IdleMode.kCoast);
        topRoller.setInverted(true);
        topRoller.burnFlash();

        bottomRoller.restoreFactoryDefaults();
        bottomRoller.setIdleMode(IdleMode.kCoast);
        bottomRoller.setInverted(false);
        bottomRoller.burnFlash();
    }

}
