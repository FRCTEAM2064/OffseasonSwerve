package frc.drive;

import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.SPI;
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

	DoubleSupplier frontRightEncoderValue = new DoubleSupplier(){ //TODO: How to set displacement type
	
		@Override
		public double getAsDouble() {
			// TODO Auto-generated method stub
			return SwerveDriveModule.readAngle(frontRightAngleEncoder, RobotMap.frontRightAngleOffset);
		}
	}; 
	
	DoubleSupplier frontLeftEncoderValue = new DoubleSupplier(){ //TODO: How to set displacement type
	
		@Override
		public double getAsDouble() {
			// TODO Auto-generated method stub
			return SwerveDriveModule.readAngle(frontLeftAngleEncoder, RobotMap.frontLeftAngleOffset);
		}
	};

	DoubleSupplier backRightEncoderValue = new DoubleSupplier(){ //TODO: How to set displacement type
	
		@Override
		public double getAsDouble() {
			// TODO Auto-generated method stub
			return SwerveDriveModule.readAngle(backRightAngleEncoder, RobotMap.backRightAngleOffset);
		}
	};

	DoubleSupplier backLeftEncoderValue = new DoubleSupplier(){ //TODO: How to set displacement type
	
		@Override
		public double getAsDouble() {
			// TODO Auto-generated method stub
			return SwerveDriveModule.readAngle(backLeftAngleEncoder, RobotMap.backLeftAngleOffset);
		}
	};
	
	DoubleConsumer frontRightAngleOutput = new DoubleConsumer(){
	
		@Override
		public void accept(double arg0) {
			frontRightAngle.set(arg0);
			
		}
	};
	DoubleConsumer frontLeftAngleOutput = new DoubleConsumer(){
	
		@Override
		public void accept(double arg0) {
			frontLeftAngle.set(arg0);
			
		}
	};
	DoubleConsumer backRightAngleOutput = new DoubleConsumer(){
	
		@Override
		public void accept(double arg0) {
			backRightAngle.set(arg0);
			
		}
	};
	DoubleConsumer backLeftAngleOutput = new DoubleConsumer(){
	
		@Override
		public void accept(double arg0) {
			backLeftAngle.set(arg0);
			
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
	
	public PIDController frontRightAngleController = new PIDController(0.18, 0.0, 0.0);
	public PIDController frontLeftAngleController = new PIDController(0.18, 0.0, 0.0);
	public PIDController backLeftAngleController = new PIDController(0.18, 0.0, 0.0);
	public PIDController backRightAngleController = new PIDController(0.18, 0.0, 0.0);
	public PIDController rotationJoyAngleController = new PIDController(0.1, 0.0, 0.0);
	
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

	public SwerveDriveSubsystem() {
		zeroGyro();

		mSwerveModules[0].getDriveMotor().setInverted(true);
		mSwerveModules[1].getDriveMotor().setInverted(false);
		mSwerveModules[2].getDriveMotor().setInverted(false);
		mSwerveModules[3].getDriveMotor().setInverted(true);

		mSwerveModules[0].getAngleMotor().setInverted(true);
		mSwerveModules[3].getAngleMotor().setInverted(true);

		for (SwerveDriveModule module : mSwerveModules) {
			module.getPIDController().enableContinuousInput(0, 2 * Math.PI);
			module.getPIDController().setTolerance(Math.toRadians(3.0));
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
// 	@Override
// 	public void holonomicDrive(double forward, double strafe, double rotation, boolean iFO) {
// 		forward *= getSpeedMultiplier();
// 		strafe *= getSpeedMultiplier();
// 		if (iFO) {
// 			double angleRad = Math.toRadians(getGyroAngle());
// 			double temp = forward * Math.cos(angleRad) +
// 					strafe * Math.sin(angleRad);
// 			strafe = -forward * Math.sin(angleRad) + strafe * Math.cos(angleRad);
// 			forward = temp;
// 		}

// 		double a = strafe - rotation * (WHEELBASE / TRACKWIDTH);
// 		double b = strafe + rotation * (WHEELBASE / TRACKWIDTH);
// 		double c = forward - rotation * (TRACKWIDTH / WHEELBASE);
// 		double d = forward + rotation * (TRACKWIDTH / WHEELBASE);

// 		double[] angles = new double[]{
// 				Math.atan2(b, c) * 180 / Math.PI,
// 				Math.atan2(b, d) * 180 / Math.PI,
// 				Math.atan2(a, d) * 180 / Math.PI,
// 				Math.atan2(a, c) * 180 / Math.PI
// 		};
// /*
// 	 * 0 is Front Right
// 	 * 1 is Front Left
// 	 * 2 is Back Left
// 	 * 3 is Back Right
// */
// 		double[] speeds = new double[]{
// 				Math.sqrt(b * b + c * c), //Front right
// 				Math.sqrt(b * b + d * d), //Front left
// 				Math.sqrt(a * a + d * d), //Back left
// 				Math.sqrt(a * a + c * c) //Back right
// 		};

// 		double max = speeds[0];

// 		for (double speed : speeds) {
// 			if (speed > max) {
// 				max = speed;
// 			}
// 		}

// 		for (int i = 0; i < 4; i++) {
// 			if (Math.abs(forward) > 0.05 ||
// 			    Math.abs(strafe) > 0.05 ||
// 			    Math.abs(rotation) > 0.05) {
// 				mSwerveModules[i].getPIDController().setSetpoint(Math.toRadians(angles[i] + 180));
// 				// mSwerveModules[i].getPIDController().setSetpoint(Math.toRadians(angles[i]));
// 			} else {
// 				// mSwerveModules[i].getPIDController().setSetpoint(mSwerveModules[i].getTargetAngle());
// 			}
// 			mSwerveModules[i].setTargetSpeed(speeds[i]);
// 		}
// 	}
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
			if (Math.abs(forward) > 0.05 ||
			    Math.abs(strafe) > 0.05 ||
			    Math.abs(rotation) > 0.05) {
					
				mSwerveModules[i].getPIDController().calculate(SwerveDriveModule.readAngle(mSwerveModules[i].getEncoder(), mSwerveModules[i].getOffset()), Math.toRadians(angles[i] + 180));
				// mSwerveModules[i].getPIDController().setSetpoint(Math.toRadians(angles[i]));
			} else {
				// mSwerveModules[i].getPIDController().setSetpoint(mSwerveModules[i].getTargetAngle());
			}

			if (speeds[i] > RobotMap.maxSwerveSpeed){
				mSwerveModules[i].setTargetSpeed(RobotMap.maxSwerveSpeed);
			}
			else if (speeds[i] < -RobotMap.maxSwerveSpeed){
				mSwerveModules[i].setTargetSpeed(-RobotMap.maxSwerveSpeed);
			}
			else{
				mSwerveModules[i].setTargetSpeed(speeds[i]);
			}
		}
	}

// 	@Override
// 	public void holonomicDrive(double forward, double strafe, double rotation, boolean iFO) {
// 		forward *= getSpeedMultiplier();
// 		strafe *= getSpeedMultiplier();
// 		if (iFO) {
// 			double angleRad = Math.toRadians(getGyroAngle());
// 			double temp = forward * Math.cos(angleRad) +
// 					strafe * Math.sin(angleRad);
// 			strafe = -forward * Math.sin(angleRad) + strafe * Math.cos(angleRad);
// 			forward = temp;
// 		}

// 		double a = strafe - rotation * (WHEELBASE / TRACKWIDTH);
// 		double b = strafe + rotation * (WHEELBASE / TRACKWIDTH);
// 		double c = forward - rotation * (TRACKWIDTH / WHEELBASE);
// 		double d = forward + rotation * (TRACKWIDTH / WHEELBASE);

// 		double[] angles = new double[]{
// 				Math.atan2(b, c) * 180 / Math.PI,
// 				Math.atan2(b, d) * 180 / Math.PI,
// 				Math.atan2(a, d) * 180 / Math.PI,
// 				Math.atan2(a, c) * 180 / Math.PI
// 		};
// /*
// 	 * 0 is Front Right
// 	 * 1 is Front Left
// 	 * 2 is Back Left
// 	 * 3 is Back Right
// */
// 		double[] speeds = new double[]{
// 				Math.sqrt(b * b + c * c), //Front right
// 				Math.sqrt(b * b + d * d), //Front left
// 				Math.sqrt(a * a + d * d), //Back left
// 				Math.sqrt(a * a + c * c) //Back right
// 		};

// 		double max = speeds[0];

// 		for (double speed : speeds) {
// 			if (speed > max) {
// 				max = speed;
// 			}
// 		}

// 		for (int i = 0; i < 4; i++) {
// 			boolean reversed = false;
// 			if (Math.abs(forward) > 0.05 ||
// 			    Math.abs(strafe) > 0.05 ||
// 			    Math.abs(rotation) > 0.05) {
// 					if(OI.shortestPathDirection(mSwerveModules[i].readAngle(mSwerveModules[i].getEncoder(), mSwerveModules[i].getOffset()), Math.toRadians(angles[i]) + 180 ) == -1){
// 						reversed = true;
// 						mSwerveModules[i].getPIDController().setSetpoint(Math.toRadians(angles[i]));
// 					}
// 					else{
// 						mSwerveModules[i].getPIDController().setSetpoint(Math.toRadians(angles[i] + 180));
// 					}
// 					// mSwerveModules[i].getPIDController().setSetpoint(Math.toRadians(angles[i]));
// 			} else {
// 				// mSwerveModules[i].getPIDController().setSetpoint(mSwerveModules[i].getTargetAngle());
// 			}
// 			mSwerveModules[i].setTargetSpeed(speeds[i]);
// 		}
// 	}
 
	@Override
	public void stopDriveMotors() {
		for (SwerveDriveModule module : mSwerveModules) {
			module.setTargetSpeed(0);
		}
	}
}
