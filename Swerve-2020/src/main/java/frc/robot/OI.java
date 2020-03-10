/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.commands.rotateToCenter;
import frc.commands.rotationControl;
import frc.commands.raiseLift;
import frc.commands.bringClimbToDefault;
import frc.commands.hoodDown;
import frc.commands.hoodUp;
import frc.commands.lowerLift;
import frc.commands.polarMotion;
import frc.commands.positionControl;
import frc.commands.purgeBalls;
import frc.commands.shoot;
import frc.commands.toggleControlPanel;
import frc.commands.toggleIntakePanel;
import frc.commands.intakeTubingUpwards;
/**
 * Add your docs here.
 */

public class OI {
    public static double previous_angle;

    public static Joystick ljoy= new Joystick(0);
    public static Joystick rjoy = new Joystick(1);
    public static Joystick ojoy = new Joystick(2);

    public static JoystickButton lb1 = new JoystickButton(ljoy, 1);
    public static JoystickButton lb2 = new JoystickButton(ljoy, 2);
    public static JoystickButton lb3 = new JoystickButton(ljoy, 3);
    public static JoystickButton lb4 = new JoystickButton(ljoy, 4);
    public static JoystickButton lb5 = new JoystickButton(ljoy, 5);
    public static JoystickButton lb6 = new JoystickButton(ljoy, 6);
    public static JoystickButton lb7 = new JoystickButton(ljoy, 7);
    public static JoystickButton lb8 = new JoystickButton(ljoy, 8);

    public static JoystickButton rb1 = new JoystickButton(rjoy, 1);
    public static JoystickButton rb2 = new JoystickButton(rjoy, 2);
    public static JoystickButton rb3 = new JoystickButton(rjoy, 3);
    public static JoystickButton rb4 = new JoystickButton(rjoy, 4);
    public static JoystickButton rb5 = new JoystickButton(rjoy, 5);
    public static JoystickButton rb6 = new JoystickButton(rjoy, 6);
    public static JoystickButton rb7 = new JoystickButton(rjoy, 7);
    public static JoystickButton rb8 = new JoystickButton(rjoy, 8);

    public static JoystickButton ob1 = new JoystickButton(ojoy, 1);
    public static JoystickButton ob2 = new JoystickButton(ojoy, 2);
    public static JoystickButton ob3 = new JoystickButton(ojoy, 3);
    public static JoystickButton ob4 = new JoystickButton(ojoy, 4);
    public static JoystickButton ob5 = new JoystickButton(ojoy, 5);
    public static JoystickButton ob6 = new JoystickButton(ojoy, 6);
    public static JoystickButton ob7 = new JoystickButton(ojoy, 7);
    public static JoystickButton ob8 = new JoystickButton(ojoy, 8);
    public static JoystickButton ob9 = new JoystickButton(ojoy, 9);
    public static JoystickButton ob10 = new JoystickButton(ojoy, 10);
    public static JoystickButton ob11 = new JoystickButton(ojoy, 11);
    public static JoystickButton ob12 = new JoystickButton(ojoy, 12);
    public static JoystickButton ob13 = new JoystickButton(ojoy, 13);


    public OI(){
        //rb1 is used to halve robot rotation
        // lb4.whenPressed(new rotateToAngleGyro(-90, true));
        // lb7.whenPressed(new polarMotion(RobotMap.inchesToMeters(36), 90));
        

        rb2.whileHeld(new rotateToCenter());
        // rb3.whenPressed(new toggleIntakePanel());
        // rb4.whileHeld(new purgeBalls());
        // rb4.whileHeld(new rotateToAngleGyro(-180+22.5, false));
        rb8.whenPressed(new bringClimbToDefault());
        ob11.whileHeld(new intakeTubingUpwards());
        ob9.whileHeld(new shoot(-0.85));
        
        ob1.whileHeld(new shoot(2000, true));
        // ob1.whileHeld(new shootPower(-(ojoy.getRawAxis(4) + 1)/2));
        ob3.whileHeld(new hoodUp());
        ob4.whileHeld(new hoodDown());
        ob5.whileHeld(new raiseLift());
        ob6.whileHeld(new lowerLift());
        ob8.whileHeld(new positionControl());
        // ob9.whileHeld(new shootPower(-Robot.shooter.shooterLengthSpeed(Robot.shooter.shooterAreaLength(Robot.vision.firstLime.table.getEntry("ta").getDouble(15.0)))));
        ob10.whenPressed(new toggleControlPanel());
        ob12.whileHeld(new rotationControl());
        // if(Robot.timerino.get() > 5){
        //     if(ob1.get()){ //Shooter LEDs
        //         Robot.arduino.write(new byte[] {0x7}, 1);
        //     }
        //     else if (ob11.get()){ //Celebration button
        //         Robot.arduino.write(new byte[] {0x9}, 1);
        //     }
        //     else{ //Passive
        //         Robot.arduino.write(new byte[] {0x12}, 1);
        //     }

        //     Robot.timerino.reset();
        // }
        
    }
    /**
     * @return left joystick moving forward and backward axis val. Forward = 1; backward = -1
     */
    public static double getlYval(){
        if (Math.abs(ljoy.getY()) < 0.05) return 0;
        else return -ljoy.getY();
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
