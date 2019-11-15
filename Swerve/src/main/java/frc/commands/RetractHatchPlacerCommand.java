package frc.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.subsystems.HatchPlacerSubsystem;

public class RetractHatchPlacerCommand extends InstantCommand {
	public RetractHatchPlacerCommand() {
		this.setRunWhenDisabled(true);
	}

	@Override
	protected void initialize() {
		HatchPlacerSubsystem.getInstance().retract();
	}
}
