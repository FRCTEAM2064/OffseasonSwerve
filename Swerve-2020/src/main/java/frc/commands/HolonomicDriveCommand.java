package frc.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.drive.HolonomicDrivetrain;

public class HolonomicDriveCommand extends Command {
	private final HolonomicDrivetrain mDrivetrain;
	private final boolean iFO;

	public HolonomicDriveCommand(HolonomicDrivetrain drivetrain, boolean isFieldOriented) {
		mDrivetrain = drivetrain;
		iFO = isFieldOriented;
		// requires(drivetrain);
	}

	private double deadband(double input) {
		if (Math.abs(input) < 0.05) return 0;
		return input;
	}

	@Override
	protected void execute() {
		double forward = -OI.getlYval();
		double strafe = OI.getlXval();
		double rotation = OI.getrXval();

		if (OI.quickRotLeft()) {
			rotation = -0.6;
		} else if (OI.quickRotRight()) {
			rotation = 0.6;
		}

		forward = deadband(forward);
		strafe = deadband(strafe);
		rotation = deadband(rotation);

		SmartDashboard.putNumber("Forward", forward);
		SmartDashboard.putNumber("Strafe", strafe);
		SmartDashboard.putNumber("Rotation", rotation);

		mDrivetrain.holonomicDrive(forward, strafe, rotation, iFO);
	}

	@Override
	protected void end() {
		mDrivetrain.stopDriveMotors();
	}

	@Override
	protected void interrupted() {
		end();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}
