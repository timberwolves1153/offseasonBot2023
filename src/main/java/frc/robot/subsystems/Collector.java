package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.CAN;

public class Collector {
    
    private CANSparkMax pivotMotor;
    private CANSparkMax rollerMotor;

    public Collector(){
        pivotMotor = new CANSparkMax(41/*THIS COULD CHANGE!*/, MotorType.kBrushless);
        rollerMotor = new CANSparkMax(42/*THIS COULD CHANGE!*/, MotorType.kBrushless);
    }

    public void collectorIntake(){
        rollerMotor.setVoltage(6/*Test then change*/);
    }

     public void collectorStop(){
        rollerMotor.setVoltage(0/*Test then change*/);
    }

     public void collectorOuttake(){
        rollerMotor.setVoltage(-6/*Test then change*/);
    }

    public void pivotForward(){
        pivotMotor.setVoltage(2/*Test then change*/);
    }

    public void pivotBack(){
        pivotMotor.setVoltage(-2/*Test then change*/);
    }

    public void pivotStop(){
        pivotMotor.setVoltage(0/*Test then change*/);
    }




     


}
