package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.filter.LinearFilter;
import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.DeployIntakeConstants;

public class Collector extends SubsystemBase{
    
    private CANSparkMax rollerMotor;
    private CANSparkMax deployMotor;
    private LinearFilter currentFilter;


    public Collector () {
        rollerMotor = new CANSparkMax(41, MotorType.kBrushless);
        deployMotor = new CANSparkMax(42, MotorType.kBrushless);
        

        currentFilter = LinearFilter.movingAverage(10);

        
        configDeployMotor();
        configRollerMotor();
    }

    public double getUnfilteredDeployMotorCurrent() {
        return deployMotor.getOutputCurrent();
    }

    public double getFilteredDeployMotorCurrent() {
        return currentFilter.calculate(getUnfilteredDeployMotorCurrent());
    }

    public double getDeployEncoderPosition() {
        return deployMotor.getEncoder().getPosition();
    }

    //runs rollers to collect cubes
    public void collectorIntake() {
        rollerMotor.setVoltage(6);
    }


    //runs rollers to spit out cubes
    public void collectorOutake() {
        rollerMotor.setVoltage(-6);
    }
    //stops rollers from moving
    public void collectorStop() {
        rollerMotor.setVoltage(0.0);
    }

    //purpose: to test directionality of deploy motor & see intake move w/ basic controls
    public void runIntakeDeployForward() {
        deployMotor.setVoltage(10);//rollerMotor => deployMotor
    }

    //purpose: to test directionality of deploy motor & see intake move w/ basic controls
    public void runIntakeDeployBackward() {
        deployMotor.setVoltage(-2);//rollerMotor => deployMotor
    }

    //purpose: to test directionality of deploy motor & see intake move w/ basic controls
    public void stopIntake() {
        deployMotor.setVoltage(0.0);//rollerMotor => deployMotor
    }

    public void setDeployMotorPosition(double position) {
        deployMotor.getPIDController().setReference(position, ControlType.kSmartMotion);
    }

    public void deployIntake() {
        // NEED to find Garth's extension point - HINT: it is not the current value
        deployMotor.getPIDController().setReference(DeployIntakeConstants.extensionPoint, ControlType.kSmartMotion);
    }

    public void retractIntake() {
        // NEED to find Garth's extension point - HINT: it is not the current value
        this.setDeployMotorPosition(getDeployEncoderPosition() - DeployIntakeConstants.extensionPoint);
    }

    public void zeroDeployMotor() {
        deployMotor.getEncoder().setPosition(0);
    }

    @Override 
    public void periodic() {

        if (Constants.collectorTuningMode) {
            SmartDashboard.putNumber("Deploy Motor Position", getDeployEncoderPosition());
            SmartDashboard.putNumber("Deploy Motor unfiltered Current", getUnfilteredDeployMotorCurrent());
            SmartDashboard.putNumber("Deploy Motor Filtered Current", getFilteredDeployMotorCurrent());
            }
    }

    public void configDeployMotor() {
        deployMotor.restoreFactoryDefaults();
        deployMotor.setIdleMode(IdleMode.kCoast);
        deployMotor.setInverted(false);
        deployMotor.setSmartCurrentLimit(DeployIntakeConstants.ExtensionCurrentLimit);
        
        deployMotor.getPIDController().setSmartMotionMaxVelocity(
            DeployIntakeConstants.smartMotionMaxVelocity, 0);

        deployMotor.getPIDController().setSmartMotionMaxAccel(
            DeployIntakeConstants.smartmotionMaxAccel, 0);

        deployMotor.getPIDController().setSmartMotionAllowedClosedLoopError(
            DeployIntakeConstants.smartMotionAllowableError, 0);

        deployMotor.getPIDController().setSmartMotionMinOutputVelocity(0, 0);

        // 2713 had a p-gain of 0, why?
        deployMotor.getPIDController().setP(0);
        deployMotor.getPIDController().setFF(0.02);

        deployMotor.getEncoder().setPositionConversionFactor(DeployIntakeConstants.deployMotorGearRatio);
        deployMotor.getEncoder().setVelocityConversionFactor(DeployIntakeConstants.deployMotorGearRatio);

        deployMotor.burnFlash();
    }

    public void configRollerMotor() {
        rollerMotor.restoreFactoryDefaults();
        rollerMotor.setIdleMode(IdleMode.kCoast);
        rollerMotor.setInverted(true);
        rollerMotor.burnFlash();
    }
}
