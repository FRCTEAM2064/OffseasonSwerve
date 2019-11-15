package frc.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.subsystems.HatchPlacerSubsystem;

public class ExtendHatchPlacerCommand extends InstantCommand {
	public ExtendHatchPlacerCommand() {
		this.setRunWhenDisabled(true);
	}

	@Override
	protected void initialize() {
		HatchPlacerSubsystem.getInstance().extend();
	}
}
