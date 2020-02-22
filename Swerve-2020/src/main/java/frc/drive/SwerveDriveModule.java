package frc.drive;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.RobotController;
import frc.robot.RobotMap;
public class SwerveDriveModule extends Subsystem {
	private static final long STALL_TIMEOUT = 2000;

	private long mStallTimeBegin = Long.MAX_VALUE;

	private double mLastError = 0;

	private final int mModuleNumber;

	private double angleOffset;

	private final CANSparkMax mAngleMotor;
	private final CANSparkMax mDriveMotor;

	private final PIDController mAngleController;
	private final PIDController mDriveController;

	private final AnalogInput mAngleEnc;

	public SwerveDriveModule(int moduleNumber, CANSparkMax angleMotor, CANSparkMax driveMotor,PIDController angleController, PIDController driveController, AnalogInput angleEnc, double angleOffset) {
		mModuleNumber = moduleNumber;

		mAngleMotor = angleMotor;
		mDriveMotor = driveMotor;

		mAngleController = angleController;
		mDriveController = driveController;

		mAngleEnc = angleEnc;

		this.angleOffset = angleOffset;

		//angleMotor.changeControlMode(CANSparkMax.TalonControlMode.Position);
		
		//angleMotor.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogEncoder);
		
		// angleMotor.reverseSensor(true);
		angleMotor.setInverted(true);
		// angleMotor.setPID(20, 0, 200); // P: 20, I: 0, D: 200
		//Already done in SwerveDriveSubsystem

		// angleMotor.set(0);
		// angleMotor.set(0);
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
	protected void initDefaultCommand() {}

	public CANSparkMax getAngleMotor() {
		return mAngleMotor;
	}

	public CANSparkMax getDriveMotor() {
		return mDriveMotor;
	}

	public PIDController getAnglePIDController(){
		return mAngleController;
	}

	public PIDController getDrivePIDController(){
		return mDriveController;
	}

	public AnalogInput getEncoder(){
		return mAngleEnc;
	}
	
	public double getOffset(){
		return angleOffset;
	}

	public double readAngle(){
		double angle = ((getEncoder().getVoltage() / RobotController.getVoltage5V()) * 2.0 * Math.PI);
		angle += getOffset();
		angle %= 2.0 * Math.PI;
        if (angle < 0.0) {
			angle += 2.0 * Math.PI;
        }
        return angle;
	}

	public void setTargetSpeed(double speed) {
		if (speed > RobotMap.maxSwerveSpeed){
			setTargetSpeed(RobotMap.maxSwerveSpeed);
		}
		else if (speed < -RobotMap.maxSwerveSpeed){
			setTargetSpeed(-RobotMap.maxSwerveSpeed);
		}
		else{
			getDriveMotor().set(speed);
		}
	}

	public void setTargetVelocity(double velocity){
		getDriveMotor().set(velocity/RobotMap.empirical_free_velocity);
	}

	/**
	 * Returns drive encoder position as an integer; for jaci's pathfinder
	 * @return 1 rotation = 4096 ticks
	 */
	protected int getDriveEncoderVal() {return (int)(getDriveMotor().getEncoder().getPosition() * 1024);}
}
