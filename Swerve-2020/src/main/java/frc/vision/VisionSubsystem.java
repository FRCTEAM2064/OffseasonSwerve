/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.vision;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.vision.drivers.Limelight;
import frc.vision.drivers.Limelight.CamMode;
import frc.vision.drivers.Limelight.LedMode;
import frc.robot.OI;
import frc.robot.Robot;

public class VisionSubsystem extends PIDSubsystem {

  public Limelight firstLime;
  public PIDController rotateToTarget;

  public VisionSubsystem() {
    super("VisionSubsystem", 0.0075, 0, 0);
    
    firstLime = new Limelight(NetworkTableInstance.getDefault().getTable("limelight-first"));
    firstLime.setCamMode(CamMode.VISION);
    // firstLime.setLedMode(LedMode.ON);
    firstLime.setPipeline(0);
    setAbsoluteTolerance(1);
    
    
    // rotateToTarget = new PIDController(0.00125, 0, 0);
  }

  public double returnRotationValue(){
    return rotateToTarget.calculate(Robot.vision.firstLime.table.getEntry("tx").getDouble(126.0), 0);
  }

  @Override
  public void initDefaultCommand(){

  }

  @Override
  protected double returnPIDInput() {
    return firstLime.table.getEntry("tx").getDouble(0);
  }

  @Override
  protected void usePIDOutput(double output) {
    Robot.drive.holonomicDrive(OI.getlYval(), OI.getlXval(), -output, true); //TODO: may have to change back to positive output.
    // Robot.drive.holonomicDrive(OI.getlYval(), OI.getlXval(), OI.getrXval(), true);
  }
}