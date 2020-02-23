/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class rotateToCenter extends Command {
  /**
   * Creates a new rotateToCenter.
   */
  public double setpoint;

  public rotateToCenter() {
    // Use addRequirements() here to declare subsystem dependencies.
    // this.setpoint = setpoint;  
    requires(Robot.drive);
    
  }

  // Called when the command is initially scheduled.
  
  public void initialize(){
    Robot.vision.enable();
    Robot.vision.setSetpoint(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end() {
    Robot.vision.disable();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Robot.vision.onTarget();
  }

  @Override
  protected void interrupted() {
    Robot.vision.disable();
  }
}