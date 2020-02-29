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
  double initialFREnc;
  
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
    initialFREnc = Robot.drive.mSwerveModules[0].getDriveEncoderVal();
    // System.out.println("Command initialized");
    Robot.drive.holonomicDrive(Math.sin(Math.toRadians(angle))/4, Math.cos(Math.toRadians(angle))/4, 0, true);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // System.out.println("Command executing");
    System.out.println(Robot.drive.mSwerveModules[0].getDriveEncoderVal());
    Robot.drive.holonomicDrive(Math.sin(Math.toRadians(angle))/4, Math.cos(Math.toRadians(angle))/4, 0, true);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Math.abs(Robot.drive.mSwerveModules[0].getDriveEncoderVal() - initialFREnc) >= RobotMap.actualDistanceMultiplierHardWood
        * (1024 * meters / RobotMap.circumference_of_wheel);
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
    Robot.drive.stopAllMotors();
  }
}
