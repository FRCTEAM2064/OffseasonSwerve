package frc.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.subsystems.HatchPlacerSubsystem;

public class GrabHatchPanelCommand extends InstantCommand {
    public GrabHatchPanelCommand() {
        super(() -> HatchPlacerSubsystem.getInstance().grab());

        this.setRunWhenDisabled(true);
    }
}
