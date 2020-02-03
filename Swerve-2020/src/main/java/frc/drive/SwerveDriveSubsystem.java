package frc.drive;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
// import frc.commands.testMoveSwerve;
import frc.robot.RobotMap;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.SwerveModifier;

public class SwerveDriveSubsystem extends Subsystem{
	// private static final double WHEELBASE = 12.5;
	private static final double WHEELBASE = 26.5;
	// private static final double TRACKWIDTH = 13.5;
	private static final double TRACKWIDTH = 33;
	// private static final double RATIO = Math.sqrt(Math.pow(WHEELBASE, 2) + Math.pow(TRACKWIDTH, 2));
	//Value ratio is never used

	public double adjustmentAngle = 0.0;
	public double prevAngle = 0.0;

	public CANSparkMax frontRightAngle = new CANSparkMax(RobotMap.frontRightAngleID, MotorType.kBrushless);
	public CANSparkMax frontRightDrive = new CANSparkMax(RobotMap.frontRightDriveID, MotorType.kBrushless);
	public CANSparkMax frontLeftAngle = new CANSparkMax(RobotMap.frontLeftAngleID, MotorType.kBrushless);
	public CANSparkMax frontLeftDrive = new CANSparkMax(RobotMap.frontLeftDriveID, MotorType.kBrushless);
	public CANSparkMax backLeftAngle = new CANSparkMax(RobotMap.backLeftAngleID, MotorType.kBrushless);
	public CANSparkMax backLeftDrive = new CANSparkMax(RobotMap.backLeftDriveID, MotorType.kBrushless);
	public CANSparkMax backRightAngle = new CANSparkMax(RobotMap.backRightAngleID, MotorType.kBrushless);
	public CANSparkMax backRightDrive = new CANSparkMax(RobotMap.backRightDriveID, MotorType.kBrushless);

	public AnalogInput frontRightAngleEncoder = new AnalogInput(RobotMap.frontRightEncoderID);
	public AnalogInput frontLeftAngleEncoder = new AnalogInput(RobotMap.frontLeftEncoderID);
	public AnalogInput backLeftAngleEncoder = new AnalogInput(RobotMap.backLeftEncoderID);
	public AnalogInput backRightAngleEncoder = new AnalogInput(RobotMap.backRightEncoderID);

	public CANEncoder backRightAngleCANCoder = backRightAngle.getEncoder();
	public CANEncoder frontRightAngleCANCoder = frontRightAngle.getEncoder();
	public CANEncoder frontLeftAngleCANCoder = frontLeftAngle.getEncoder();
	public CANEncoder backLeftAngleCANCoder = backLeftAngle.getEncoder();
	public CANEncoder backRightDriveCANCoder = backRightDrive.getEncoder();
	public CANEncoder frontRightDriveCANCoder = frontRightDrive.getEncoder();
	public CANEncoder frontLeftDriveCANCoder = frontLeftDrive.getEncoder();
	public CANEncoder backLeftDriveCANCoder = backLeftDrive.getEncoder();
	
	public PIDController frontRightAngleController = new PIDController(0.18, 0.0, 0.0);
	public PIDController frontLeftAngleController = new PIDController(0.18, 0.0, 0.0);
	public PIDController backLeftAngleController = new PIDController(0.18, 0.0, 0.0);
	public PIDController backRightAngleController = new PIDController(0.18, 0.0, 0.0);
	
	public PIDController frontRightDriveController = new PIDController(0.00004, 0.0, 0.0);
	public PIDController frontLeftDriveController = new PIDController(0.00004, 0.0, 0.0);
	public PIDController backLeftDriveController = new PIDController(0.00004, 0.0, 0.0);
	public PIDController backRightDriveController = new PIDController(0.00004, 0.0, 0.0);
	
	
	public PIDController rotationJoyAngleController = new PIDController(0.000375 * 1.25, 0.0, 0.00002);
	
	public Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.05, RobotMap.empirical_free_velocity, 10.0, 1.0);
	
	public SwerveModifier modifier;

	public EncoderFollower flFollower;
	public EncoderFollower frFollower;
	public EncoderFollower blFollower;
	public EncoderFollower brFollower;
	/*
	 * 0 is Front Right
	 * 1 is Front Left
	 * 2 is Back Left
	 * 3 is Back Right
	 */
	public SwerveDriveModule[] mSwerveModules = new SwerveDriveModule[] {
		new SwerveDriveModule(0, frontRightAngle, frontRightDrive, frontRightAngleController, frontRightDriveController, frontRightAngleEncoder, RobotMap.frontRightAngleOffset),
		new SwerveDriveModule(1, frontLeftAngle, frontLeftDrive, frontLeftAngleController, frontLeftDriveController, frontLeftAngleEncoder, RobotMap.frontLeftAngleOffset),
		new SwerveDriveModule(2, backLeftAngle, backLeftDrive, backLeftAngleController, backLeftDriveController, backLeftAngleEncoder, RobotMap.backLeftAngleOffset),
		new SwerveDriveModule(3, backRightAngle, backRightDrive, backRightAngleController, backRightDriveController, backRightAngleEncoder, RobotMap.backRightAngleOffset)
	};

	public AHRS mNavX = new AHRS(SPI.Port.kMXP, (byte) 200);

	public SwerveDriveSubsystem() {
		mNavX.zeroYaw();

		mSwerveModules[0].getDriveMotor().setInverted(true);
		mSwerveModules[1].getDriveMotor().setInverted(false);
		mSwerveModules[2].getDriveMotor().setInverted(false);
		mSwerveModules[3].getDriveMotor().setInverted(true);

		mSwerveModules[0].getAngleMotor().setInverted(true);
		mSwerveModules[3].getAngleMotor().setInverted(true);

		for (SwerveDriveModule module : mSwerveModules) {
			module.getAnglePIDController().enableContinuousInput(0, 2 * Math.PI);
			module.getAnglePIDController().setTolerance(Math.toRadians(3.0));
			module.getDrivePIDController().setTolerance(250);
			module.getDriveMotor().getEncoder().setPosition(0);
			// module.getPIDController().setSetpoint(0);
		}
		
		// fieldOrientedController.setInputRange(0, 360);
		// fieldOrientedController.setAbsoluteTolerance(3.0);
		// fieldOrientedController.setOutputRange(-0.5, 0.5);
		// fieldOrientedController.setContinuous(true);

		rotationJoyAngleController.enableContinuousInput(-180, 180);
		rotationJoyAngleController.setTolerance(3);
	}

	public AHRS getNavX() {
		return mNavX;
	}

	public double getGyroAngle() {
		// return temp_gyro.getAngle();
		return (mNavX.getAngle());
	}

	public double getGyroRate() {
		return mNavX.getRate();
	}

	public double getRawGyroAngle() {
		return mNavX.getAngle();
	}

	public void holonomicDrive(double forward, double strafe, double rotation, boolean iFO) {
		if (iFO) {
			double angleRad = Math.toRadians(mNavX.getYaw() + 180);
			double temp = forward * Math.cos(angleRad) + strafe * Math.sin(angleRad);
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
/*
	 * 0 is Front Right
	 * 1 is Front Left
	 * 2 is Back Left
	 * 3 is Back Right
*/
		double[] speeds = new double[]{
				Math.sqrt(b * b + c * c), //Front right
				Math.sqrt(b * b + d * d), //Front left
				Math.sqrt(a * a + d * d), //Back left
				Math.sqrt(a * a + c * c) //Back right
		};

		double max = speeds[0];

		for (double speed : speeds) {
			if (speed > max) {
				max = speed;
			}
		}

		for (int i = 0; i < 4; i++) {
				double angle = angles[i];
				double currentAngle = mSwerveModules[i].readAngle();
				// Old version
				mSwerveModules[i].getAngleMotor().set(mSwerveModules[i].getAnglePIDController().calculate(currentAngle, Math.toRadians(angle + 180)));
				// mSwerveModules[i].setTargetAngle(angle + 180);
				// mSwerveModules[i].setTargetAngle(angle);
				mSwerveModules[i].setTargetSpeed(speeds[i]);
		}
	}
	
	public void stopAllMotors() {
		for (SwerveDriveModule module : mSwerveModules) {
			module.setTargetSpeed(0);
			module.getAngleMotor().set(0);
		}
	}

	public static void toggleDriveInverted(SwerveDriveModule module){
		if (module.getDriveMotor().getInverted()) module.getDriveMotor().setInverted(false);
		else module.getDriveMotor().setInverted(true);
	}

	@Override
	protected void initDefaultCommand() {
		// testMoveSwerve testMove = new testMoveSwerve();
		// testMove.start();
	}
}