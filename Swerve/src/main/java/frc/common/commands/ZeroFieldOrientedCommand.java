package frc.common.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.common.subsystems.HolonomicDrivetrain;

public class ZeroFieldOrientedCommand extends Command {
    private final HolonomicDrivetrain drivetrain;

    public ZeroFieldOrientedCommand(HolonomicDrivetrain drivetrain) {
        this.drivetrain = drivetrain;
    }

    @Override
    protected void initialize() {
        drivetrain.getGyroscope().setAdjustmentAngle(drivetrain.getGyroscope().getUnadjustedAngle());
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}
