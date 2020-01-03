package frc.drive;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import frc.common.drivers.Gyroscope;
import frc.robot.OI;
import frc.robot.RobotMap;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.modifiers.SwerveModifier;

public class SwerveDriveSubsystem extends HolonomicDrivetrain {
	// private static final double WHEELBASE = 12.5;
	private static final double WHEELBASE = 24;
	// private static final double TRACKWIDTH = 13.5;
	private static final double TRACKWIDTH = 25;
	// private static final double RATIO = Math.sqrt(Math.pow(WHEELBASE, 2) + Math.pow(TRACKWIDTH, 2));
	//Value ratio is never used

	public double adjustmentAngle = 0.0;

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
			pidSource = PIDSourceType.kDisplacement;
		}
	
		@Override
		public double pidGet() {
			return SwerveDriveModule.readAngle(frontRightAngleEncoder, RobotMap.frontRightAngleOffset);
		}
	
		@Override
		public PIDSourceType getPIDSourceType() {
			return PIDSourceType.kDisplacement;
		}
	};
	private PIDSource frontLeftEncoderValue = new PIDSource(){
	
		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {
			pidSource = PIDSourceType.kDisplacement;
		}
	
		@Override
		public double pidGet() {
			return SwerveDriveModule.readAngle(frontLeftAngleEncoder, RobotMap.frontLeftAngleOffset);
		}
	
		@Override
		public PIDSourceType getPIDSourceType() {
			return PIDSourceType.kDisplacement;
		}
	};
	private PIDSource backRightEncoderValue = new PIDSource(){
	
		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {
			pidSource = PIDSourceType.kDisplacement;
		}
	
		@Override
		public double pidGet() {
			return SwerveDriveModule.readAngle(backRightAngleEncoder, RobotMap.backRightAngleOffset);
		}
	
		@Override
		public PIDSourceType getPIDSourceType() {
			return PIDSourceType.kDisplacement;
		}
	};
	private PIDSource backLeftEncoderValue = new PIDSource(){
	
		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {
			pidSource = PIDSourceType.kDisplacement;
		}
	
		@Override
		public double pidGet() {
			return SwerveDriveModule.readAngle(backLeftAngleEncoder, RobotMap.backLeftAngleOffset);
		}
	
		@Override
		public PIDSourceType getPIDSourceType() {
			return PIDSourceType.kDisplacement;
		}
	};
	// private PIDSource gyroscopeValue = new PIDSource(){
	// 	@Override
	// 	public void setPIDSourceType(PIDSourceType arg0) {	
	// 	}
	
	// 	@Override
	// 		public double pidGet() {
	// 			return mNavX.getYaw() + 180;
	// 		}

	// 	@Override
	// 	public PIDSourceType getPIDSourceType() {
	// 		return PIDSourceType.kDisplacement;
	// 	}
	// };
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
	// private void setAdjustmentAngle(double input){
	// 	adjustmentAngle = input;
	// }
	// private PIDOutput gyroscopeOutput = new PIDOutput(){
	
	// 	@Override
	// 	public void pidWrite(double input) {
	// 		setAdjustmentAngle(input);
	// 	}
	// };
	//Testing PID values on front left controller first before applying them to the other angle motors
	
	public PIDController frontRightAngleController = new PIDController(0.18, 0.0, 0.0, frontRightEncoderValue, frontRightAngleOutput);
	public PIDController frontLeftAngleController = new PIDController(0.18, 0.0, 0.0, frontLeftEncoderValue, frontLeftAngleOutput);
	public PIDController backLeftAngleController = new PIDController(0.18, 0.0, 0.0, backLeftEncoderValue, backLeftAngleOutput);
	public PIDController backRightAngleController = new PIDController(0.18, 0.0, 0.0, backRightEncoderValue, backRightAngleOutput);
	// public PIDController fieldOrientedController = new PIDController (0.12, 0.0, 0.0, gyroscopeValue, gyroscopeOutput);
	
	
	/*
	 * 0 is Front Right
	 * 1 is Front Left
	 * 2 is Back Left
	 * 3 is Back Right
	 */
	public SwerveDriveModule[] mSwerveModules = new SwerveDriveModule[] {
		new SwerveDriveModule(0, frontRightAngle, frontRightDrive, frontRightAngleController, frontRightAngleEncoder, RobotMap.frontRightAngleOffset),
		new SwerveDriveModule(1, frontLeftAngle, frontLeftDrive, frontLeftAngleController, frontLeftAngleEncoder, RobotMap.frontLeftAngleOffset),
		new SwerveDriveModule(2, backLeftAngle, backLeftDrive, backLeftAngleController, backLeftAngleEncoder, RobotMap.backLeftAngleOffset),
		new SwerveDriveModule(3, backRightAngle, backRightDrive, backRightAngleController, backRightAngleEncoder, RobotMap.backRightAngleOffset)
	};

	public AHRS mNavX = new AHRS(SPI.Port.kMXP, (byte) 200);
	// public ADXRS450_Gyro temp_gyro = new ADXRS450_Gyro(SPI.Port.kMXP);

	public SwerveDriveSubsystem() {
		zeroGyro();

		mSwerveModules[0].getDriveMotor().setInverted(true);
		mSwerveModules[1].getDriveMotor().setInverted(false);
		mSwerveModules[2].getDriveMotor().setInverted(false);
		mSwerveModules[3].getDriveMotor().setInverted(true);

		mSwerveModules[0].getAngleMotor().setInverted(true);
		mSwerveModules[3].getAngleMotor().setInverted(true);

		for (SwerveDriveModule module : mSwerveModules) {
			module.getPIDController().setInputRange(0, 2 * Math.PI);
			module.getPIDController().setAbsoluteTolerance(Math.toRadians(3.0));
			module.getPIDController().setOutputRange(-0.8, 0.8);
			module.getPIDController().setContinuous(true);
			module.getPIDController().enable();
			// module.getPIDController().setSetpoint(0);
		}
		// fieldOrientedController.setInputRange(0, 360);
		// fieldOrientedController.setAbsoluteTolerance(3.0);
		// fieldOrientedController.setOutputRange(-0.5, 0.5);
		// fieldOrientedController.setContinuous(true);
	}

	public AHRS getNavX() {
		return mNavX;
	}

	public double getGyroAngle() {
		// return temp_gyro.getAngle();
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
	public void holonomicDrive(double forward, double strafe, double rotation, boolean iFO) {
		forward *= getSpeedMultiplier();
		strafe *= getSpeedMultiplier();
		if (iFO) {
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
			boolean reversed = false;
			if (Math.abs(forward) > 0.05 ||
			    Math.abs(strafe) > 0.05 ||
			    Math.abs(rotation) > 0.05) {
					if(OI.shortestPathDirection(mSwerveModules[i].readAngle(mSwerveModules[i].getEncoder(), mSwerveModules[i].getOffset()), Math.toRadians(angles[i] + 180)) == -1){
						reversed = true;
						mSwerveModules[i].getPIDController().setSetpoint(Math.toRadians(angles[i]));
					}
					else{
						mSwerveModules[i].getPIDController().setSetpoint(Math.toRadians(angles[i] + 180));
					}
					// mSwerveModules[i].getPIDController().setSetpoint(Math.toRadians(angles[i]));
			} else {
				// mSwerveModules[i].getPIDController().setSetpoint(mSwerveModules[i].getTargetAngle());
			}
			if (reversed) mSwerveModules[i].setTargetSpeed(-speeds[i]);
			else mSwerveModules[i].setTargetSpeed(speeds[i]);
		}
	}

	@Override
	public void stopDriveMotors() {
		for (SwerveDriveModule module : mSwerveModules) {
			module.setTargetSpeed(0);
		}
	}

	//Test code for trajectory library
	Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.05, 1.7, 2.0, 60.0);
	Waypoint[] points = new Waypoint[] {
		new Waypoint(-4, -1, Pathfinder.d2r(-45)),
		new Waypoint(-2, -2, 0),
		new Waypoint(0, 0, 0)
	};

	Trajectory trajectory = Pathfinder.generate(points, config);
	// Wheelbase Width(left to right) = 0.5m, Wheelbase length(front to back) = 0.6m, Swerve Mode = Default
	SwerveModifier modifier = new SwerveModifier(trajectory).modify(0.5, 0.6, SwerveModifier.Mode.SWERVE_DEFAULT);
	
	Trajectory fl = modifier.getFrontLeftTrajectory();
	Trajectory fr = modifier.getFrontRightTrajectory();
	Trajectory bl = modifier.getBackLeftTrajectory();
	Trajectory br = modifier.getBackRightTrajectory();
}
