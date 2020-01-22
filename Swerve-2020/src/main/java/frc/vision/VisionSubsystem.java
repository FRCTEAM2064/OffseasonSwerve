/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.vision;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.controller.PIDController;
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

  
  public Ultrasonic Usonic;
  public Limelight firstLime;
  public PIDController rotateToTarget;
  public VisionSubsystem(){
    firstLime = new Limelight(NetworkTableInstance.getDefault().getTable(FIRST_LIMELIGHT_TABLE_NAME));
    firstLime.setCamMode(CamMode.VISION);
    firstLime.setLedMode(LedMode.ON);
    firstLime.setPipeline(0);

    targetXCoor = new PIDSource(){
    
      
      @Override
      public void pidWrite(double output) {
        Robot.drive.holonomicDrive(0, 0, output, true, false);
      }
    };

    rotateToTarget = new PIDController(0.1, 0, 0);
    rotateToTarget.setInputRange(-0.5, 0.5);
    rotateToTarget.setOutputRange(-0.2, 0.2);
    rotateToTarget.setAbsoluteTolerance(0.03);
    rotateToTarget.disableContinuousOutput();
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  public double getAngle(){
    int dist = Usonic.getRangeMM() * 1000;

  }
  public double centeringRobot(){
    double x = firstLime.table.getEntry("tx");
    double y = firstLime.table.getEntry("ty");
    float KpAim = -0.1;
    float KpDistance = -0.1;
    float min_aim_command = 0.05;
    if(OI.rb1.get()){
      float heading_error = -x;
      float distance_error = -y;
      float steering_adjust = 0.0;
      if(x>1.0){
        steering_adjust = KpAim*heading_error - min_aim_command;
      }
      else if(x<1.0){
        steering_adjust = KpAim*heading_error + min_aim_command;
      }
      float distance_adjust = KpDistance + distance_error;
      return steering_adjust + distance_adjust;
    }
  }
}
