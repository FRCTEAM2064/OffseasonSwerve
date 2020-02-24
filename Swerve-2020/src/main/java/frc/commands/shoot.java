/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import java.util.concurrent.TimeUnit;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class shoot extends Command {
  public shoot() {
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

    Robot.shooter.shooter_motor.set(-RobotMap.maxFlywheelSpeed);
    Robot.shooter.intakeTubingUpwards.set(0);
    Robot.intake.intakeTubingInwards.set(0);

    try {
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    Robot.intake.intakeTubingInwards.set(RobotMap.maxTubingSpeed);
    Robot.shooter.intakeTubingUpwards.set(RobotMap.maxTubingSpeed);

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // Robot.shooter.shooter_motor.set(-0.5);
    // double RPM = Robot.shooter.shooter_encoder.getVelocity()/360;
    // if (Math.abs(RPM) < 2500) {
      // Robot.shooter.intakeTubingUpwards.set(0);
      Robot.shooter.shooter_motor.set(-RobotMap.maxFlywheelSpeed);
    // }
    // else{
      Robot.shooter.intakeTubingUpwards.set(RobotMap.maxTubingSpeed);
    // }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.shooter.shooter_motor.set(0.0);
    Robot.shooter.intakeTubingUpwards.set(0);
    Robot.intake.intakeTubingInwards.set(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.shooter.shooter_motor.set(0);
    Robot.shooter.intakeTubingUpwards.set(0);
    Robot.intake.intakeTubingInwards.set(0);
  }
}
