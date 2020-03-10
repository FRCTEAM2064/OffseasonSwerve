/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

public class rotateToAngleGyro extends Command {
  public double setpoint;
  public boolean auto = false;
  public rotateToAngleGyro(double setpoint, boolean auto) {
    requires(Robot.drive);
    this.setpoint = setpoint;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.drive.holonomicDrive(OI.getlYval(), OI.getlXval(), Robot.drive.rotationAngleController.calculate(Robot.drive.getGyroAngle(), setpoint), true);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    System.out.println(Robot.drive.getGyroAngle());
    if (Robot.drive.rotationAngleController.calculate(Robot.drive.getGyroAngle(), setpoint) > 0.5){
      Robot.drive.holonomicDrive(OI.getlYval(), OI.getlXval(), 0.5, true);
    }
    else if (Robot.drive.rotationAngleController.calculate(Robot.drive.getGyroAngle(), setpoint) < -0.5){
      Robot.drive.holonomicDrive(OI.getlYval(), OI.getlXval(), -0.5, true);
    }
    else Robot.drive.holonomicDrive(OI.getlYval(), OI.getlXval(), Robot.drive.rotationAngleController.calculate(Robot.drive.getGyroAngle(), setpoint), true);
  
    System.out.println(Robot.drive.getGyroAngle());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if (auto) return Math.abs(Robot.drive.getGyroAngle() - setpoint) <= 5;
    else return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.drive.rotationAngleController.close();
    Robot.drive.stopAllMotors();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.drive.stopAllMotors();
  }
}
