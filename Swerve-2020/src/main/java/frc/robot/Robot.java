/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.drive.SwerveDriveSubsystem;
import frc.vision.VisionSubsystem;


/*
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  public static SwerveDriveSubsystem drive;
  public static VisionSubsystem vision;
  public static int numOfIterations = 0;
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    drive = new SwerveDriveSubsystem();
    vision = new VisionSubsystem();
    
    
    drive.mNavX.reset();
    // drive.backLeftAngleController.enable();
    // drive.backRightAngleController.enable();
    // drive.frontLeftAngleController.enable();
    // drive.frontRightAngleController.enable();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    
    switch (m_autoSelected) {
      case kCustomAuto:

        break;
      case kDefaultAuto:
      default:
      
        break;
    }
  }

  @Override
  public void teleopInit(){
    // vision.rotateToTarget.enable();
    // drive.frontRightAngleController.setSetpoint(0);
  }
  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    // drive.frontLeftAngle.set(drive.frontLeftAngleController.calculate(drive.mSwerveModules[1].readAngle(), Math.toRadians(90)));
    // drive.frontRightAngle.set(drive.frontRightAngleController.calculate(drive.mSwerveModules[0].readAngle(), Math.toRadians(90)));
    // drive.backLeftAngle.set(drive.backLeftAngleController.calculate(drive.mSwerveModules[2].readAngle(), Math.toRadians(90)));
    // drive.backRightAngle.set(drive.backRightAngleController.calculate(drive.mSwerveModules[3].readAngle(), Math.toRadians(90)));

    // System.out.println(Math.toDegrees(drive.mSwerveModules[1].readAngle()));
    // System.out.println(Math.toDegrees(drive.mSwerveModules[0].readAngle()));
    // System.out.println(Math.toDegrees(drive.mSwerveModules[2].readAngle()));
    // System.out.println(Math.toDegrees(drive.mSwerveModules[3].readAngle()));

    // OI.previous_strafe_vals[numOfIterations] = OI.getlXval();
    // if ((OI.previous_strafe_vals[0] * -1 > 0 && OI.getlXval() < 0) || (OI.previous_strafe_vals[0] * -1 < 0 && OI.getlXval() > 0)){
    //   drive.holonomicDrive(OI.getlYval(), OI.getlXval(), OI.getrXval(), false, true);
    // }
    // else{
      drive.holonomicDrive(OI.getlYval(), OI.getlXval(), OI.getrXval(), false, false);
    // }
    // double rotation = drive.rotationJoyAngleController.calculate(drive.mNavX.getYaw(), OI.getrAngle());
    // System.out.println("X coordinate" + vision.firstLime.getTargetPosition().x);
    // vision.rotateToTarget.setSetpoint(0);
    
 //First check if this works
    // System.out.println(drive.mNavX.getYaw()); //TODO: Readings aren't very accurate
    // drive.holonomicDrive(-OI.getlYval(), OI.getlXval(), rotation, false); //TODO: TEST LEVI's ROTATION THING
    
    //Step 6: Trapezoidal motion profile for drive motor
    // System.out.println(OI.getrAngle());
    numOfIterations++;
    numOfIterations %= OI.previous_strafe_vals.length;
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {

  }
  @Override
  public void disabledInit(){

  }
  @Override
  public void disabledPeriodic(){

  }
}
