/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.commands.rotationControl;
import frc.commands.toggleControlPanel;
import frc.robot.Robot;

public class rotationControlFull extends CommandGroup {
  /**
   * Add your docs here.
   */
  public rotationControlFull() {
    if (!Robot.controlPanel.isUp) {
      addSequential(new toggleControlPanel());
    }
    addSequential(new rotationControl());
  }
}
