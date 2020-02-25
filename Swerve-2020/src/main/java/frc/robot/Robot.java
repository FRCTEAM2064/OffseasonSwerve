/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.drive.SwerveDriveSubsystem;
import frc.vision.VisionSubsystem;
import manipulators.ClimbingSubsystem;
import manipulators.ControlPanelSubsystem;
import manipulators.IntakeSubsystem;
/*
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
import manipulators.ShooterSubsystem;
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  public static OI oi;
  public static SwerveDriveSubsystem drive;
  public static VisionSubsystem vision;
  public static IntakeSubsystem intake;
  public static ShooterSubsystem shooter;
  public static ControlPanelSubsystem controlPanel;
  public static ClimbingSubsystem climb;
  public static I2C arduino;
  public static int numOfIterations = 0;
  public Compressor compressor;
  

  public UsbCamera driverCam;
  
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    
    driverCam = CameraServer.getInstance().startAutomaticCapture();
    driverCam.setFPS(24);
    driverCam.setPixelFormat(PixelFormat.kMJPEG);
    driverCam.setResolution(360, 240);
    drive = new SwerveDriveSubsystem();
    vision = new VisionSubsystem();
    controlPanel = new ControlPanelSubsystem();
    climb = new ClimbingSubsystem();
    shooter = new ShooterSubsystem();
    intake = new IntakeSubsystem();
    oi = new OI();
    compressor = new Compressor();
    arduino = new I2C(I2C.Port.kOnboard, 168);

    Scheduler.getInstance().enable();
    
    // drive.mNavX.reset();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * 
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // driverCam = CameraServer.getInstance().startAutomaticCapture();
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
    Scheduler.getInstance().close();
    Scheduler.getInstance().enable();
  }
  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    // Robot.drive.testMotors();
    // System.out.println(Robot.shooter.shooter_encoder.getVelocity());
    // System.out.println(Robot.shooter.shooter_encoder.getVelocity()/360);
    Robot.drive.update();
    // System.out.println(Robot.drive.mSwerveModules[1].readAngle());
    // System.out.println(Robot.climb.careful.getPosition());
    // System.out.println("ta " + vision.firstLime.table.getEntry("ta").getDouble(126.0));
    System.out.println(Robot.arduino.addressOnly()); //SHOULD BE FALSE; MAKE SURE TO FIRST DEPLOY CODE ONTO ARDUINO
    // System.out.println("tx " + vision.firstLime.table.getEntry("tx").getDouble(126.0));
    // System.out.println("ty " + vision.firstLime.table.getEntry("ty").getDouble(126.0));
    // System.out.println("tl " + vision.firstLime.table.getEntry("tl").getDouble(126.0));
    // System.out.println(Robot.vision.rotateToTarget.atSetpoint());
    // System.out.println(Robot.drive.mSwerveModules[0].readAngle());
    // System.out.println(Robot.controlPanel.readColorString(Robot.controlPanel.m_colorMatcher.matchClosestColor(Robot.controlPanel.colorSensor.getColor())));

    // Robot.drive.calibrateNavX();
// System.out.println(Robot.controlPanel.readColorString(Robot.controlPanel.m_colorMatcher.matchClosestColor(Robot.controlPanel.colorSensor.getColor())));
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
