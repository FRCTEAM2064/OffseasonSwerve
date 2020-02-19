/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package manipulators;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class ControlPanelSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private final I2C.Port i2cPort = I2C.Port.kOnboard;

  // public DoubleSolenoid accessControlPanel = new DoubleSolenoid(5, 4);
  // public TalonSRX runControlPanel = new TalonSRX(RobotMap.controlPanelID);
  public VictorSP runControlPanel = new VictorSP(RobotMap.controlPanelID);

  public ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);

  public ColorMatch m_colorMatcher = new ColorMatch();


  public Color previous_color;

  public boolean isUp = false;

  private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

  public Color[] twoMoreThanDetected = {kBlueTarget, kGreenTarget, kRedTarget, kYellowTarget};
  public Color colorMatch(Color cur){
    for (int i = 0; i < twoMoreThanDetected.length; i++){
      if(twoMoreThanDetected[i].equals(cur)){
        return(twoMoreThanDetected[(i+2)%4]);
      }
    }
    
    return kRedTarget;
  } 

  public int colorDirect(Color cur, Color target){
    int curi = 0;
    int tari = 0;
    for(int i = 0; i<twoMoreThanDetected.length; i ++){
      if(twoMoreThanDetected[i].equals(cur)){
        curi = i;
      }
      else if(twoMoreThanDetected[i].equals(target)){
        tari = i;
      }
    }
    if((tari - curi) > ((4 -curi)%3)){
      return 1;
    }
    return -1;
  }

  // public int colorDirect(Color cur, Color target){
  public ControlPanelSubsystem(){
    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget);
  }

  @Override
  public void initDefaultCommand() {
  }

  public double detectColor(ColorMatchResult current, ColorMatchResult initial){
    if (previous_color.equals(current.color)){
      previous_color = current.color;
      return 0;
    }
    if (current.color.equals(initial.color)){
      previous_color = current.color;
      return 0.5;
    }
    else{
      previous_color = current.color;
      return 0;
    }
  }

  public String readColorString(ColorMatchResult colorMatch){
    if (colorMatch.color == kBlueTarget){
      return "Blue";
    }
    else if (colorMatch.color == kRedTarget){
      return "Red";
    }
    else if (colorMatch.color == kGreenTarget){
      return "Green";
    }
    else if (colorMatch.color == kYellowTarget){
      return "Yellow";
    }
    else{
      return "Unknown";
    }
  }

  public Color takeGameData(String gamedata){
    switch(gamedata.charAt(0)){
      case 'B':
        return kBlueTarget;
      case 'G':
        return kGreenTarget;
      case 'R':
        return kRedTarget;
      case 'Y':
        return kYellowTarget;
      default:
        return previous_color; //If it doesn't move it's because of this code
    }
  }
}
