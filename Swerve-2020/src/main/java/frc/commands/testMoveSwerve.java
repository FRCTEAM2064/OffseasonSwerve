/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Robot;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.DistanceFollower;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.SwerveModifier;

public class testMoveSwerve extends CommandBase {
  Subsystem[] subsystems = {Robot.drive};
  private Trajectory fl;
  private Trajectory fr;
  private Trajectory bl;
  private Trajectory br;

  private double previous_velocity = 0.0;
  private int iterations = 0;
  public testMoveSwerve() {
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystems);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    

    Waypoint[] points = new Waypoint[] {
      new Waypoint(-4, -1, 0),
      // new Waypoint(-2, -2, 0),
      // new Waypoint(0, 0, 0);
    };

    Trajectory trajectory = Pathfinder.generate(points, Robot.drive.config);
    SwerveModifier modifier = new SwerveModifier(trajectory).modify(0.6731, 0.8382, SwerveModifier.Mode.SWERVE_DEFAULT);
    
    fl = modifier.getFrontLeftTrajectory();
    fr = modifier.getFrontRightTrajectory(); 
    bl = modifier.getBackLeftTrajectory();
    br = modifier.getBackRightTrajectory();
    // EncoderFollower frontLeft = new EncoderFollower(fl);
    // // frontLeft.configureEncoder(0, Math.PI * 2, 4.0);
    // // frontLeft.
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      Robot.drive.mSwerveModules[1].setTargetVelocity(fl.segments[iterations].velocity);
      Robot.drive.mSwerveModules[1].setTargetAngle(fl.segments[iterations].heading);
      // Robot.drive.mSwerveModules[1].
    iterations++;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (iterations < fl.segments.length);
  }
    //Test code for trajectory library:


  // public void useTrajectories(){
    
  // }
}
