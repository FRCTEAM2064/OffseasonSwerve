/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.commands.polarMotion;

public class MoveForward2m extends CommandGroup {
  /**
   * Add your docs here.
   */
  public MoveForward2m() {
    addSequential(new polarMotion(2,90));
  }
}
