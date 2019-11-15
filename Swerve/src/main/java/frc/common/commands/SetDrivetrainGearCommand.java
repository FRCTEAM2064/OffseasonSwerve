package frc.common.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.common.subsystems.ShiftingTankDrivetrain;

public class SetDrivetrainGearCommand extends Command {
	private final ShiftingTankDrivetrain drivetrain;
	private final boolean highGear;

	public SetDrivetrainGearCommand(ShiftingTankDrivetrain drivetrain, boolean highGear) {
		this.drivetrain = drivetrain;
		this.highGear = highGear;
	}

	@Override
	protected void initialize() {
		drivetrain.setHighGear(highGear);
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
}
