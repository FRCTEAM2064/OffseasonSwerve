package frc.drive;

import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.RobotController;
import frc.commands.SwerveModuleCommand;
import frc.robot.RobotMap;
import frc.util.MotorStallException;

public class SwerveDriveModule extends Subsystem {
	private static final long STALL_TIMEOUT = 2000;

	private long mStallTimeBegin = Long.MAX_VALUE;

	private double mLastError = 0, mLastTargetAngle = 0;

	private final int mModuleNumber;

	private static double angleOffset;

	private final CANSparkMax mAngleMotor;
	private final CANSparkMax mDriveMotor;

	private final PIDController mAngleController;

	private final AnalogInput mAngleEnc;

	public SwerveDriveModule(int moduleNumber, CANSparkMax angleMotor, CANSparkMax driveMotor,PIDController angleController, AnalogInput angleEnc, double angleOffset) {
		mModuleNumber = moduleNumber;

		mAngleMotor = angleMotor;
		mDriveMotor = driveMotor;

		mAngleController = angleController;

		mAngleEnc = angleEnc;

		this.angleOffset = angleOffset;

		//angleMotor.changeControlMode(CANSparkMax.TalonControlMode.Position);
		
		//angleMotor.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogEncoder);
		
		// angleMotor.reverseSensor(true);
		angleMotor.setInverted(true);
		// angleMotor.setPID(20, 0, 200); // P: 20, I: 0, D: 200
		//Already done in SwerveDriveSubsystem

		// angleMotor.set(0);
		angleMotor.set(0);
		// angleMotor.enableControl();
		
		// driveMotor.enableBrakeMode(true);
		
		// Set amperage limits
		// angleMotor.setCurrentLimit(50);
		angleMotor.setSmartCurrentLimit(50);
		// angleMotor.EnableCurrentLimit(true);

		// driveMotor.setCurrentLimit(50);
		driveMotor.setSmartCurrentLimit(50);
		// driveMotor.EnableCurrentLimit(true);
	
		// angleEnc.setPositionConversionFactor(RobotMap.encUnitsPerRot); //Assuming the encoder returns 1/1024 as a dec for one encoder unit
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new SwerveModuleCommand(this));
	}

	public CANSparkMax getAngleMotor() {
		return mAngleMotor;
	}

	public CANSparkMax getDriveMotor() {
		return mDriveMotor;
	}

	public double getTargetAngle() {
		return mLastTargetAngle;
	}

	public void robotDisabledInit() {
		mStallTimeBegin = Long.MAX_VALUE;
	}

	public void setTargetAngle(double targetAngle) {
		mLastTargetAngle = targetAngle;

		targetAngle %= 360;
		targetAngle += angleOffset;

		// double currentAngle = mAngleMotor.getPosition() * (360.0 / 1024.0);
		double currentAngle = mAngleEnc.getValue() * (360.0 / RobotMap.encUnitsPerRot);
		double currentAngleMod = currentAngle % 360;
		if (currentAngleMod < 0) currentAngleMod += 360;

		double delta = currentAngleMod - targetAngle;

		if (delta > 180) {
			targetAngle += 360;
		} else if (delta < -180) {
			targetAngle -= 360;
		}

		delta = currentAngleMod - targetAngle;
		if (delta > 90 || delta < -90) {
			if (delta > 90)
				targetAngle += 180;
			else if (delta < -90)
				targetAngle -= 180;
			mDriveMotor.setInverted(false);
		} else {
			mDriveMotor.setInverted(true);
		}

		targetAngle += currentAngle - currentAngleMod;

		// double currentError = mAngleMotor.getError();
		double currentError = Math.abs(targetAngle * (RobotMap.encUnitsPerRot/360.0) - mAngleEnc.getValue());
		if (Math.abs(currentError - mLastError) < 7.5 &&
				Math.abs(currentAngle - targetAngle) > 5) {
			if (mStallTimeBegin == Long.MAX_VALUE) mStallTimeBegin = System.currentTimeMillis();
			if (System.currentTimeMillis() - mStallTimeBegin > STALL_TIMEOUT) {
				throw new MotorStallException(String.format("Angle motor on swerve module '%d' has stalled.",
						mModuleNumber));
			}
		} else {
			mStallTimeBegin = Long.MAX_VALUE;
		}
		mLastError = currentError;


		// targetAngle *= 1024.0 / 360.0;
		targetAngle *= RobotMap.encUnitsPerRot / 360.0;
		// mAngleMotor.setSetpoint(targetAngle);
		mAngleController.setSetpoint(targetAngle);
	}

	public void setTargetSpeed(double speed) {
		mDriveMotor.set(speed);
	}

	
    public static double readAngle(AnalogInput angleEncoder) {
        double angle = (1.0 - angleEncoder.getVoltage() / RobotController.getVoltage5V()) * 2.0 * Math.PI + angleOffset;
        angle %= 2.0 * Math.PI;
        if (angle < 0.0) {
            angle += 2.0 * Math.PI;
        }

        return angle;
    }
}
