package frc.drive;

public abstract class HolonomicDrivetrain extends Drivetrain {

	private double mAdjustmentAngle = 0;
	private boolean mFieldOriented = false;


	public double getAdjustmentAngle() {
		return mAdjustmentAngle;
	}

	public abstract double getGyroAngle();

	@Override
	protected void initDefaultCommand() {
		// setDefaultCommand(new HolonomicDriveCommand(this, false));
	}

	public boolean isFieldOriented() {
		return mFieldOriented;
	}

	private void setAdjustmentAngle(double adjustmentAngle) {
		mAdjustmentAngle = adjustmentAngle;
	}

	public void setFieldOriented(boolean fieldOriented) {
		mFieldOriented = fieldOriented;
	}

	public abstract void stopAllMotors();

	public void zeroGyro() {
		setAdjustmentAngle(getGyroAngle() + getAdjustmentAngle());
	}
	//one with the robot
	//one with the program
}
