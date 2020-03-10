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

public class bringClimbToDefault extends Command {
  double initial;
  double current;
  public bringClimbToDefault() {
    requires(Robot.climb);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    initial = Robot.climb.careful.getPosition();
    Robot.climb.winchControl.set(0.8);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    current = Robot.climb.careful.getPosition();
    if (current <= 0){
      Robot.climb.winchControl.set(0.8);
    }
    else{
      Robot.climb.winchControl.set(0.4);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    current = Robot.climb.careful.getPosition();
    return current >= RobotMap.climbBelowTrench - 10;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.climb.winchControl.set(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.climb.winchControl.set(0);
  }
}
