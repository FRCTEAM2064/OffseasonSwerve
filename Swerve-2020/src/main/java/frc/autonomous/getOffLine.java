/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.commands.bringClimbToDefault;
import frc.commands.polarMotion;
import frc.commands.rotateToCenter;

public class getOffLine extends CommandGroup {
  /**
   * Add your docs here.
   */
  public getOffLine() {
    addParallel(new bringClimbToDefault());
    addSequential(new polarMotion(2, 90));
    addSequential(new rotateToCenter(true, 3.0));
    // addSequential(new shoot(6.0));69
  }
}
