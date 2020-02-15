/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.SwerveModifier;

public class testMoveSwerve extends Command{

  private Trajectory trajectory;

  public testMoveSwerve(){
    Waypoint[] points = new Waypoint[] {
      new Waypoint(0, 0, 0),
      new Waypoint(0, 2, 2),
      new Waypoint(2, 2, 0)
    };
    trajectory = Pathfinder.generate(points, Robot.drive.config);

    Robot.drive.modifier = new SwerveModifier(trajectory).modify(0.6731, 0.8382, SwerveModifier.Mode.SWERVE_DEFAULT);
    Robot.drive.flFollower = new EncoderFollower(Robot.drive.modifier.getFrontLeftTrajectory());
    Robot.drive.frFollower = new EncoderFollower(Robot.drive.modifier.getFrontRightTrajectory());
    Robot.drive.blFollower = new EncoderFollower(Robot.drive.modifier.getBackLeftTrajectory());
    Robot.drive.brFollower = new EncoderFollower(Robot.drive.modifier.getBackRightTrajectory());
    Robot.drive.flFollower.configureEncoder(0, 4096, RobotMap.RADIUS_OF_WHEEL*2);
		Robot.drive.frFollower.configureEncoder(0, 4096, RobotMap.RADIUS_OF_WHEEL*2);
		Robot.drive.blFollower.configureEncoder(0, 4096, RobotMap.RADIUS_OF_WHEEL*2);
		Robot.drive.brFollower.configureEncoder(0, 4096, RobotMap.RADIUS_OF_WHEEL*2);
		// flFollower.configurePIDVA(0.00004, 0.0, 0, 1 / RobotMap.empirical_free_velocity, 3);
		// frFollower.configurePIDVA(0.00004, 0.0, 0, 1 / RobotMap.empirical_free_velocity, 3);
		// blFollower.configurePIDVA(0.00004, 0.0, 0, 1 / RobotMap.empirical_free_velocity, 3);
		// brFollower.configurePIDVA(0.00004, 0.0, 0, 1 / RobotMap.empirical_free_velocity, 3);

		Robot.drive.flFollower.configurePIDVA(1, 0.0, 0, 1 / RobotMap.empirical_free_velocity, 3);
		Robot.drive.frFollower.configurePIDVA(1, 0.0, 0, 1 / RobotMap.empirical_free_velocity, 3);
		Robot.drive.blFollower.configurePIDVA(1, 0.0, 0, 1 / RobotMap.empirical_free_velocity, 3);
		Robot.drive.brFollower.configurePIDVA(1, 0.0, 0, 1 / RobotMap.empirical_free_velocity, 3);
    
    System.out.println("Command Initialized");
    // Use addRequirements() here to declare subsystem dependencies.
    requires(Robot.drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.drive.flFollower = new EncoderFollower(Robot.drive.modifier.getFrontLeftTrajectory());
    Robot.drive.frFollower = new EncoderFollower(Robot.drive.modifier.getFrontRightTrajectory());
    Robot.drive.blFollower = new EncoderFollower(Robot.drive.modifier.getBackLeftTrajectory());
    Robot.drive.brFollower = new EncoderFollower(Robot.drive.modifier.getBackRightTrajectory());

    double fl_angle = Pathfinder.boundHalfDegrees(Pathfinder.r2d(Robot.drive.flFollower.getHeading()));
    double fr_angle = Pathfinder.boundHalfDegrees(Pathfinder.r2d(Robot.drive.frFollower.getHeading()));
    double bl_angle = Pathfinder.boundHalfDegrees(Pathfinder.r2d(Robot.drive.blFollower.getHeading()));
    double br_angle = Pathfinder.boundHalfDegrees(Pathfinder.r2d(Robot.drive.brFollower.getHeading()));
    // Robot.drive.mSwerveModules[1].getDriveMotor().set(0.4);

//TODO: PROBLEM: Command structure is wack
    Robot.drive.mSwerveModules[1].getAngleMotor().set(Robot.drive.mSwerveModules[1].getAnglePIDController().calculate(Robot.drive.mSwerveModules[1].readAngle(), Math.toRadians(fl_angle)));
    Robot.drive.mSwerveModules[1].getDriveMotor().set(Robot.drive.flFollower.calculate((int)Robot.drive.mSwerveModules[1].getDriveEncoderVal()));
    
    Robot.drive.mSwerveModules[0].getAngleMotor().set(Robot.drive.mSwerveModules[0].getAnglePIDController().calculate(Robot.drive.mSwerveModules[0].readAngle(), Math.toRadians(fr_angle)));
    Robot.drive.mSwerveModules[0].getDriveMotor().set(Robot.drive.frFollower.calculate((int)Robot.drive.mSwerveModules[0].getDriveEncoderVal()));
    
    Robot.drive.mSwerveModules[2].getAngleMotor().set(Robot.drive.mSwerveModules[2].getAnglePIDController().calculate(Robot.drive.mSwerveModules[2].readAngle(), Math.toRadians(bl_angle)));
    Robot.drive.mSwerveModules[2].getDriveMotor().set(Robot.drive.blFollower.calculate((int)Robot.drive.mSwerveModules[2].getDriveEncoderVal()));
    
    Robot.drive.mSwerveModules[3].getAngleMotor().set(Robot.drive.mSwerveModules[3].getAnglePIDController().calculate(Robot.drive.mSwerveModules[3].readAngle(), Math.toRadians(br_angle)));
    Robot.drive.mSwerveModules[3].getDriveMotor().set(Robot.drive.brFollower.calculate((int)Robot.drive.mSwerveModules[3].getDriveEncoderVal()));
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Robot.drive.flFollower = new EncoderFollower(Robot.drive.modifier.getFrontLeftTrajectory());
    Robot.drive.frFollower = new EncoderFollower(Robot.drive.modifier.getFrontRightTrajectory());
    Robot.drive.blFollower = new EncoderFollower(Robot.drive.modifier.getBackLeftTrajectory());
    Robot.drive.brFollower = new EncoderFollower(Robot.drive.modifier.getBackRightTrajectory());

    double fl_angle = Pathfinder.boundHalfDegrees(Pathfinder.r2d(Robot.drive.flFollower.getHeading()));
    double fr_angle = Pathfinder.boundHalfDegrees(Pathfinder.r2d(Robot.drive.frFollower.getHeading()));
    double bl_angle = Pathfinder.boundHalfDegrees(Pathfinder.r2d(Robot.drive.blFollower.getHeading()));
    double br_angle = Pathfinder.boundHalfDegrees(Pathfinder.r2d(Robot.drive.brFollower.getHeading()));
    // Robot.drive.mSwerveModules[1].getDriveMotor().set(0.4);

//TODO: PROBLEM: Command structure is wack
    Robot.drive.mSwerveModules[1].getAngleMotor().set(Robot.drive.mSwerveModules[1].getAnglePIDController().calculate(Robot.drive.mSwerveModules[1].readAngle(), Math.toRadians(fl_angle)));
    Robot.drive.mSwerveModules[1].getDriveMotor().set(Robot.drive.flFollower.calculate((int)Robot.drive.mSwerveModules[1].getDriveEncoderVal()));

<<<<<<< HEAD
    Robot.drive.mSwerveModules[0].getAngleMotor().set(Robot.drive.mSwerveModules[0].getAnglePIDController().calculate(Robot.drive.mSwerveModules[0].readAngle(), Math.toRadians(fr_angle)));
    Robot.drive.mSwerveModules[0].getDriveMotor().set(Robot.drive.frFollower.calculate((int)Robot.drive.mSwerveModules[0].getDriveEncoderVal()));
=======
    Robot.drive.mSwerveModules[0].getAngleMotor().set(Robot.drive.mSwerveModules[0].getAnglePIDController().calculate(Robot.drive.mSwerveModules[0].readAngle(), fr_angle));
    Robot.drive.mSwerveModules[0].getDriveMotor().set(Robot.drive.frFollower.calculate((int)-Robot.drive.rTFRDEncVal(Robot.drive.previous_FRenc)));
>>>>>>> 

    Robot.drive.mSwerveModules[2].getAngleMotor().set(Robot.drive.mSwerveModules[2].getAnglePIDController().calculate(Robot.drive.mSwerveModules[2].readAngle(), Math.toRadians(bl_angle)));
    Robot.drive.mSwerveModules[2].getDriveMotor().set(Robot.drive.blFollower.calculate((int)Robot.drive.mSwerveModules[2].getDriveEncoderVal()));

    Robot.drive.mSwerveModules[3].getAngleMotor().set(Robot.drive.mSwerveModules[3].getAnglePIDController().calculate(Robot.drive.mSwerveModules[3].readAngle(), Math.toRadians(br_angle)));
    Robot.drive.mSwerveModules[3].getDriveMotor().set(Robot.drive.brFollower.calculate((int)Robot.drive.mSwerveModules[3].getDriveEncoderVal()));
        System.out.println("Command running");
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end() {
    Robot.drive.stopAllMotors();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Robot.drive.frFollower.isFinished() && Robot.drive.flFollower.isFinished() && Robot.drive.blFollower.isFinished() && Robot.drive.brFollower.isFinished();
  }
}