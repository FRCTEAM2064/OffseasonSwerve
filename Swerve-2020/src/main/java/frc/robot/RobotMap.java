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
    public static final int frontRightAngleID = 32;
    public static final int frontRightDriveID = 2;
    public static final int frontLeftAngleID = 3; 
    public static final int frontLeftDriveID = 4; 
    public static final int backLeftAngleID = 5;
    public static final int backLeftDriveID = 6;
    public static final int backRightAngleID = 7; 
    public static final int backRightDriveID = 8;
    public static final int intakeTubingLeftID = 9;
    public static final int intakeTubingRightID = 10;
    public static final int shooterID = 13;
    public static final int winchControlID = 15;
    
    public static final int backRightEncoderID = 0;
    public static final int frontRightEncoderID = 1;
    public static final int backLeftEncoderID = 3;
    public static final int frontLeftEncoderID = 2;
    public static final int intakeTubingInwardsID = 4;
    public static final int intakeTubingUpwardsID = 5;
    public static final int controlPanelID = 6;

    public static double frontRightAngleOffset = -Math.toRadians(221.4);
    public static double frontLeftAngleOffset = -Math.toRadians(68.8);
    public static double backLeftAngleOffset = -Math.toRadians(52.6);
    public static double backRightAngleOffset = -Math.toRadians(129.9);

    public static double maxSwerveSpeed = 1;
    public static double frictional_Coeff = 1;
    
    public static final double RADIUS_OF_WHEEL = inchesToMeters(2); //in m
    public static final double circumference_of_wheel = RADIUS_OF_WHEEL * Math.PI * 2; //in m
    public static final double empirical_max_rpm = 5676 * maxSwerveSpeed;
    // public static final double shooter_max_rpm = 
    public static final double empirical_free_velocity = empirical_max_rpm * circumference_of_wheel * frictional_Coeff; //full speed

    public static final double maxRaiseLiftSpeed = 1;
    public static final double maxLowerLiftSpeed = 0.5;
    public static final double maxFlywheelSpeed = 0.85; //CHANGE THIS TO CHANGE SHOOT SPEED 
    public static final double maxTubingSpeed = 1;

    public static final double climbBelowTrench = 150;

    public static final double actualDistanceMultiplierHardWood = 7.236; //TODO: Need to change this to get the actual distance
    public static double inchesToMeters(double inches){
        return inches*2.54/100;
    }
}
