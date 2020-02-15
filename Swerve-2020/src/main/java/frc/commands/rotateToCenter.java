/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

public class rotateToCenter extends Command {
  /**
   * Creates a new rotateToCenter.
   */
  public rotateToCenter() {
    // Use addRequirements() here to declare subsystem dependencies.
    requires(Robot.drive);
  }

  // Called when the command is initially scheduled.
  
  public void initialize(){
    Robot.drive.holonomicDrive(-OI.getlYval(), OI.getlXval(), Robot.vision.centeringRobotPID(), false);
    // System.out.println("Command Initialized");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    // System.out.println("Command Executing");
    Robot.drive.holonomicDrive(-OI.getlYval(), OI.getlXval(), Robot.vision.centeringRobotPID(), false);
=======
    // System.out.println("Command Executing");   //TODO: May not be necessary but still want to try
    // if (Robot.vision.firstLime.tx.getDouble(0.0) > 0)
      // Robot.drive.holonomicDrive(-OI.getlYval(), OI.getlXval(), Robot.vision.centeringRobotPID(), true);
    // else{
      Robot.drive.holonomicDrive(-OI.getlYval(), OI.getlXval(), Robot.vision.centeringRobotPID(), true);
    // }
>>>>>>> 
=======
    // System.out.println("Command Executing");
    Robot.drive.holonomicDrive(-OI.getlYval(), OI.getlXval(), Robot.vision.centeringRobotPID(), false);
>>>>>>> 3676ba864874d636f635985bc552606c86d2739b
=======
    // System.out.println("Command Executing");
    Robot.drive.holonomicDrive(-OI.getlYval(), OI.getlXval(), Robot.vision.centeringRobotPID(), false);
>>>>>>> 3676ba864874d636f635985bc552606c86d2739b
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end() {
    // System.out.println("Command ended");
    Robot.drive.holonomicDrive(-OI.getlYval(), OI.getlXval(), 0, false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Robot.vision.rotateToTarget.atSetpoint();
  }
}
