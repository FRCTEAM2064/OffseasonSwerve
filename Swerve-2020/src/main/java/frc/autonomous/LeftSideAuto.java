/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.commands.polarMotion;
import frc.commands.toggleIntakePanel;
import frc.robot.RobotMap;

public class LeftSideAuto extends CommandGroup {
  /**
   * Add your docs here.
   */
  public LeftSideAuto() {
    addSequential(new polarMotion(RobotMap.inchesToMeters(14.5), 0));
    addSequential(new WaitCommand(200));
    addSequential(new toggleIntakePanel());
    addSequential(new polarMotion(RobotMap.inchesToMeters(7*12 - 39 + 41.7), 90)); // 18 is to get balls, 39 is accounting for robot length
    addSequential(new WaitCommand(2)); //wait for balls to go in
    // addSequential(new polarMotion(meters, angle));
  }
}
