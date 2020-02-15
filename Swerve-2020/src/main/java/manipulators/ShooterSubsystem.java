/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package manipulators;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.sensors.CANCoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Add your docs here.
 */
public class ShooterSubsystem extends SubsystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public TalonSRX intakeTubingUpwards;

  public CANSparkMax shooter_motor;
  public CANCoder shooter_encoder;
  
  public ShooterSubsystem(){
    intakeTubingUpwards = new TalonSRX(12);

    shooter_motor = new CANSparkMax(13, MotorType.kBrushless);
    shooter_encoder = new CANCoder(13);
  }
  @Override
  public void periodic(){
    shooter_motor.set(0.7);
    double RPM = shooter_encoder.getVelocity()/360;
    if (RPM < 1500) {
      shooter_motor.set(0.9);
    }
  } 
}
