package frc.drive;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.OI;
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

	public double trueFRDEnc = 0;
	public double trueFLDEnc = 0;
	public double trueBRDEnc = 0;
	public double trueBLDEnc = 0;

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
	
	public PIDController rotationAngleController = new PIDController(0.0012, 0.0, 0.0);
	
	// public Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.05, RobotMap.empirical_free_velocity, 10.0, 1.0);
	
	// public SwerveModifier modifier;

	public EncoderFollower flFollower;
	public EncoderFollower frFollower;
	public EncoderFollower blFollower;
	public EncoderFollower brFollower;

	
	public double previous_FRenc = 0.0;
	public double previous_FLenc = 0.0;
	public double previous_BLenc = 0.0;
	public double previous_BRenc = 0.0;
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

	public AHRS mNavX;

	public SwerveDriveSubsystem() {
		mNavX = new AHRS(SPI.Port.kMXP, (byte) 200);
		mNavX.zeroYaw();

		mSwerveModules[0].getDriveMotor().setInverted(false);
		mSwerveModules[1].getDriveMotor().setInverted(true);
		mSwerveModules[2].getDriveMotor().setInverted(true);
		mSwerveModules[3].getDriveMotor().setInverted(false);

		mSwerveModules[0].getAngleMotor().setInverted(true);
		mSwerveModules[1].getAngleMotor().setInverted(true);
		mSwerveModules[2].getAngleMotor().setInverted(true);
		mSwerveModules[3].getAngleMotor().setInverted(true);

		for (SwerveDriveModule module : mSwerveModules) {
			module.getAnglePIDController().enableContinuousInput(0, 2 * Math.PI);
			module.getAnglePIDController().setTolerance(Math.toRadians(3.0));
			module.getDrivePIDController().setTolerance(250);
			module.getDriveMotor().getEncoder().setPosition(0);
		}
		
		// flFollower.configurePIDVA(0.00004, 0.0, 0, 1 / RobotMap.empirical_free_velocity, 3);
		// frFollower.configurePIDVA(0.00004, 0.0, 0, 1 / RobotMap.empirical_free_velocity, 3);
		// blFollower.configurePIDVA(0.00004, 0.0, 0, 1 / RobotMap.empirical_free_velocity, 3);
		// brFollower.configurePIDVA(0.00004, 0.0, 0, 1 / RobotMap.empirical_free_velocity, 3);

		rotationAngleController.enableContinuousInput(-180, 180);
		rotationAngleController.setTolerance(5);
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
		rotation *= -1;
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

	// public double rTFRDEncVal(double previous_driveenc){
	// 	if (mSwerveModules[0].readAngle() > Math.PI/2 && mSwerveModules[0].readAngle() < 1.5 * Math.PI){
	// 		trueFRDEnc -= previous_driveenc - mSwerveModules[0].getDriveEncoderVal();
	// 	}
	// 	else{
	// 		trueFRDEnc += previous_driveenc - mSwerveModules[0].getDriveEncoderVal();
	// 	}
	// 	return trueFRDEnc;
	// }

	// public double rTFLDEncVal(double previous_driveenc){
	// 	if (mSwerveModules[1].readAngle() > Math.PI/2 && mSwerveModules[1].readAngle() < 1.5 * Math.PI){
	// 		trueFLDEnc -= previous_driveenc - mSwerveModules[1].getDriveEncoderVal();
	// 	}
	// 	else{
	// 		trueFLDEnc += previous_driveenc - mSwerveModules[1].getDriveEncoderVal();
	// 	}
	// 	return trueFLDEnc;
	// }
	// public double rTBRDEncVal(double previous_driveenc){
	// 	if (mSwerveModules[2].readAngle() > Math.PI/2 && mSwerveModules[2].readAngle() < 1.5 * Math.PI){
	// 		trueBRDEnc -= previous_driveenc - mSwerveModules[2].getDriveEncoderVal();
	// 	}
	// 	else{
	// 		trueBRDEnc += previous_driveenc - mSwerveModules[2].getDriveEncoderVal();
	// 	}
	// 	return trueBRDEnc;
	// }
	// public double rTBLDEncVal(double previous_driveenc){
	// 	if (mSwerveModules[3].readAngle() > Math.PI/2 && mSwerveModules[3].readAngle() < 1.5 * Math.PI){
	// 		trueBLDEnc -= previous_driveenc - mSwerveModules[3].getDriveEncoderVal();
	// 	}
	// 	else{
	// 		trueBLDEnc += previous_driveenc - mSwerveModules[3].getDriveEncoderVal();
	// 	}
	// 	return trueBLDEnc;
	// }
	
	public void update(){
		holonomicDrive(-OI.getlYval(), -OI.getlXval(), OI.getrXval(), true);
		// previous_FRenc = rTFRDEncVal(trueFRDEnc);
		// previous_FLenc = rTFLDEncVal(trueFLDEnc);
		// previous_BLenc = rTBLDEncVal(trueBLDEnc);
		// previous_BRenc = rTBRDEncVal(trueBRDEnc);
	}
	public void testMotors(){
		// frontRightAngle.set(0.5);
		// frontLeftAngle.set(0.5);
		// backLeftAngle.set(0.5);
		// backRightAngle.set(0.5);

		// frontRightAngle.set(frontRightAngleController.calculate(mSwerveModules[0].readAngle(), Math.toRadians(90)));
		// frontLeftAngle.set(frontLeftAngleController.calculate(mSwerveModules[1].readAngle(), Math.toRadians(90)));
		// backLeftAngle.set(backLeftAngleController.calculate(mSwerveModules[2].readAngle(), Math.toRadians(90)));
		// backRightAngle.set(backRightAngleController.calculate(mSwerveModules[3].readAngle(), Math.toRadians(90)));

		// System.out.println("front right " + Math.toDegrees(mSwerveModules[0].readAngle()));
		// System.out.println("front left " + Math.toDegrees(mSwerveModules[1].readAngle()));
    	System.out.println("back left " + Math.toDegrees(mSwerveModules[2].readAngle()));
    	System.out.println("back right " + Math.toDegrees(mSwerveModules[3].readAngle()));
	}

	public void calibrateNavX(){
		System.out.println(mNavX.getYaw());
	}
}