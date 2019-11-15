package frc.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.subsystems.ClimberSubsystem;

public class SetClimberExtendedCommand extends InstantCommand {
    private final boolean extended;

    public SetClimberExtendedCommand(boolean extended) {
        this.extended = extended;

        requires(ClimberSubsystem.getInstance());
    }


    @Override
    protected void initialize() {
        ClimberSubsystem.getInstance().setClimberExtended(extended);
    }
}
