/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import com.revrobotics.ColorMatchResult;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.Robot;

public class positionControl extends Command {
  boolean noGameData = false;
  public positionControl() {
    requires(Robot.controlPanel);
  }

  ColorMatchResult current;
  Color target;
  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    String gameData;
    gameData = DriverStation.getInstance().getGameSpecificMessage();
    if(!(gameData.length() > 0)) noGameData = true;
    
    target = Robot.controlPanel.takeGameData(gameData);
    target = Robot.controlPanel.convertData(target);
    
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    current = Robot.controlPanel.m_colorMatcher.matchClosestColor(Robot.controlPanel.colorSensor.getColor());
    // Robot.controlPanel.runControlPanel.set(Robot.controlPanel.colorDirect(current.color,target) * 0.4); //Make fast as possible.
    Robot.controlPanel.runControlPanel.set(0.4);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return noGameData || current.color == target;
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
    Robot.controlPanel.runControlPanel.set(0);
  }
}
