/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.vision;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.vision.Limelight.CamMode;
import frc.vision.Limelight.LedMode;

/**
 * Add your docs here.
 */
public class VisionSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private static final String FIRST_LIMELIGHT_TABLE_NAME = "limelight-first";


  public Limelight firstLime;

  public VisionSubsystem(){
    firstLime = new Limelight(NetworkTableInstance.getDefault().getTable(FIRST_LIMELIGHT_TABLE_NAME));
    firstLime.setCamMode(CamMode.VISION);
    firstLime.setLedMode(LedMode.ON);
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
