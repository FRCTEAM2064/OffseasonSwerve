package frc.drive;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.SPI;
import frc.robot.RobotMap;

public class SwerveDriveSubsystem extends HolonomicDrivetrain {
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
	public CANEncoder frontRightAngleCANCoder = frontLeftAngle.getEncoder();
	public CANEncoder frontLeftAngleCANCoder = frontLeftAngle.getEncoder();
	public CANEncoder backLeftAngleCANCoder = backLeftAngle.getEncoder();
	public CANEncoder backRightDriveCANCoder = backRightDrive.getEncoder();
	public CANEncoder frontRightDriveCANCoder = frontLeftDrive.getEncoder();
	public CANEncoder frontLeftDriveCANCoder = frontLeftDrive.getEncoder();
	public CANEncoder backLeftDriveCANCoder = backLeftDrive.getEncoder();
	
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
	
	// private void setAdjustmentAngle(double input){
	// 	adjustmentAngle = input;
	// }
	// private PIDOutput gyroscopeOutput = new PIDOutput(){
	
	// 	@Override
	// 	public void pidWrite(double input) {
	// 		setAdjustmentAngle(input);
	// 	}
	// };
	//one with the robot
	//one with the program
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

		// rotationJoyAngleController.enableContinuousInput(-180, 180);
		// rotationJoyAngleController.setTolerance(3); TODO: BRING THIS BACK
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
	
	public void holonomicDrive(double forward, double strafe, double rotation, boolean iFO, boolean isOptimized) {
		forward *= getSpeedMultiplier();
		strafe *= getSpeedMultiplier();
		if (iFO) {
			double angleRad = Math.toRadians(getGyroAngle());
			double temp = forward * Math.cos(angleRad) +
					strafe * Math.sin(angleRad);
			strafe = -forward * Math.sin(angleRad) + strafe * Math.cos(angleRad);
			forward = temp;
		}
		// mSwerveModules[i].readAngle(mSwerveModules[i].getEncoder(), mSwerveModules[i].getOffset())
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
				if (isOptimized){
					double angle = angles[i];
					double currentAngle = mSwerveModules[i].readAngle();
					if (Math.abs(angle - currentAngle) < Math.PI/2 && Math.abs(angle - currentAngle) > 3*Math.PI/2) {
						angle = (angle + Math.PI) % Math.PI*2; //TODO: If this doesn't work, uncomment line below and comment this line out
						// angle = (angle) % 2*Math.PI - Math.PI;
						speeds[i] = -speeds[i];
					}
					//If we are moving slowly, don't change the angle to keep things stable (rotating wheels when speed is small can induce lateral movement)
					if (Math.abs(speeds[i]) < .05){
						angle = prevAngle;
					}
					else {
						prevAngle = angle;
					}
					mSwerveModules[i].getAngleMotor().set(mSwerveModules[i].getPIDController().calculate(mSwerveModules[i].readAngle(), Math.toRadians(angles[i] + 180)));
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
				else{
					mSwerveModules[i].getAngleMotor().set(mSwerveModules[i].getPIDController().calculate(mSwerveModules[i].readAngle(), Math.toRadians(angles[i] + 180)));
				
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
			else{
				stopAllMotors();
			}
		}
	}

	@Override
	public void stopAllMotors() {
		for (SwerveDriveModule module : mSwerveModules) {
			module.setTargetSpeed(0);
			module.getAngleMotor().set(0);
		}
	}
}
