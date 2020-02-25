/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package manipulators;

import com.ctre.phoenix.sensors.CANCoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class ShooterSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public VictorSP intakeTubingUpwards;

  public CANSparkMax shooter_motor;
  public CANCoder shooter_encoder;
  
  public ShooterSubsystem(){
    shooter_motor = new CANSparkMax(RobotMap.shooterID, MotorType.kBrushless);
    shooter_encoder = new CANCoder(RobotMap.shooterID);
    intakeTubingUpwards = new VictorSP(RobotMap.intakeTubingUpwardsID);
  }

  public double shooterAreaLength(double area){
    return 4.82 * area - 14.1;
  }

  public double shooterLengthSpeed(double length){
    return length/40 + 0.5;
  }

  @Override
  protected void initDefaultCommand() {
    // TODO Auto-generated method stub

  }
}
