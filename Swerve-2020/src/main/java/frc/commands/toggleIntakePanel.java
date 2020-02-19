/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class toggleIntakePanel extends Command {
  public toggleIntakePanel() {
    requires(Robot.intake);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    if(Robot.intake.isDown) {
      // Robot.intake.intakePiston.set(DoubleSolenoid.Value.kReverse);
      Robot.intake.deactivateMotors();
    }
    else{
      // Robot.intake.intakePiston.set(DoubleSolenoid.Value.kForward);
      Robot.intake.activateMotors();
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Robot.intake.isDown) Robot.intake.activateMotors();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    if (Robot.intake.isDown) Robot.intake.isDown = false;
    else Robot.intake.isDown = true;
    Robot.intake.deactivateMotors();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.intake.deactivateMotors();
  }
}
