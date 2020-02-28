/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.common.drivers.Limelight.LedMode;
import frc.robot.OI;
import frc.robot.Robot;

public class rotateToCenter extends Command {
  /**
   * Creates a new rotateToCenter.
   */
  public double setpoint;
  public boolean auto = false;
  public rotateToCenter() {
    // Use addRequirements() here to declare subsystem dependencies.
    // this.setpoint = setpoint;  
    requires(Robot.drive);
    requires(Robot.vision);
  }
  public rotateToCenter(boolean auto) {
    // Use addRequirements() here to declare subsystem dependencies.
    // this.setpoint = setpoint;  
    this.auto = auto;
    requires(Robot.drive);
    requires(Robot.vision);
  }

  // Called when the command is initially scheduled.
  
  public void initialize(){
    // System.out.println("Command Initialized");
    Robot.vision.firstLime.setLedMode(LedMode.ON);
    Robot.vision.enable();
    Robot.vision.setSetpoint(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // System.out.println("command executing");
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end() {
    Robot.vision.disable();
    Robot.vision.firstLime.setLedMode(LedMode.OFF);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (auto) return Robot.vision.onTarget();
    else return false;
    
  }

  @Override
  protected void interrupted() {
    Robot.vision.disable();
    Robot.drive.holonomicDrive(OI.getlYval(), OI.getlXval(), OI.getrXval(), true);
  }
}