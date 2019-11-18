package frc.drive;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SPI;
import frc.robot.RobotMap;

public class SwerveDriveSubsystem extends HolonomicDrivetrain {
	// private static final double WHEELBASE = 12.5;
	private static final double WHEELBASE = 24;
	// private static final double TRACKWIDTH = 13.5;
	private static final double TRACKWIDTH = 25;
	// private static final double RATIO = Math.sqrt(Math.pow(WHEELBASE, 2) + Math.pow(TRACKWIDTH, 2));
	//Value ratio is never used

	private CANSparkMax frontRightAngle = new CANSparkMax(RobotMap.frontRightAngleID, MotorType.kBrushless);
	private CANSparkMax frontRightDrive = new CANSparkMax(RobotMap.frontRightDriveID, MotorType.kBrushless);
	private CANSparkMax frontLeftAngle = new CANSparkMax(RobotMap.frontLeftAngleID, MotorType.kBrushless);
	private CANSparkMax frontLeftDrive = new CANSparkMax(RobotMap.frontLeftDriveID, MotorType.kBrushless);
	private CANSparkMax backLeftAngle = new CANSparkMax(RobotMap.backLeftAngleID, MotorType.kBrushless);
	private CANSparkMax backLeftDrive = new CANSparkMax(RobotMap.backLeftDriveID, MotorType.kBrushless);
	private CANSparkMax backRightAngle = new CANSparkMax(RobotMap.backRightAngleID, MotorType.kBrushless);
	private CANSparkMax backRightDrive = new CANSparkMax(RobotMap.backRightDriveID, MotorType.kBrushless);

	public AnalogInput frontRightAngleEncoder = new AnalogInput(RobotMap.frontRightEncoderID);
	public AnalogInput frontLeftAngleEncoder = new AnalogInput(RobotMap.frontLeftEncoderID);
	public AnalogInput backLeftAngleEncoder = new AnalogInput(RobotMap.backLeftEncoderID);
	public AnalogInput backRightAngleEncoder = new AnalogInput(RobotMap.backRightEncoderID);

	private PIDSource frontRightEncoderValue = new PIDSource(){
	
		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {
			pidSource = 
		}
	
		@Override
		public double pidGet() {
			return SwerveDriveModule.readAngle(frontRightAngleEncoder);
		}
	
		@Override
		public PIDSourceType getPIDSourceType() {
			return null;
		}
	};
	private PIDOutput frontRightAngleOutput = new PIDOutput(){
	
		@Override
		public void pidWrite(double output) {
			frontRightAngle.set(output);
		}
	};
	private PIDOutput frontLeftAngleOutput = new PIDOutput(){
	
		@Override
		public void pidWrite(double output) {
			frontLeftAngle.set(output);
		}
	};
	private PIDOutput backRightAngleOutput = new PIDOutput(){
	
		@Override
		public void pidWrite(double output) {
			backRightAngle.set(output);
		}
	};
	private PIDOutput backLeftAngleOutput = new PIDOutput(){
	
		@Override
		public void pidWrite(double output) {
			backLeftAngle.set(output);
		}
	};

	private PIDController frontRightAngleController = new PIDController(0.5, 0.0, 0.0001, SwerveDriveModule.readAngle(frontRightAngleEncoder), frontRightAngleOutput);
	private PIDController frontLeftAngleController = new PIDController(frontLeftAngle);
	private PIDController backLeftAngleController = new PIDController(backLeftAngle);
	private PIDController backRightAngleController = new PIDController(backRightAngle);

	/*
	 * 0 is Front Right
	 * 1 is Front Left
	 * 2 is Back Left
	 * 3 is Back Right
	 */
	public SwerveDriveModule[] mSwerveModules = new SwerveDriveModule[] {
		new SwerveDriveModule(0, frontRightAngle, frontRightDrive, frontRightAngleController, frontRightAngleEncoder, 0),
		new SwerveDriveModule(1, frontLeftAngle, frontLeftDrive, frontLeftAngleController, frontLeftAngleEncoder, 0),
		new SwerveDriveModule(2, backLeftAngle, backLeftDrive, backLeftAngleController, backLeftAngleEncoder, 0),
		new SwerveDriveModule(3, backRightAngle, backRightDrive, backRightAngleController, backRightAngleEncoder, 0)
	};

	public AHRS mNavX = new AHRS(SPI.Port.kMXP, (byte) 200);

	public SwerveDriveSubsystem() {
		zeroGyro();

		mSwerveModules[0].getDriveMotor().setInverted(true);
		mSwerveModules[1].getDriveMotor().setInverted(true);
		mSwerveModules[2].getDriveMotor().setInverted(true);
		mSwerveModules[3].getDriveMotor().setInverted(true);

		mSwerveModules[0].getAngleMotor().setInverted(true);
		mSwerveModules[3].getAngleMotor().setInverted(true);


		for (SwerveDriveModule module : mSwerveModules) {
			module.setTargetAngle(0);
		}
		frontRightAngleController.disable();
		frontLeftAngleController.disable();
		backRightAngleController.disable();
		backLeftAngleController.disable();
	}

	public AHRS getNavX() {
		return mNavX;
	}

	public double getGyroAngle() {
		return (mNavX.getAngle() - getAdjustmentAngle());
	}

	public double getGyroRate() {
		return mNavX.getRate();
	}

	public double getRawGyroAngle() {
		return mNavX.getAngle();
	}

	public SwerveDriveModule getSwerveModule(int i) {
		return mSwerveModules[i];
	}

	@Override
	public void holonomicDrive(double forward, double strafe, double rotation) {
		forward *= getSpeedMultiplier();
		strafe *= getSpeedMultiplier();
		if (isFieldOriented()) {
			double angleRad = Math.toRadians(getGyroAngle());
			double temp = forward * Math.cos(angleRad) +
					strafe * Math.sin(angleRad);
			strafe = -forward * Math.sin(angleRad) + strafe * Math.cos(angleRad);
			forward = temp;
		}

		double a = strafe - rotation * (WHEELBASE / TRACKWIDTH);
		double b = strafe + rotation * (WHEELBASE / TRACKWIDTH);
		double c = forward - rotation * (TRACKWIDTH / WHEELBASE);
		double d = forward + rotation * (TRACKWIDTH / WHEELBASE);

		double[] angles = new double[]{
				Math.atan2(b, c) * 180 / Math.PI,
				Math.atan2(b, d) * 180 / Math.PI,
				Math.atan2(a, d) * 180 / Math.PI,
				Math.atan2(a, c) * 180 / Math.PI
		};

		double[] speeds = new double[]{
				Math.sqrt(b * b + c * c),
				Math.sqrt(b * b + d * d),
				Math.sqrt(a * a + d * d),
				Math.sqrt(a * a + c * c)
		};

		double max = speeds[0];

		for (double speed : speeds) {
			if (speed > max) {
				max = speed;
			}
		}

		for (int i = 0; i < 4; i++) {
			if (Math.abs(forward) > 0.05 ||
			    Math.abs(strafe) > 0.05 ||
			    Math.abs(rotation) > 0.05) {
				mSwerveModules[i].setTargetAngle(angles[i] + 180);
			} else {
				mSwerveModules[i].setTargetAngle(mSwerveModules[i].getTargetAngle());
			}
			mSwerveModules[i].setTargetSpeed(speeds[i]);
		}
	}

	@Override
	public void stopDriveMotors() {
		for (SwerveDriveModule module : mSwerveModules) {
			module.setTargetSpeed(0);
		}
	}
}
