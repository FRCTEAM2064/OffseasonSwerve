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
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.drive.SwerveDriveSubsystem;
import frc.vision.VisionSubsystem;
<<<<<<< HEAD
<<<<<<< HEAD
import manipulators.ClimbingSubsystem;
=======
>>>>>>> 3676ba864874d636f635985bc552606c86d2739b
=======
>>>>>>> 3676ba864874d636f635985bc552606c86d2739b
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
<<<<<<< HEAD
<<<<<<< HEAD
  public static ShooterSubsystem shooter;
  public static ControlPanelSubsystem controlPanel;
  public static ClimbingSubsystem climb;
=======
  public static ControlPanelSubsystem controlPanel;
>>>>>>> 3676ba864874d636f635985bc552606c86d2739b
=======
  public static ControlPanelSubsystem controlPanel;
>>>>>>> 3676ba864874d636f635985bc552606c86d2739b
  public static int numOfIterations = 0;

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
    // intake = new IntakeSubsystem();
    controlPanel = new ControlPanelSubsystem();
    oi = new OI();
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

  }
  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 3676ba864874d636f635985bc552606c86d2739b
=======
>>>>>>> 3676ba864874d636f635985bc552606c86d2739b
    Scheduler.getInstance().run();
    oi.getCommands();
    // System.out.println(vision.firstLime.table.getEntry("tx").getDouble(0.0));
    // System.out.println(Robot.controlPanel.readColorString(Robot.controlPanel.m_colorMatcher.matchClosestColor(Robot.controlPanel.colorSensor.getColor())));
    // drive.holonomicDrive(-OI.getlYval(), OI.getlXval(), OI.getrXval(), true);
    // drive.frontRightDrive.set(1);
    // System.out.println(drive.frontRightDriveCANCoder.getVelocity());
    // Swerve Test code
    // drive.frontLeftAngle.set(drive.frontLeftAngleController.calculate(drive.mSwerveModules[1].readAngle(), Math.toRadians(90)));
    // drive.frontRightAngle.set(drive.frontRightAngleController.calculate(drive.mSwerveModules[0].readAngle(), Math.toRadians(90)));
    // drive.backLeftAngle.set(drive.backLeftAngleController.calculate(drive.mSwerveModules[2].readAngle(), Math.toRadians(90)));
    // drive.backRightAngle.set(drive.backRightAngleController.calculate(drive.mSwerveModules[3].readAngle(), Math.toRadians(90)));
<<<<<<< HEAD

    // System.out.println(Math.toDegrees(drive.mSwerveModules[1].readAngle()));
    // System.out.println(Math.toDPegrees(drive.mSwerveModules[0].readAngle()));
    // System.out.println(Math.toDegrees(drive.mSwerveModules[2].readAngle()));
    // System.out.println(Math.toDegrees(drive.mSwerveModules[3].readAngle()));

=======

    // System.out.println(Math.toDegrees(drive.mSwerveModules[1].readAngle()));
    // System.out.println(Math.toDPegrees(drive.mSwerveModules[0].readAngle()));
    // System.out.println(Math.toDegrees(drive.mSwerveModules[2].readAngle()));
    // System.out.println(Math.toDegrees(drive.mSwerveModules[3].readAngle()));

>>>>>>> 3676ba864874d636f635985bc552606c86d2739b
    // OI.previous_strafe_vals[numOfIterations] = OI.getlXval();
    double rotation = drive.rotationJoyAngleController.calculate(drive.mNavX.getYaw(), OI.getrAngle());
    //System.out.println(OI.getrAngle());
 //First check if this works
    // System.out.println(drive.mNavX.getYaw()); //TODO: Readings aren't very accurate
    if (Math.abs(rotation) < 0.05) rotation = 0;
    drive.holonomicDrive(-OI.getlYval(), OI.getlXval(), rotation, true); //TODO: TEST LEVI's ROTATION THING
<<<<<<< HEAD
<<<<<<< HEAD
=======
    // Scheduler.getInstance().run();
    // oi.getCommands();
    // Robot.drive.update();
    // System.out.println(drive.rTFRDEncVal(drive.previous_FRenc));
    // Robot.drgive.calibrateNavX();
    // Robot.drive.testMotors();
=======
=======
>>>>>>> 3676ba864874d636f635985bc552606c86d2739b
  }
>>>>>>> 3676ba864874d636f635985bc552606c86d2739b

    // System.out.println(vision.firstLime.table.getEntry("tx").getDouble(0.0));
    // System.out.println(Robot.controlPanel.readColorString(Robot.controlPanel.m_colorMatcher.matchClosestColor(Robot.controlPanel.colorSensor.getColor())));
    // System.out.println("Front Right Drive Encoder:" + Robot.drive.mSwerveModules[0].getDriveEncoderVal());
    // drive.frontRihtDrive.set(1);
    // System.out.println(drive.frontRightDriveCANCoder.getVelocity());
>>>>>>> 
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
