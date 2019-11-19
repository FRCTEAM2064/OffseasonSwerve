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
    public static int frontRightAngleID = 7;
    public static int frontRightDriveID = 8;
    public static int frontLeftAngleID = 2;
    public static int frontLeftDriveID = 1;
    public static int backLeftAngleID = 4;
    public static int backLeftDriveID = 3;
    public static int backRightAngleID = 5;
    public static int backRightDriveID = 6;

    public static int frontLeftEncoderID = 0;
    public static int backLeftEncoderID = 1;
    public static int backRightEncoderID = 2;
    public static int frontRightEncoderID = 3;

    public static double frontRightAngleOffset = Math.toRadians(43.50);
    public static double frontLeftAngleOffset = -Math.toRadians(18);
    public static double backLeftAngleOffset = -Math.toRadians(209.1);
    public static double backRightAngleOffset = Math.toRadians(25.6);
}
