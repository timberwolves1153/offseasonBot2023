package frc.lib.util;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import edu.wpi.first.wpilibj.AnalogInput;
import frc.lib.util.CANSparkMaxUtil.Usage;

public class CANSparkMaxUtil {
    public enum Usage {
        kAll,
        kPositionOnly,
        kVelocityOnly,
        kMinimal;
    }

    public static final Usage kMinimal = null;

public static void setCANSparkMaxBusUsage(
    CANSparkMax motor, Usage usage, boolean enableFollowing) {
    if (enableFollowing) {
        motor.setPeriodicFramePeriod(CANSparkMaxLowLevel.PeriodicFrame.kStatus0, 10);
    } else {
        motor.setPeriodicFramePeriod(CANSparkMaxLowLevel.PeriodicFrame.kStatus0, 500);
    }

    if (usage == Usage.kAll) {
        motor.setPeriodicFramePeriod(CANSparkMaxLowLevel.PeriodicFrame.kStatus1, 20);
        motor.setPeriodicFramePeriod(CANSparkMaxLowLevel.PeriodicFrame.kStatus2, 20);
        motor.setPeriodicFramePeriod(CANSparkMaxLowLevel.PeriodicFrame.kStatus3, 50);
    } else if (usage == Usage.kPositionOnly) {
        motor.setPeriodicFramePeriod(CANSparkMaxLowLevel.PeriodicFrame.kStatus1, 500);
        motor.setPeriodicFramePeriod(CANSparkMaxLowLevel.PeriodicFrame.kStatus2, 20);
        motor.setPeriodicFramePeriod(CANSparkMaxLowLevel.PeriodicFrame.kStatus3, 500);
    }

    if (usage == Usage.kVelocityOnly) {
        motor.setPeriodicFramePeriod(CANSparkMaxLowLevel.PeriodicFrame.kStatus1, 20);
        motor.setPeriodicFramePeriod(CANSparkMaxLowLevel.PeriodicFrame.kStatus2, 500);
        motor.setPeriodicFramePeriod(CANSparkMaxLowLevel.PeriodicFrame.kStatus3, 500);
    }

    if (usage == Usage.kMinimal) {
        motor.setPeriodicFramePeriod(CANSparkMaxLowLevel.PeriodicFrame.kStatus1, 500);
        motor.setPeriodicFramePeriod(CANSparkMaxLowLevel.PeriodicFrame.kStatus2, 500);
        motor.setPeriodicFramePeriod(CANSparkMaxLowLevel.PeriodicFrame.kStatus3, 500);
    }

    }

    public static void setCANSparkMaxBusUsage(CANSparkMax angleMotor, Usage usage){
        setCANSparkMaxBusUsage(angleMotor, usage, false);

    }

}