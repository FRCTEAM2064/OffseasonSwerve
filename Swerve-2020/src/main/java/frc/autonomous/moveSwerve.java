/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Robot;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.modifiers.SwerveModifier;

public class moveSwerve extends CommandBase {
  Subsystem[] subsystems = {Robot.drive};
  public moveSwerve() {
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystems);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
    //Test code for trajectory library:
    //TODO: Is Jaci's library or WPI's library better to use for trajectory generation?
	Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.02, 1.7, 2.0, 60.0);
	Waypoint[] points = new Waypoint[] {
		new Waypoint(-4, -1, Pathfinder.d2r(-45)),
		new Waypoint(-2, -2, 0),
		new Waypoint(0, 0, 0)
	};

	Trajectory trajectory = Pathfinder.generate(points, config);
	// Wheelbase Width(left to right) = 0.5m, Wheelbase length(front to back) = 0.6m, Swerve Mode = Default
	SwerveModifier modifier = new SwerveModifier(trajectory).modify(0.5, 0.6, SwerveModifier.Mode.SWERVE_DEFAULT);
	
  Trajectory fl = modifier.getFrontLeftTrajectory();
	Trajectory fr = modifier.getFrontRightTrajectory();
	Trajectory bl = modifier.getBackLeftTrajectory();
  Trajectory br = modifier.getBackRightTrajectory();
  public void useTrajectories(){

  }
}
