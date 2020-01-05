package frc.util;

public class MotorStallException extends IllegalStateException {
	public MotorStallException(String msg) {
		super(msg);
	}
}
