/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * Add your docs here.
 */
public class RobotMap {    
    public static int frontRightAngleID = 2; //WAS 7
    public static int frontRightDriveID = 1; //WAS 8
    public static int frontLeftAngleID = 3; //WAS 2
    public static int frontLeftDriveID = 4; //WAS 1
    public static int backLeftAngleID = 5; //WAS 4
    public static int backLeftDriveID = 6; //WAS 3
    public static int backRightAngleID = 8; //WAS 5
    public static int backRightDriveID = 7; //WAS 6

    public static int frontLeftEncoderID = 1; //WAS 0
    public static int backLeftEncoderID = 2; //WAS 1
    public static int backRightEncoderID = 3; //WAS 2
    public static int frontRightEncoderID = 0; //WAS 3

    public static double frontRightAngleOffset = -Math.toRadians(219.5);
    public static double frontLeftAngleOffset = -Math.toRadians(279.5);
    public static double backLeftAngleOffset = -Math.toRadians(48.5);
    public static double backRightAngleOffset = -Math.toRadians(140.6);

    public static double maxSwerveSpeed = 1;

    public static int numberIt = 10;

    public static final double RADIUS_OF_WHEEL = 2.0 * 2.54/100; //in m
    public static final double circumference_of_wheel = RADIUS_OF_WHEEL * Math.PI * 2; //in m
    public static final double empirical_max_rpm = 5676 * maxSwerveSpeed;
    public static final double empirical_free_velocity = empirical_max_rpm * circumference_of_wheel; //full speed

}
