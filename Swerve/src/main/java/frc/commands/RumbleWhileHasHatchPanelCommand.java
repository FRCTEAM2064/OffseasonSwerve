package frc.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.subsystems.HatchPlacerSubsystem;

public class RumbleWhileHasHatchPanelCommand extends Command {

    @Override
    protected void execute() {
        if(HatchPlacerSubsystem.getInstance().getLeftLimitSwitch() && HatchPlacerSubsystem.getInstance().getRightLimitSwitch()) {
            Robot.getOi().primaryController.getRawJoystick().setRumble(GenericHID.RumbleType.kRightRumble, 1.0);
            Robot.getOi().secondaryController.getRawJoystick().setRumble(GenericHID.RumbleType.kRightRumble, 1.0);
        } else {
            Robot.getOi().primaryController.getRawJoystick().setRumble(GenericHID.RumbleType.kRightRumble, 0.0);
            Robot.getOi().secondaryController.getRawJoystick().setRumble(GenericHID.RumbleType.kRightRumble, 0.0);
        }
    }

    @Override
    protected void end() {
        Robot.getOi().primaryController.getRawJoystick().setRumble(GenericHID.RumbleType.kRightRumble, 0.0);
        Robot.getOi().secondaryController.getRawJoystick().setRumble(GenericHID.RumbleType.kRightRumble, 0.0);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
