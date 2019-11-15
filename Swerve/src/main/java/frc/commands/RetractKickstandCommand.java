package frc.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.subsystems.ClimberSubsystem;

public class RetractKickstandCommand extends InstantCommand {
    public RetractKickstandCommand() {
        super(() -> ClimberSubsystem.getInstance().retractKickstand());
    }
}
