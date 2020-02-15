/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package manipulators;

<<<<<<< HEAD
<<<<<<< HEAD
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
=======
>>>>>>> 3676ba864874d636f635985bc552606c86d2739b
=======
>>>>>>> 3676ba864874d636f635985bc552606c86d2739b
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

<<<<<<< HEAD
<<<<<<< HEAD
  public TalonSRX intakeTubingUpwards;

  public CANSparkMax shooter_motor;
  public CANCoder shooter_encoder;
  
  public ShooterSubsystem(){
    intakeTubingUpwards = new TalonSRX(12);

    shooter_motor = new CANSparkMax(13, MotorType.kBrushless);
    shooter_encoder = new CANCoder(13);
=======
=======
>>>>>>> 3676ba864874d636f635985bc552606c86d2739b
  CANSparkMax shooter_motor;
  CANCoder shooter_encoder;

  DoubleSolenoid hoodAdjust;
  
  public ShooterSubsystem(){
    shooter_motor = new CANSparkMax(12, MotorType.kBrushless);
    shooter_encoder = new CANCoder(12);
    
    hoodAdjust = new DoubleSolenoid(3,2);
<<<<<<< HEAD
>>>>>>> 3676ba864874d636f635985bc552606c86d2739b
=======
>>>>>>> 3676ba864874d636f635985bc552606c86d2739b
  }
  @Override
  public void periodic(){
    shooter_motor.set(0.7);
    double RPM = shooter_encoder.getVelocity()/360;
<<<<<<< HEAD
<<<<<<< HEAD
    if (RPM < 1500) {
=======
    if (RPM < 1000) {
>>>>>>> 3676ba864874d636f635985bc552606c86d2739b
=======
    if (RPM < 1000) {
>>>>>>> 3676ba864874d636f635985bc552606c86d2739b
      shooter_motor.set(0.9);
    }
  } 
}
