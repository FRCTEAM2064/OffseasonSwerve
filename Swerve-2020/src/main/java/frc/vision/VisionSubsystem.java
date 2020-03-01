/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.vision;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.vision.drivers.Limelight;
import frc.vision.drivers.Limelight.CamMode;

public class VisionSubsystem extends Subsystem {

  public Limelight firstLime;
  public PIDController rotateToTarget;

  public VisionSubsystem() {    
    firstLime = new Limelight(NetworkTableInstance.getDefault().getTable("limelight-first"));
    firstLime.setCamMode(CamMode.VISION);
    // firstLime.setLedMode(LedMode.ON);
    firstLime.setPipeline(0);
    
    rotateToTarget = new PIDController(0.0075, 0, 0);
    rotateToTarget.setTolerance(2);
  }

  public double returnRotationValue(){
    return rotateToTarget.calculate(firstLime.table.getEntry("tx").getDouble(15.0), 0);
  }

  @Override
  public void initDefaultCommand(){

  }
}