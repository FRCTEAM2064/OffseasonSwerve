package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.autonomous.AutonomousSelector;
import frc.autonomous.AutonomousTrajectories;
import frc.subsystems.*;
import frc.api.Gamepiece;
import frc.common.drivers.Limelight;
import frc.common.subsystems.SubsystemManager;

public class Robot extends TimedRobot {
    private static final double UPDATE_DT = 5e-3; // 5 ms

    private final SubsystemManager subsystemManager = new SubsystemManager(
            ClimberSubsystem.getInstance(),
            DrivetrainSubsystem.getInstance(),
            CargoGrabberSubsystem.getInstance(),
            CargoArmSubsystem.getInstance(),
            HatchFloorGathererSubsystem.getInstance(),
            HatchPlacerSubsystem.getInstance(),
            VisionSubsystem.getInstance()
    );

    private static final OI oi = new OI();

    private AutonomousTrajectories autonomousTrajectories = new AutonomousTrajectories(DrivetrainSubsystem.CONSTRAINTS);
    private AutonomousSelector autonomousSelector = new AutonomousSelector(autonomousTrajectories);

    private Command autonomousCommand = null;

    public Robot() {
        oi.bindButtons(autonomousSelector);
    }

    public static OI getOi() {
        return oi;
    }

    @Override
    public void robotInit() {
        SmartDashboard.putBoolean("Limelight Calibration Mode", false);

        subsystemManager.enableKinematicLoop(UPDATE_DT);
    }

    @Override
    public void robotPeriodic() {
    }

    @Override
    public void teleopInit() {
//        if (autonomousCommand != null) {
//            autonomousCommand.cancel();
//            autonomousCommand = null;
//        }
    }

    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {
        if (autonomousCommand != null) {
            autonomousCommand.cancel();
        }

        autonomousCommand = autonomousSelector.getCommand();
        autonomousCommand.start();
    }

    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void disabledInit() {
//        if (autonomousCommand != null) {
//            autonomousCommand.cancel();
//            autonomousCommand = null;
//        }
//        Scheduler.getInstance().removeAll();
    }

    @Override
    public void disabledPeriodic() {
        boolean calibrationMode = SmartDashboard.getBoolean("Limelight Calibration Mode", false);

        Limelight.CamMode mode = calibrationMode ? Limelight.CamMode.VISION : Limelight.CamMode.DRIVER;
        VisionSubsystem.getInstance().getLimelight(Gamepiece.HATCH_PANEL).setCamMode(mode);
        VisionSubsystem.getInstance().getLimelight(Gamepiece.CARGO).setCamMode(mode);
    }
}
