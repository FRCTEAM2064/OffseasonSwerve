/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import com.revrobotics.ColorMatchResult;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class rotationControl extends Command {
  double numberRotations = 0.0;
  ColorMatchResult initial;

  public rotationControl() {
    requires(Robot.controlPanel);
    initial = Robot.controlPanel.m_colorMatcher.matchClosestColor(Robot.controlPanel.colorSensor.getColor());
    Robot.controlPanel.previous_color = initial.color;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    // initial = Robot.controlPanel.m_colorMatcher.matchClosestColor(Robot.controlPanel.colorSensor.getColor());
    // Robot.controlPanel.previous_color = initial.color;
    //IF this doesn't work shift comment the top 2 lines out and uncomment the bottom 2 lines in the constructor (method rotationControl right above)
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.controlPanel.runControlPanel.set(0.5);
    ColorMatchResult current = Robot.controlPanel.m_colorMatcher.matchClosestColor(Robot.controlPanel.colorSensor.getColor());
    numberRotations += Robot.controlPanel.detectColor(current, initial);
    System.out.println(numberRotations);
  }
  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return (numberRotations > 2.5 && numberRotations < 4.5);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.controlPanel.runControlPanel.set(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
