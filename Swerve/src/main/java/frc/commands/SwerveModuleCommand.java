package frc.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.drive.SwerveDriveModule;

public class SwerveModuleCommand extends Command {

	private SwerveDriveModule mDriveModule;

	public SwerveModuleCommand(SwerveDriveModule driveModule) {
		mDriveModule = driveModule;

		requires(driveModule);
	}

	@Override
	protected void execute() {
		// TODO: Handle smart swerve drive angling.
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}
