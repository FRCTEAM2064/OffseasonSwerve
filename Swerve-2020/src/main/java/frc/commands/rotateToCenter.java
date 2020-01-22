/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class rotateToCenter extends CommandBase {
  /**
   * Creates a new rotateToCenter.
   */
  public rotateToCenter() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("Command initialized");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Robot.drive.holonomicDrive(0, 0, Robot.vision.centeringRobotPID(), false);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(final boolean interrupted) {
    Robot.drive.stopAllMotors();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Robot.vision.rotateToTarget.atSetpoint();
  }
}
