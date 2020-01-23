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
import frc.robot.RobotMap;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.SwerveModifier;

public class testMoveSwerve extends CommandBase {
  Subsystem[] subsystems = {Robot.drive};
  public EncoderFollower flFollower;
  public EncoderFollower frFollower;
  public EncoderFollower blFollower;
  public EncoderFollower brFollower;

  private Trajectory trajectory;

  public testMoveSwerve(){
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.drive);
        

    Waypoint[] points = new Waypoint[] {
      new Waypoint(-4, -1, 0),
      // new Waypoint(-2, -2, 0),
      new Waypoint(0, 0, 0)
    };
    trajectory = Pathfinder.generate(points, Robot.drive.config);

    SwerveModifier modifier = new SwerveModifier(trajectory).modify(0.6731, 0.8382, SwerveModifier.Mode.SWERVE_DEFAULT);

    flFollower = new EncoderFollower(modifier.getFrontLeftTrajectory());
    frFollower = new EncoderFollower(modifier.getFrontRightTrajectory());
    blFollower = new EncoderFollower(modifier.getBackLeftTrajectory());
    brFollower = new EncoderFollower(modifier.getBackRightTrajectory());
    flFollower.configureEncoder(Robot.drive.mSwerveModules[1].getDriveEncoderVal(), 4096, RobotMap.RADIUS_OF_WHEEL*2);
    frFollower.configureEncoder(Robot.drive.mSwerveModules[0].getDriveEncoderVal(), 4096, RobotMap.RADIUS_OF_WHEEL*2);
    blFollower.configureEncoder(Robot.drive.mSwerveModules[2].getDriveEncoderVal(), 4096, RobotMap.RADIUS_OF_WHEEL*2);
    brFollower.configureEncoder(Robot.drive.mSwerveModules[3].getDriveEncoderVal(), 4096, RobotMap.RADIUS_OF_WHEEL*2);
    flFollower.configurePIDVA(Robot.drive.frontLeftDriveController.getP(), 0.0, Robot.drive.frontLeftDriveController.getD(), 1 / RobotMap.empirical_free_velocity, 0);
    frFollower.configurePIDVA(Robot.drive.frontRightDriveController.getP(), 0.0, Robot.drive.frontLeftDriveController.getD(), 1 / RobotMap.empirical_free_velocity, 0);
    blFollower.configurePIDVA(Robot.drive.backLeftDriveController.getP(), 0.0, Robot.drive.frontLeftDriveController.getD(), 1 / RobotMap.empirical_free_velocity, 0);
    brFollower.configurePIDVA(Robot.drive.backRightDriveController.getP(), 0.0, Robot.drive.frontLeftDriveController.getD(), 1 / RobotMap.empirical_free_velocity, 0);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double fl_angle = Pathfinder.boundHalfDegrees(Pathfinder.r2d(flFollower.getHeading()));
    double fr_angle = Pathfinder.boundHalfDegrees(Pathfinder.r2d(frFollower.getHeading()));
    double bl_angle = Pathfinder.boundHalfDegrees(Pathfinder.r2d(blFollower.getHeading()));
    double br_angle = Pathfinder.boundHalfDegrees(Pathfinder.r2d(brFollower.getHeading()));

    Robot.drive.mSwerveModules[1].getAnglePIDController().calculate(Robot.drive.mSwerveModules[1].readAngle(), Math.toRadians(fl_angle));
    Robot.drive.mSwerveModules[1].getDriveMotor().set(flFollower.calculate(Robot.drive.mSwerveModules[1].getDriveEncoderVal()));
    
    Robot.drive.mSwerveModules[0].getAnglePIDController().calculate(Robot.drive.mSwerveModules[0].readAngle(), Math.toRadians(fr_angle));
    Robot.drive.mSwerveModules[0].getDriveMotor().set(frFollower.calculate(Robot.drive.mSwerveModules[0].getDriveEncoderVal()));
    
    Robot.drive.mSwerveModules[2].getAnglePIDController().calculate(Robot.drive.mSwerveModules[2].readAngle(), Math.toRadians(bl_angle));
    Robot.drive.mSwerveModules[2].getDriveMotor().set(blFollower.calculate(Robot.drive.mSwerveModules[2].getDriveEncoderVal()));
    
    Robot.drive.mSwerveModules[3].getAnglePIDController().calculate(Robot.drive.mSwerveModules[3].readAngle(), Math.toRadians(br_angle));
    Robot.drive.mSwerveModules[3].getDriveMotor().set(brFollower.calculate(Robot.drive.mSwerveModules[3].getDriveEncoderVal()));
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


  // public void useTrajectories(){
    
  // }
}
