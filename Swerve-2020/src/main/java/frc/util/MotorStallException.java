package frc.util;

public class MotorStallException extends IllegalStateException {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public MotorStallException(String msg) {
		super(msg);
	}
}
