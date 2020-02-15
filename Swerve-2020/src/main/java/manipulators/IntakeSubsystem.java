/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package manipulators;

<<<<<<< HEAD
<<<<<<< HEAD
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
=======
>>>>>>> 3676ba864874d636f635985bc552606c86d2739b
=======
>>>>>>> 3676ba864874d636f635985bc552606c86d2739b
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class IntakeSubsystem extends Subsystem {
  /**
   * Creates a new IntakeSubsystem.
   */
  
  public CANSparkMax intakeTubingLeft;
  public CANSparkMax intakeTubingRight;
<<<<<<< HEAD
<<<<<<< HEAD
  public TalonSRX intakeTubingInwards; 
=======
  public CANSparkMax intakeTubingInwards; //Might be talon; will have to change this if so
>>>>>>> 3676ba864874d636f635985bc552606c86d2739b
=======
  public CANSparkMax intakeTubingInwards; //Might be talon; will have to change this if so
>>>>>>> 3676ba864874d636f635985bc552606c86d2739b

  public DoubleSolenoid intakePiston;
  public boolean isDown = false;
  public IntakeSubsystem() {
<<<<<<< HEAD
<<<<<<< HEAD
    intakeTubingLeft = new CANSparkMax(9, MotorType.kBrushless);
    intakeTubingRight = new CANSparkMax(10, MotorType.kBrushless);
    intakeTubingInwards = new TalonSRX(11);
    
=======
=======
>>>>>>> 3676ba864874d636f635985bc552606c86d2739b
    intakeTubingLeft = new CANSparkMax(13, MotorType.kBrushless);
    intakeTubingRight = new CANSparkMax(14, MotorType.kBrushless);
    intakeTubingInwards = new CANSparkMax(15, MotorType.kBrushless);

<<<<<<< HEAD
>>>>>>> 3676ba864874d636f635985bc552606c86d2739b
=======
>>>>>>> 3676ba864874d636f635985bc552606c86d2739b
    intakePiston = new DoubleSolenoid(1, 0);
  }

  @Override
  protected void initDefaultCommand() {}

  public void activateMotors(){
    intakeTubingLeft.set(0.6);
    intakeTubingRight.set(0.6);
<<<<<<< HEAD
<<<<<<< HEAD
    intakeTubingInwards.set(ControlMode.PercentOutput, 0.6);
=======
    intakeTubingInwards.set(0.6);
>>>>>>> 3676ba864874d636f635985bc552606c86d2739b
=======
    intakeTubingInwards.set(0.6);
>>>>>>> 3676ba864874d636f635985bc552606c86d2739b
  }

  public void deactivateMotors(){
    intakeTubingLeft.set(0);
    intakeTubingRight.set(0);
<<<<<<< HEAD
<<<<<<< HEAD
    intakeTubingInwards.set(ControlMode.PercentOutput, 0);
=======
    intakeTubingInwards.set(0);
>>>>>>> 3676ba864874d636f635985bc552606c86d2739b
=======
    intakeTubingInwards.set(0);
>>>>>>> 3676ba864874d636f635985bc552606c86d2739b
  }
}
