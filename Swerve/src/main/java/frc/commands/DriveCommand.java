package frc.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.subsystems.DrivetrainSubsystem;
import frc.common.math.Vector2;

public class DriveCommand extends Command {
    private Vector2 translation;
    private double rotation;
    private boolean fieldOriented;

    public DriveCommand(Vector2 translation, double rotation, boolean fieldOriented) {
        this.translation = translation;
        this.rotation = rotation;
        this.fieldOriented = fieldOriented;

        requires(DrivetrainSubsystem.getInstance());

        this.setRunWhenDisabled(true);
    }

    @Override
    protected void initialize() {
        DrivetrainSubsystem.getInstance().holonomicDrive(translation, rotation, fieldOriented);
    }

    @Override
    protected void end() {
        DrivetrainSubsystem.getInstance().holonomicDrive(Vector2.ZERO, 0.0);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
