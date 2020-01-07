/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.vision;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.vision.drivers.Limelight;
import frc.vision.drivers.Limelight.CamMode;
import frc.vision.drivers.Limelight.LedMode;

/**
 * Add your docs here.
 */
public class VisionSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private static final String FIRST_LIMELIGHT_TABLE_NAME = "limelight-first";


  public Limelight firstLime;
  public PIDController rotateToTarget;
  public PIDSource targetXCoor;
  public PIDOutput rotateRobot;

  public VisionSubsystem(){
    firstLime = new Limelight(NetworkTableInstance.getDefault().getTable(FIRST_LIMELIGHT_TABLE_NAME));
    firstLime.setCamMode(CamMode.VISION);
    firstLime.setLedMode(LedMode.ON);
    firstLime.setPipeline(0);

    targetXCoor = new PIDSource(){
    
      @Override
      public void setPIDSourceType(PIDSourceType pidSource) {
        pidSource = PIDSourceType.kDisplacement;
      }
    
      @Override
      public double pidGet() {
        return firstLime.getTargetPosition().x;
      }
    
      @Override
      public PIDSourceType getPIDSourceType() {
        return PIDSourceType.kDisplacement;
      }
    };
    rotateRobot = new PIDOutput(){
    
      @Override
      public void pidWrite(double output) {
        Robot.drive.holonomicDrive(0, 0, output, true);
      }
    };

    rotateToTarget = new PIDController(0.1, 0, 0, targetXCoor, rotateRobot);
    rotateToTarget.setInputRange(-0.5, 0.5);
    rotateToTarget.setOutputRange(-0.2, 0.2);
    rotateToTarget.setAbsoluteTolerance(0.03);
    rotateToTarget.disable();
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
