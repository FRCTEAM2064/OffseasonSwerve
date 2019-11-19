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
import frc.drive.SwerveDriveModule;
import frc.drive.SwerveDriveSubsystem;

/**
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
  public SwerveDriveSubsystem drive;
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

    // drive.frontRightAngleController.enable();
    drive.frontLeftAngleController.enable();
    // drive.backRightAngleController.enable();
    // drive.backLeftAngleController.enable();
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
        drive.frontLeftAngleController.enable();
        drive.frontLeftAngleController.setSetpoint(Math.toRadians(180));
        break;
      case kDefaultAuto:
      default:
      
        break;
    }
  }

  @Override
  public void teleopInit(){
    drive.frontLeftAngleController.disable();
  }
  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    drive.getSwerveModule(1).getAngleMotor().set(OI.getrYval());
    if (OI.quickRotLeft()){
      System.out.println(SwerveDriveModule.readAngle(drive.frontLeftAngleEncoder)*180/(Math.PI));
    }
    
    //Step 4: Motor moving wheel to specific angle using encoder
    // drive.mSwerveModules[0].setTargetAngle(0);
    //Step 5: Full swerve functionality with joystick
    // drive.holonomicDrive(OI.getlYval(), OI.getlXval(), OI.getrXval());
    //Step 6: Trapezoidal motion profile for drive motor
    
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
