/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.util;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;

/**
 * Add your docs here.
 */
public abstract class TrapezoidalProfile {
    public Timer time;
    
    private double min_dist_for_max_vel;
    
    private double time_to_reach_max_vel;

    private double max_velocity;
    
    private double ka;

    private Properties current_properties;

    private Properties final_properties;

    public TrapezoidalProfile(double ka, double max_velocity){
        time = new Timer();
        this.max_velocity = max_velocity;
        this.ka = ka;
        min_dist_for_max_vel = 0.5 * max_velocity * (max_velocity/ka);
        time_to_reach_max_vel = ka / max_velocity;
    }


    public double getKa(){
        return ka;
    }

    public double getMax_Vel(){
        return max_velocity;
    }

    public abstract double returnCurrentPosition();

    public abstract double returnCurrentSpeed();

    public abstract double returnCurrentAcceleration();

    public abstract Properties update();

    public void setFinalProperties(double distance){
        final_properties.position = current_properties.position + distance;
        if(distance < min_dist_for_max_vel * 2){
            
        }
        else{
            final_properties.time = min_dist_for_max_vel * 2 + max_velocity;
            final_properties.velocity = 0;
            final_properties.acceleration = -ka;
        }
    }

    public abstract Properties getCurrentProperties();

    public Properties calculate(double current_time){
        double position = 0;
        double velocity = 0;
        double acceleration = 0;

        return new Properties(current_time, position, velocity, acceleration);
    }
}
