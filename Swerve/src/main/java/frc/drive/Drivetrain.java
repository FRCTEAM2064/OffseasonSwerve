package frc.drive;

import edu.wpi.first.wpilibj.command.Subsystem;

public abstract class Drivetrain extends Subsystem {
	private double speedMultiplier = 0.7;

	protected abstract void initDefaultCommand();

	public double getSpeedMultiplier() {
		return speedMultiplier;
	}

	public void setSpeedMultiplier(double speedMultiplier) {
		this.speedMultiplier = speedMultiplier;
	}
}
