/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package manipulators;

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
  public CANSparkMax intakeTubingInwards; //Might be talon; will have to change this if so

  public DoubleSolenoid intakePiston;
  public boolean isDown = false;
  public IntakeSubsystem() {
    intakeTubingLeft = new CANSparkMax(13, MotorType.kBrushless);
    intakeTubingRight = new CANSparkMax(14, MotorType.kBrushless);
    intakeTubingInwards = new CANSparkMax(15, MotorType.kBrushless);

    intakePiston = new DoubleSolenoid(1, 0);
  }

  @Override
  protected void initDefaultCommand() {}

  public void activateMotors(){
    intakeTubingLeft.set(0.6);
    intakeTubingRight.set(0.6);
    intakeTubingInwards.set(0.6);
  }

  public void deactivateMotors(){
    intakeTubingLeft.set(0);
    intakeTubingRight.set(0);
    intakeTubingInwards.set(0);
  }
}
