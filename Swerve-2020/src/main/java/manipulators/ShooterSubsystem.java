/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package manipulators;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.controller.PIDController;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class ShooterSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public VictorSP intakeTubingUpwards;

  public CANSparkMax shooter_motor;
  public CANEncoder shooter_encoder;
  public CANSparkMax variable_hood;
  public CANEncoder hood_encoder;
  public PIDController shooter_velocity;
  public PIDController hood_angle;
  
  public double previous_vel;
  public double current_vel = 0;
  public double error;
  public double summation_of_vel = 0;
  public double returned_output = 0;
  
  public ShooterSubsystem(){
    shooter_motor = new CANSparkMax(RobotMap.shooterID, MotorType.kBrushless);
    shooter_encoder = new CANEncoder(shooter_motor);
    intakeTubingUpwards = new VictorSP(RobotMap.intakeTubingUpwardsID);
    variable_hood = new CANSparkMax(RobotMap.intake1ID, MotorType.kBrushed);
    hood_encoder = new CANEncoder(variable_hood);
    hood_angle = new PIDController(0.002, 0, 0.0001);
    shooter_velocity = new PIDController(0.001, 0.001, 0.001);
  }

  public double shooterAreaLength(double area){
    return 4.82 * area - 14.1;
  }

  public double shooterLengthSpeed(double length){
    return length/40 + 0.5;
  }

  public double shooterPercentSpeed(double percent){
    return percent * 4500;
  }
  @Override
  protected void initDefaultCommand() {
    // TODO Auto-generated method stub
  }

  public void update(){
    previous_vel = current_vel;
    current_vel = shooter_encoder.getVelocity()/60;
    error = shooter_velocity.getSetpoint() - current_vel;
    summation_of_vel += 0.050 * current_vel;
  }

  public void reset(){
    current_vel = 0;
    shooter_velocity.setSetpoint(0);
    summation_of_vel = 0;
    returned_output = 0;
  }

  public double convert_encoder_to_angle(){
    return hood_encoder.getPosition()/30;
  }

  public double hood_angle_power(double angle){
    return hood_angle.calculate(convert_encoder_to_angle(), angle);
  }
}
