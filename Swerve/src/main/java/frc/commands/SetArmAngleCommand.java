package frc.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.subsystems.CargoArmSubsystem;

public class SetArmAngleCommand extends Command {
	private double angle;

	public SetArmAngleCommand(double angle) {
		this.angle = angle;
		requires(CargoArmSubsystem.getInstance());

		this.setRunWhenDisabled(true);
	}

	@Override
	protected void initialize() {
		CargoArmSubsystem.getInstance().setTargetAngle(angle);
	}

	@Override
	protected boolean isFinished() {
		return CargoArmSubsystem.getInstance().isWithinTargetAngleRange(angle);
	}
}
