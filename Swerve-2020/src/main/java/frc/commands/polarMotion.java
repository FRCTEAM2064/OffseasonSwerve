/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class polarMotion extends Command {
  double meters;
  double angle;
  double initialEnc;
  /**
   * @param meters - How far to move in meters
   * @param angle - What angle to move in, assuming positive x axis is 0 degrees
   */
  public polarMotion(double meters, double angle) {
    this.meters = meters;
    this.angle = angle;
    requires(Robot.drive);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    initialEnc = Robot.drive.trueFRDEnc;
    Robot.drive.holonomicDrive(meters * -Math.sin(Math.toRadians(angle)), meters * -Math.cos(Math.toRadians(angle)), 0, true);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.drive.holonomicDrive(meters * -Math.sin(Math.toRadians(angle)), meters * -Math.cos(Math.toRadians(angle)), 0, true);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Math.abs(Robot.drive.trueFRDEnc - initialEnc) >= (4096 * meters/RobotMap.circumference_of_wheel);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.drive.stopAllMotors();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
