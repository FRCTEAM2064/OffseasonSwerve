/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * Add your docs here.
 */
public class OI {
    private static Joystick ljoy = new Joystick(0);
    private static Joystick rjoy = new Joystick(1);
    private static Joystick ojoy = new Joystick(2);

    private static JoystickButton lb1 = new JoystickButton(ljoy, 1);
    private static JoystickButton lb2 = new JoystickButton(ljoy, 2);
    private static JoystickButton lb3 = new JoystickButton(ljoy, 3);
    private static JoystickButton lb4 = new JoystickButton(ljoy, 4);

    private static JoystickButton rb1 = new JoystickButton(rjoy, 1);
    private static JoystickButton rb2 = new JoystickButton(rjoy, 2);
    private static JoystickButton rb3 = new JoystickButton(rjoy, 3);
    private static JoystickButton rb4 = new JoystickButton(rjoy, 4);


    /**
     * @return left joystick moving forward and backward axis val. Forward = 1; backward = -1
     */
    public static double getlYval(){
        return -ljoy.getY();
    }
    /**
     * @return left joystick moving right and left axis val. right = 1; left = -1
     */
    public static double getlXval(){
        return ljoy.getX();
    }
    /**
     * @return right joystick moving right and left axis val. right = 1; left = -1
     */
    public static double getrXval(){
        return rjoy.getX();
    }

    public static boolean quickRotLeft(){
        return rb2.get();
    }
    
    public static boolean quickRotRight(){
        return rb4.get();
    }
}
