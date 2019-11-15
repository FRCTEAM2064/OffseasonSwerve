package frc.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.subsystems.HatchPlacerSubsystem;

public class ReleaseHatchPanelCommand extends InstantCommand {
    public ReleaseHatchPanelCommand() {
        super(() -> HatchPlacerSubsystem.getInstance().release());

        this.setRunWhenDisabled(true);
    }
}
