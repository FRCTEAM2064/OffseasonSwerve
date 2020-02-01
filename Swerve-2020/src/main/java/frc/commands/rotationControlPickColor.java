/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class rotationControlPickColor extends Command {

  private ColorMatchResult sensorColor;
  private ColorMatchResult gameColor;

  public rotationControlPickColor() {
    requires(Robot.controlPanel);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    //initialize sensorColor
    sensorColor = Robot.controlPanel.m_colorMatcher.matchClosestColor(Robot.controlPanel.colorSensor.getColor());
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //activate the controlPanel motor and turn wheel while detecting color
    // Robot.controlPanel.runControlPanel.set(ControlMode.PercentOutput, 0.3);
    getGameData();
    sensorColor = Robot.controlPanel.m_colorMatcher.matchClosestColor(Robot.controlPanel.colorSensor.getColor());
    System.out.println("spinning...." + sensorColor.color);
  }
  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
  
    if(gameColor.color == sensorColor.color){
      return true;
    }else{
      return false;
    }
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    System.out.println("Stop Rotating");
    // Robot.controlPanel.runControlPanel.set(ControlMode.PercentOutput, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }

  private void getGameData(){
    
    String gameData;
    
    gameData = DriverStation.getInstance().getGameSpecificMessage();

    if(gameData.length() > 0)
    {
      switch (gameData.charAt(0))
      {
        case 'B' :
          //Game Color = Blue
          //Sensor Color = Red
          gameColor = Robot.controlPanel.m_colorMatcher.matchClosestColor(ColorMatch.makeColor(0.561, 0.232, 0.114));
          break;
        case 'G' :
          //Game Color = Green
          //Sensor Color = Yellow
          gameColor = Robot.controlPanel.m_colorMatcher.matchClosestColor(ColorMatch.makeColor(0.361, 0.524, 0.113));
          
          break;
        case 'R' :
          //Game Color = Red
          //Sensor Color = Blue
          gameColor = Robot.controlPanel.m_colorMatcher.matchClosestColor(ColorMatch.makeColor(0.143, 0.427, 0.429));
          break;
        case 'Y' :
          //Game Color = Yellow
          //Sensor Color = Green
          gameColor = Robot.controlPanel.m_colorMatcher.matchClosestColor(ColorMatch.makeColor(0.197, 0.561, 0.240));
          break;
        default :
          //This is corrupt data
          break;
      }
    } else {
      //Code for no data received yet
    }

  }
}
