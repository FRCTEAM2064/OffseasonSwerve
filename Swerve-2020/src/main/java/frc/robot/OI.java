/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import frc.commands.rotateToCenter;
import frc.commands.rotationControl;
import frc.commands.rotationControlPickColor;
import frc.commands.raiseLift;
import frc.commands.lowerLift;
import frc.commands.shoot;
import frc.commands.testMoveSwerve;

// import frc.commands.testMoveSwerve;
import frc.commands.toggleIntakePanel;
// import frc.commands.rotateToCenter;
/**
 * Add your docs here.
 */

public class OI {
    public static double previous_angle;

    public static Joystick ljoy= new Joystick(0);
    public static Joystick rjoy = new Joystick(1);

    public static JoystickButton lb1 = new JoystickButton(ljoy, 1);
    public static JoystickButton lb2 = new JoystickButton(ljoy, 2);
    public static JoystickButton lb3 = new JoystickButton(ljoy, 3);
    public static JoystickButton lb4 = new JoystickButton(ljoy, 4);
    public static JoystickButton lb5 = new JoystickButton(ljoy, 5);
    public static JoystickButton lb8 = new JoystickButton(ljoy, 8);

    public static JoystickButton rb1 = new JoystickButton(rjoy, 1);
    public static JoystickButton rb2 = new JoystickButton(rjoy, 2);
    public static JoystickButton rb3 = new JoystickButton(rjoy, 3);
    public static JoystickButton rb4 = new JoystickButton(rjoy, 4);

    public OI(){
        //rb1 is used to halve robot rotation
        // rb2.whileHeld(new testMoveSwerve());
        // rb3.whenPressed(new toggleIntakePanel());
        lb1.whileHeld(new shoot());
        lb2.whenPressed(new rotateToCenter());
        // lb3.whenPressed(new rotationControl());
        lb3.whileHeld(new toggleIntakePanel());
        lb4.whileHeld(new raiseLift());
        lb5.whileHeld(new lowerLift());
    }
    /**
     * @return left joystick moving forward and backward axis val. Forward = 1; backward = -1
     */
    public static double getlYval(){
        if (Math.abs(ljoy.getY()) < 0.05) return 0;
        else return ljoy.getY();
    }
    /**
     * @return left joystick moving right and left axis val. right = 1; left = -1
     */
    public static double getlXval(){
        if (Math.abs(ljoy.getX()) < 0.05) return 0;
        else return ljoy.getX();
    }
    /**
     * @return right joystick moving right and left axis val. right = 1; left = -1
     */
    public static double getrXval(){
        if (Math.abs(rjoy.getX()) < 0.05) return 0;
        else{
            if (rb1.get()) return -rjoy.getX()/2;
            else return -rjoy.getX();
        }
    }

    public static double getrYval(){
        if (Math.abs(rjoy.getY()) < 0.05) return 0;
        else return rjoy.getY();
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

    // public static double getlAngle(){
    //     if (getlYval() < 0.05 && getlXval() < 0.05){
    //         return previous_angle;
    //     }
    //     double current_angle = Math.toDegrees(Math.atan2(getlYval(), getlXval())) + 90;
    //     if (current_angle > 180){
    //         current_angle -= 360;
    //     }
    //     previous_angle = current_angle;
    //     return current_angle;
    // }

    /**
     * @param setpoint : The final 
     * returns +1 or -1 depending on if 
     * the shortest direction is counterclockwise 
     * or clockwise, respectively
     */
    public static double shortestPathDirection(double current_angle, double setpoint){
        double difference_without_discont = Math.abs(setpoint - current_angle);
        double difference_with_discont = Math.abs(360 - difference_without_discont);
        if (!(difference_without_discont > difference_with_discont)) return 1;
        return -1;
    }
    
    public static boolean shouldOptimize(double setpoint, double current){
        double error = getContinuousError(setpoint - current, 360);
        return Math.abs(error) > 90 && Math.abs(error) < 270;
    }

    public static double getContinuousError(double error, double m_inputRange) {
        error %= m_inputRange;
        if (Math.abs(error) > m_inputRange / 2) {
            if (error > 0) {
                return error - m_inputRange;
          } 
            else {
                return error + m_inputRange;
            }
        }
        return error;
      }
    }
    // public static double shortestPathDirection(double current_angle, double setpoint){
    //     double difference_without_discont = Math.abs(setpoint - current_angle);
    //     double difference_with_discont = Math.abs(360 - difference_without_discont);
    //     if (!(difference_without_discont > difference_with_discont)) return -1;
    //     return 1;
    // }
