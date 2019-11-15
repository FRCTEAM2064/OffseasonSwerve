package frc.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.subsystems.HatchFloorGathererSubsystem;

public class SetHatchFloorGathererAngleCommand extends Command {
    private double angle;

    public SetHatchFloorGathererAngleCommand(double angle) {
        this.angle = angle;

        requires(HatchFloorGathererSubsystem.getInstance());
    }

    @Override
    protected void initialize() {
        HatchFloorGathererSubsystem.getInstance().setTargetAngle(angle);
    }

    @Override
    protected boolean isFinished() {
        return HatchFloorGathererSubsystem.getInstance().isAtTargetAngle();
    }
}
