package frc.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.subsystems.ClimberSubsystem;

public class ExtendKickstandCommand extends InstantCommand {
    public ExtendKickstandCommand() {
        super(() -> ClimberSubsystem.getInstance().extendKickstand());
    }
}
