/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.common.input.JoystickAxis;

/**
 * Add your docs here.
 */

public class OI {
    public static double previous_angle = 0;

    private static Joystick ljoy= new Joystick(0);
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
        if (Math.abs(-ljoy.getY()) < 0.02) return 0;
        else return -ljoy.getY();
    }
    /**
     * @return left joystick moving right and left axis val. right = 1; left = -1
     */
    public static double getlXval(){
        if (Math.abs(-ljoy.getX()) < 0.02) return 0;
        else return -ljoy.getX();
    }
    /**
     * @return right joystick moving right and left axis val. right = 1; left = -1
     */
    public static double getrXval(){
        if (Math.abs(rjoy.getX()) < 0.02) return 0;
        else return -rjoy.getX();
    }

    public static double getrYval(){
        if (Math.abs(rjoy.getY()) < 0.02) return 0;
        else return rjoy.getY();
    }

    public static boolean quickRotLeft(){
        return rb3.get();
    }
    
    public static boolean quickRotRight(){
        return rb4.get();
    }

    public static double getrAngle(){
        if (getrYval() == 0 && getrXval() == 0){
            return previous_angle;
        }
        double current_angle = Math.toDegrees(Math.atan2(getrYval(), getrXval())) + 90;
        if (current_angle > 180){
            current_angle -= 360;
        }
        previous_angle = current_angle;
        return current_angle;
    }
    /**
     * @param setpoint : The final 
     * returns +1 or -1 depending on if 
     * the shortest direction is counterclockwise 
     * or clockwise, respectively
     */
    public static double shortestPathDirection(double current_angle, double setpoint){
        double difference_without_discont = Math.abs(setpoint - current_angle);
        double difference_with_discont = Math.abs(360 - difference_without_discont);
        if (!(difference_without_discont > difference_with_discont)) return -1;
        return 1;
    }
}
