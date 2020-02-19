/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package manipulators;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.sensors.CANCoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class ShooterSubsystem extends SubsystemBase {
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
  @Override
  public void periodic(){} 
}
