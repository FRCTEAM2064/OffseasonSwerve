package frc.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.ConditionalCommand;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.subsystems.CargoArmSubsystem;
import frc.common.math.Vector2;

public class ClimbCommand extends CommandGroup {
    private static final double DRIVE_SPEED = 0.25;
    private static final double INTAKE_SPEED = 0.75;
    private static final double TARGET_PITCH = Math.toRadians(-5.0);

    public ClimbCommand() {
        addParallel(new SetClimberExtendedCommand(true));
        addParallel(new DriveCommand(new Vector2(DRIVE_SPEED, 0.0), 0.0, false));
        addParallel(new SetBottomCargoRollerSpeedCommand(INTAKE_SPEED));
        addParallel(new ExtendKickstandCommand());
        addParallel(new CorrectPitchCommand(TARGET_PITCH, true));
//        addSequential(new SetArmAngleCommand(CargoArmSubsystem.CARGO_SHIP_SCORE_ANGLE));
    }
}
