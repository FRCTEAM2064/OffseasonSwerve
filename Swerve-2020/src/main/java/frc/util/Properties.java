/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.util;

/**
 * Add your docs here.
 */
public class Properties {
    public double time;
    public double position;
    public double velocity;
    public double acceleration;
/**
 * @param time
 * @param position
 * @param velocity
 * @param acceleration
 */
    public Properties(double time, double position, double velocity, double acceleration) {
        this.time = time;
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
    }
}

