/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class setHoodAngle extends Command {
  double angle;
  public setHoodAngle(double angle) {
    requires(Robot.shooter);
    this.angle = angle;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.shooter.variable_hood.set(Robot.shooter.hood_angle_power(angle));
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.shooter.variable_hood.set(Robot.shooter.hood_angle_power(angle));
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Math.abs(Robot.shooter.convert_encoder_to_angle() - angle) <= 3;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.shooter.variable_hood.set(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.shooter.variable_hood.set(0);
  }
}
