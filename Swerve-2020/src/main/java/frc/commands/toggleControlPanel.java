<<<<<<< HEAD
<<<<<<< HEAD
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class toggleControlPanel extends Command {
  public toggleControlPanel() {
    requires(Robot.controlPanel);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    if (Robot.controlPanel.isUp){
      Robot.controlPanel.accessControlPanel.set(DoubleSolenoid.Value.kReverse);
      Robot.controlPanel.isUp = false;
    }
    else{
      Robot.controlPanel.accessControlPanel.set(DoubleSolenoid.Value.kForward);
      Robot.controlPanel.isUp = true;
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
=======
=======
>>>>>>> 3676ba864874d636f635985bc552606c86d2739b
// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
// /* Open Source Software - may be modified and shared by FRC teams. The code   */
// /* must be accompanied by the FIRST BSD license file in the root directory of */
// /* the project.                                                               */
// /*----------------------------------------------------------------------------*/

// package frc.commands;

// import edu.wpi.first.wpilibj.DoubleSolenoid;
// import edu.wpi.first.wpilibj.command.Command;
// import frc.robot.Robot;

// public class toggleControlPanel extends Command {
//   public toggleControlPanel() {
//     requires(Robot.controlPanel);
//   }

//   // Called just before this Command runs the first time
//   @Override
//   protected void initialize() {
//     if (Robot.controlPanel.isUp){
//       Robot.controlPanel.accessControlPanel.set(DoubleSolenoid.Value.kReverse);
//       Robot.controlPanel.isUp = false;
//     }
//     else{
//       Robot.controlPanel.accessControlPanel.set(DoubleSolenoid.Value.kForward);
//       Robot.controlPanel.isUp = true;
//     }
//   }

//   // Called repeatedly when this Command is scheduled to run
//   @Override
//   protected void execute() {
//   }

//   // Make this return true when this Command no longer needs to run execute()
//   @Override
//   protected boolean isFinished() {
//     return true;
//   }

//   // Called once after isFinished returns true
//   @Override
//   protected void end() {
//   }

//   // Called when another command which requires one or more of the same
//   // subsystems is scheduled to run
//   @Override
//   protected void interrupted() {
//   }
// }
<<<<<<< HEAD
>>>>>>> 3676ba864874d636f635985bc552606c86d2739b
=======
>>>>>>> 3676ba864874d636f635985bc552606c86d2739b
