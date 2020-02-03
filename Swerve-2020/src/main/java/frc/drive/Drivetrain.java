package frc.drive;

import edu.wpi.first.wpilibj2.command.Subsystem;

public abstract class Drivetrain implements Subsystem {
	private double speedMultiplier = 0.7;

	protected abstract void initDefaultCommand();

	public double getSpeedMultiplier() {
		return speedMultiplier;
	}

	public void setSpeedMultiplier(double speedMultiplier) {
		this.speedMultiplier = speedMultiplier;
	}
}
