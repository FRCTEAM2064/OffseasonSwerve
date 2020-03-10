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
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class IntakeSubsystem extends Subsystem {
  /**
   * Creates a new IntakeSubsystem.
   */
  
  public CANSparkMax intake1;
  public VictorSP intakeTubingInwards;

  public DoubleSolenoid intakePiston;
  public boolean isDown = false;
  
  public IntakeSubsystem() {
    intake1 = new CANSparkMax(RobotMap.intake1ID, MotorType.kBrushless);
    intakeTubingInwards = new VictorSP(RobotMap.intakeTubingInwardsID);
    intakePiston = new DoubleSolenoid(0, 1);
  }

  @Override
  protected void initDefaultCommand() {}

  public void activateMotors(){
    intake1.set(RobotMap.maxTubingSpeed);
    intakeTubingInwards.set(RobotMap.maxTubingSpeed);
  }

  public void deactivateMotors(){
    intake1.set(0);
    intakeTubingInwards.set(0);
  }

  public void reverseMotors(){
    intake1.set(-RobotMap.maxTubingSpeed);
    intakeTubingInwards.set(-RobotMap.maxTubingSpeed);
  }
}
