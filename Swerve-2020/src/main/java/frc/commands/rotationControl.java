/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.revrobotics.ColorMatchResult;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class rotationControl extends Command {
  double numberRotations = 0.0;
  ColorMatchResult initial;

  public rotationControl() {
    requires(Robot.controlPanel);
    // initial = Robot.controlPanel.m_colorMatcher.matchClosestColor(Robot.controlPanel.colorSensor.getColor());
    // Robot.controlPanel.previous_color = initial.color;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    initial = Robot.controlPanel.m_colorMatcher.matchClosestColor(Robot.controlPanel.colorSensor.getColor());
    Robot.controlPanel.previous_color = initial.color;
    //IF this doesn't work shift comment the top 2 lines out and uncomment the bottom 2 lines in the constructor (method rotationControl right above)
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
<<<<<<< HEAD
    Robot.controlPanel.runControlPanel.set(ControlMode.PercentOutput, 0.3);
    ColorMatchResult current = Robot.controlPanel.m_colorMatcher.matchClosestColor(Robot.controlPanel.colorSensor.getColor());
    numberRotations += Robot.controlPanel.detectRotation(current, initial);
=======
    // Robot.controlPanel.runControlPanel.set(ControlMode.PercentOutput, 0.3);
    ColorMatchResult current = Robot.controlPanel.m_colorMatcher.matchClosestColor(Robot.controlPanel.colorSensor.getColor());
    numberRotations += Robot.controlPanel.detectColor(current, initial);
>>>>>>> 3676ba864874d636f635985bc552606c86d2739b
  }
  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return (numberRotations > 3 && numberRotations < 5);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
<<<<<<< HEAD
    Robot.controlPanel.runControlPanel.set(ControlMode.PercentOutput, 0);
=======
    // Robot.controlPanel.runControlPanel.set(ControlMode.PercentOutput, 0);
>>>>>>> 3676ba864874d636f635985bc552606c86d2739b
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
