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
    
    rotateToTarget = new PIDController(0.007, 0, 0.0005);
    rotateToTarget.setTolerance(2.5);
  }
//we see everything
  public double returnRotationValue(){
    if (firstLime.table.getEntry("tx").getDouble(100.0) == 100.0) return 0.2;
    else if (Math.abs(firstLime.table.getEntry("tx").getDouble(100.0)) < 2.5) return 0;
    else return rotateToTarget.calculate(firstLime.table.getEntry("tx").getDouble(100.0), 0);
  }
//you cannot hide from us
  @Override
  public void initDefaultCommand(){

  }
}