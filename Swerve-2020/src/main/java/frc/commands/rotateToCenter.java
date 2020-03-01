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
  public boolean time = false;
  public rotateToCenter() {
    // Use addRequirements() here to declare subsystem dependencies.
    // this.setpoint = setpoint;  
    requires(Robot.drive);
    requires(Robot.vision);
  }
  public rotateToCenter(boolean auto, double timeout) {
    // Use addRequirements() here to declare subsystem dependencies.
    // this.setpoint = setpoint;  
    this.auto = auto;
    setTimeout(timeout);
    requires(Robot.drive);
    requires(Robot.vision);
  }

  // Called when the command is initially scheduled.
  
  public void initialize(){
    Robot.vision.firstLime.setLedMode(LedMode.ON);
    if(auto){
      Robot.drive.holonomicDrive(0, 0, Robot.vision.returnRotationValue(), true);
    }
    else{
      Robot.drive.holonomicDrive(-OI.getlYval(), -OI.getlXval(), Robot.vision.returnRotationValue(), true);
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // System.out.println("command executing");
    if(auto){
      Robot.drive.holonomicDrive(0, 0, Robot.vision.returnRotationValue(), true);
    }
    else{
      Robot.drive.holonomicDrive(-OI.getlYval(), -OI.getlXval(), Robot.vision.returnRotationValue(), true);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end() {
    Robot.vision.rotateToTarget.close();
    Robot.vision.firstLime.setLedMode(LedMode.OFF);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (auto) return Robot.vision.rotateToTarget.atSetpoint() || isTimedOut();
    else return false;
    
  }

  @Override
  protected void interrupted() {
    Robot.vision.close();
    Robot.vision.firstLime.setLedMode(LedMode.OFF);
    Robot.drive.holonomicDrive(OI.getlYval(), OI.getlXval(), OI.getrXval(), true);
  }
}