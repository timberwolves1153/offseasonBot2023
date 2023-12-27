package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;

public class Limelight extends PIDSubsystem{
    
    private NetworkTable table;
	
	private Target target;

	private final double targetFixedHeightInches;
	private final double limelightFixedAngleDegrees;
	private final double limelightFixedHeightInches;

    public Limelight() {

        super(new PIDController(0.045, 0.008, 0.0075));
    	table = NetworkTableInstance.getDefault().getTable("limelight");
        setPipeline(1);

        targetFixedHeightInches = 0;
		limelightFixedAngleDegrees = 0;
		limelightFixedHeightInches = 0;

		target = new Target();
    }

    public void updateLimelightData() {
		NetworkTableEntry tv = table.getEntry("tv");
		boolean targetExists = tv.getDouble(0) > 0.0;
	//	System.out.println("Tv:" + tv.getDouble(0));
		
		if (!targetExists) {
			disable();
			target.setTargetExistance(false);
			return;
		} else {
			target.setTargetExistance(true);
		}
		
		NetworkTableEntry tx = table.getEntry("tx");
		NetworkTableEntry ty = table.getEntry("ty");
		NetworkTableEntry ta = table.getEntry("ta");



		target.x = tx.getDouble(0);
		target.y = ty.getDouble(0);
		target.a = ta.getDouble(0);
		
		if (!isEnabled()) enable();
	}

    public void updateShuffleBoard() {
		updateLimelightData();
		SmartDashboard.putBoolean("Has Target", target != null);
		SmartDashboard.putNumber("Target Distance", calcDistance());
		if (target != null) {
			SmartDashboard.putNumber("Target X", target.x);
			SmartDashboard.putNumber("Target Y", target.y);
			SmartDashboard.putNumber("Target A", target.a);
		}
		//System.out.println("Target a:" + ta);
	}

    public double calcDistance() {
		if(!target.exists()) return -1;

		double targetOffsetAngleVertical = target.y;

		double angleToGoalDegrees = limelightFixedAngleDegrees + targetOffsetAngleVertical;
		double angleToGoalRadians = angleToGoalDegrees * (Math.PI / 180);

		double distanceInches = (targetFixedHeightInches - limelightFixedHeightInches) / Math.tan(angleToGoalRadians);
		
		System.out.println(distanceInches);
		return distanceInches;
	}

    public Target getTargetValues() {
    	return target;
    }

	public boolean targetExists() {
		return target.exists();
	}
	
	public boolean getTargetV() {
		NetworkTableEntry tv = table.getEntry("tv");
		System.out.println(tv.getDouble(0));
		if (tv.getDouble(0) > 0.0) {
			return true;
		} else {
			return false;
		}
	}


    public static class Target {
    	public double x;
    	public double y;
		public double a;
		
		public double x0;
    	public double y0;
    	public double a0;

		private boolean exists = false;

		public boolean exists() {
			return exists;
		}

		public void setTargetExistance(boolean exists) {
			this.exists = exists;
		}
    }

    public void flushNetworkTables() {
		NetworkTableInstance.getDefault().flush();
	}
    
    public void setPipeline(int pipeline) {
		table = NetworkTableInstance.getDefault().getTable("limelight");
		NetworkTableEntry pipelineEntry = table.getEntry("pipeline");
		pipelineEntry.setNumber(pipeline);
		pipelineEntry.setDefaultNumber(pipeline);
	}

    @Override
	protected void useOutput(double output, double setpoint) {
		
	}

	@Override
	protected double getMeasurement() {
		return target.x;
	}

	@Override
	public void periodic() {
		super.periodic();

		NetworkTableEntry pipelineEntry = table.getEntry("pipeline");

		if(pipelineEntry.getDouble(0) != 1) {
			setPipeline(1);
		}
	}

}
