/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.common.drivers.Limelight.LedMode;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class shoot extends Command {
  private double power = -RobotMap.default_power;
  private double velocity;
  private boolean use_velocity = false;
  private double output;
  public shoot() {
    requires(Robot.shooter);
  }

  public shoot(double power) {
    this.power = power;
    requires(Robot.shooter);
  }

  public shoot(double power, double timeout){
    this.power = power;
    setTimeout(timeout);
  }

  public shoot(double velocity, boolean e){
    this.velocity = velocity;
    use_velocity = e;
  }

  public shoot(double velocity, double timeout, boolean e){
    this.velocity = velocity;
    setTimeout(timeout);
    use_velocity = e;
  }
  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.shooter.shooter_velocity.setSetpoint(velocity);
    if (use_velocity){
      output = Robot.shooter.shooter_velocity.getP() * Robot.shooter.error + Robot.shooter.shooter_velocity.getI() * Robot.shooter.summation_of_vel
         + Robot.shooter.shooter_velocity.getD() * (Robot.shooter.current_vel - Robot.shooter.previous_vel);
      Robot.shooter.returned_output += output;
      Robot.shooter.shooter_motor.set(Robot.shooter.returned_output);
    }
    else{
      Robot.shooter.shooter_motor.set(power);
      Robot.vision.firstLime.setLedMode(LedMode.ON);
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (use_velocity){
      Robot.shooter.shooter_motor.set(Robot.shooter.returned_output);
    }
    else{
      Robot.shooter.shooter_motor.set(power);
    }
    // if (-Robot.shooter.shooter_encoder.getVelocity() > -Robot.shooter.shooterPercentSpeed(speed) - 1500){
    //   Robot.intake.intakeTubingInwards.set(RobotMap.maxTubingSpeed);
    //   Robot.shooter.intakeTubingUpwards.set(RobotMap.maxTubingSpeed);
    // }
    // else{
    //   Robot.intake.intakeTubingInwards.set(0);
    //   Robot.shooter.intakeTubingUpwards.set(0);
    // }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false || isTimedOut();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.vision.firstLime.setLedMode(LedMode.OFF);
    Robot.shooter.shooter_motor.set(0);
    Robot.shooter.intakeTubingUpwards.set(0);
    Robot.intake.intakeTubingInwards.set(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.vision.firstLime.setLedMode(LedMode.OFF);
    Robot.shooter.shooter_motor.set(0);
    Robot.shooter.intakeTubingUpwards.set(0);
    Robot.intake.intakeTubingInwards.set(0);
  }
}

//   private double speed = 3000;


//   public shoot(double timeout){
//     setTimeout(timeout);
//   }

//   public shoot(int ball_final){
//     this.ball_final = ball_final;
//   }

//   // Called just before this Command runs the first time
//   @Override
//   protected void initialize() {
//     Robot.vision.firstLime.setLedMode(LedMode.ON);
//     Robot.shooter.shooter_motor.set(-RobotMap.maxFlywheelSpeed);
//     // Robot.shooter.intakeTubingUpwards.set(0);
//     // Robot.intake.intakeTubingInwards.set(0);
//   }

//   // Called repeatedly when this Command is scheduled to run
//   @Override
//   protected void execute() {
//     Robot.shooter.shooter_motor.set(-RobotMap.maxFlywheelSpeed);
    
//     if (-Robot.shooter.shooter_encoder.getVelocity() > speed){
//       Robot.intake.intakeTubingInwards.set(RobotMap.maxTubingSpeed);
//       Robot.shooter.intakeTubingUpwards.set(RobotMap.maxTubingSpeed);
      
//     }
//     else{
//       Robot.intake.intakeTubingInwards.set(0);
//       Robot.shooter.intakeTubingUpwards.set(0);
//     }
//     // if (-Robot.shooter.shooter_encoder.getVelocity() < 3300){
//     //   ball_count++;
//     //   // Probably won't work; it'll try to add every 50ms and it doesn't take 50ms to bring the shooter up to this speed.
//     // }
//   }

//   // Make this return true when this Command no longer needs to run execute()
//   @Override
//   protected boolean isFinished() {
//     if (ball_final > 0) return ball_count >=ball_final;
//     return false || isTimedOut();
//   }

//   // Called once after isFinished returns true
//   @Override
//   protected void end() {
//     System.out.println("Command Ended");
//     Robot.vision.firstLime.setLedMode(LedMode.OFF);
//     Robot.shooter.shooter_motor.set(0.0);
//     Robot.shooter.intakeTubingUpwards.set(0);
//     Robot.intake.intakeTubingInwards.set(0);
//   }

//   // Called when another command which requires one or more of the same
//   // subsystems is scheduled to run
//   @Override
//   protected void interrupted() {
//     Robot.shooter.shooter_motor.set(0);
//     Robot.shooter.intakeTubingUpwards.set(0);
//     Robot.intake.intakeTubingInwards.set(0);
//   }
// }
