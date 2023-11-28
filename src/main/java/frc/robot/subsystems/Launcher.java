package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;

public class Launcher extends PIDSubsystem{

    private CANSparkMax topRoller;
    private CANSparkMax bottomRoller;

    public Launcher(){

        super(new PIDController(0.5, 0.001, 0));

        getController().setTolerance(0);

        topRoller = new CANSparkMax(61, MotorType.kBrushless);
        bottomRoller = new CANSparkMax(62, MotorType.kBrushless);
    }

    public void launcher(){
        topRoller.setVoltage(6);
        bottomRoller.setVoltage(6);

    }
    public void stopLauncher(){
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

    @Override
    protected void useOutput(double output, double setpoint) {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected double getMeasurement() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMeasurement'");
    }



    
}
