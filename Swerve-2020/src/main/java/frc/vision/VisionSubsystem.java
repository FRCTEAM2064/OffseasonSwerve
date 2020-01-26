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
import frc.vision.drivers.Limelight.LedMode;

/**
 * Add your docs here.
 */
public class VisionSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public Limelight firstLime;
  public PIDController rotateToTarget;

  public VisionSubsystem() {
    firstLime = new Limelight(NetworkTableInstance.getDefault().getTable("limelight-first"));
    firstLime.setCamMode(CamMode.VISION);
    firstLime.setLedMode(LedMode.ON);
    firstLime.setPipeline(0);

    rotateToTarget = new PIDController(0.27, 0, 0);
    rotateToTarget.setTolerance(0.5);
    rotateToTarget.disableContinuousInput();
  }
  @Override
  public void initDefaultCommand(){}

  public double centeringRobotPID(){
    // System.out.println(rotateToTarget.calculate(firstLime.table.getInstance().getEntry("tx").getDouble(0.0), 0));
    return rotateToTarget.calculate(firstLime.table.getInstance().getEntry("tx").getDouble(0), 0);
  }


  // public double centeringRobot(){
  //   double x = firstLime.table.getEntry("tx");
  //   double y = firstLime.table.getEntry("ty");
  //   float KpAim = -0.1;
  //   float KpDistance = -0.1;
  //   float min_aim_command = 0.05;
  //   if(OI.rb1.get()){
  //     float heading_error = -x;
  //     float distance_error = -y;
  //     float steering_adjust = 0.0;
  //     if(x>1.0){
  //       steering_adjust = KpAim*heading_error - min_aim_command;
  //     }
  //     else if(x<1.0){
  //       steering_adjust = KpAim*heading_error + min_aim_command;
  //     }
  //     float distance_adjust = KpDistance + distance_error;
  //     return steering_adjust + distance_adjust;
  //   }
  // }
}
