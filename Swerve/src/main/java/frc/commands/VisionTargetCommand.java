package frc.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.subsystems.DrivetrainSubsystem;
import frc.subsystems.VisionSubsystem;
import frc.api.Gamepiece;
import frc.common.control.PidConstants;
import frc.common.control.PidController;
import frc.common.math.Rotation2;
import frc.common.math.Vector2;
import frc.common.drivers.Limelight;

public class VisionTargetCommand extends Command {
    private final PidController controller = new PidController(new PidConstants(0.9, 0.0, 1.0));

    private final Limelight limelight;
    private final boolean flipRobotOriented;

    private double lastTime;

    public VisionTargetCommand(Gamepiece gamepiece) {
        limelight = VisionSubsystem.getInstance().getLimelight(gamepiece);
        flipRobotOriented = gamepiece == Gamepiece.HATCH_PANEL;

        requires(DrivetrainSubsystem.getInstance());
    }

    @Override
    protected void initialize() {
        lastTime = Timer.getFPGATimestamp();

        limelight.setCamMode(Limelight.CamMode.VISION);
    }

    @Override
    protected void execute() {
        double time = Timer.getFPGATimestamp();
        double dt = time - lastTime;

        double scalar = 0.15;
        if (Robot.getOi().secondaryController.getRightTriggerAxis().get() > 0.1) {
            scalar = 0.3;
        }

        double forward = Robot.getOi().primaryController.getLeftYAxis().get(true) * scalar;
        double strafe;
        if (limelight.hasTarget() && Math.abs(Robot.getOi().primaryController.getLeftXAxis().get(false)) < 0.75) {
            strafe = controller.calculate(limelight.getTargetPosition().x, dt);
        } else {
            strafe = Robot.getOi().primaryController.getLeftXAxis().get(true) * scalar;
        }
        double rotation = Robot.getOi().primaryController.getRightXAxis().get(true);
        if (Robot.getOi().secondaryController.getRightTriggerAxis().get() > 0.1) {
            rotation = 0.0;
            DrivetrainSubsystem.getInstance().setSnapRotation(0.0);
        }

        Vector2 translation = new Vector2(forward, strafe);
        if (flipRobotOriented) {
            translation = translation.rotateBy(Rotation2.fromDegrees(180.0));
        }

        DrivetrainSubsystem.getInstance().holonomicDrive(translation, rotation, false);
    }

    @Override
    protected void end() {
        limelight.setCamMode(Limelight.CamMode.DRIVER);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
