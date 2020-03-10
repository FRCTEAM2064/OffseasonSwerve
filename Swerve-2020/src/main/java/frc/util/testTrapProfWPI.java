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
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile.Constraints;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile.State;
public class testTrapProfWPI {
    Constraints constraints = new Constraints(20, 2);  //using ft/s
    State initial = new State(0, 0);
    State goal = new State(100, 0);
    TrapezoidProfile test = new TrapezoidProfile(constraints, goal, initial);
    public void f(){
        // test.calculate(t)
    }

}
