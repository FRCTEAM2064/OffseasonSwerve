/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.commands.hoodUp;
import frc.commands.polarMotion;
import frc.commands.rotateToCenter;
import frc.commands.shoot;
import frc.commands.toggleIntakePanel;
import frc.robot.RobotMap;

public class RightSideAuto extends CommandGroup {
  /**
   * Add your docs here.
   */
  public RightSideAuto() {
    addParallel(new hoodUp());
    addParallel(new toggleIntakePanel());
    addSequential(new polarMotion(RobotMap.inchesToMeters(27.808), 180));
    addSequential(new polarMotion(RobotMap.inchesToMeters(117.219), 270));
    addSequential(new rotateToCenter(true, 2));
    addSequential(new shoot(5.0));
    addSequential(new polarMotion(RobotMap.inchesToMeters(136), 270));
    addSequential(new polarMotion(RobotMap.inchesToMeters(136), 90));
    addSequential(new shoot(4));
  }
}
