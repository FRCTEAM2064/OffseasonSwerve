package frc.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.subsystems.DrivetrainSubsystem;
import frc.common.drivers.SwerveModule;

public class WaitForFrontWheelsToExceedCurrentCommand extends Command {
    private final double current;

    public WaitForFrontWheelsToExceedCurrentCommand(double current) {
        this.current = current;
    }

    @Override
    protected boolean isFinished() {
        for (SwerveModule module : DrivetrainSubsystem.getInstance().getSwerveModules()) {
            if (module.getModulePosition().y > 0.0) {
                // Is front module
                if (!(module.getDriveCurrent() > current)) {
                    // Current does not exceed threshold
                    return false;
                }
            }
        }

        return true;
    }
}
