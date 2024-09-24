// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import static frc.robot.Constants.LauncherConstants.*;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CANLifter extends SubsystemBase {
  CANSparkMax m_rightArm;
  CANSparkMax m_leftArm;

  /** Creates a new Launcher. */
  public CANLifter() {
    m_rightArm = new CANSparkMax(kArmRight, MotorType.kBrushed);
    m_leftArm = new CANSparkMax(kArmLeft, MotorType.kBrushed);

    m_rightArm.setSmartCurrentLimit(kArmRightCurrentLimit);
    m_leftArm.setSmartCurrentLimit(kArmLeftCurrentLimit);
  }

  /**
   * This method is an example of the 'subsystem factory' style of command creation. A method inside
   * the subsytem is created to return an instance of a command. This works for commands that
   * operate on only that subsystem, a similar approach can be done in RobotContainer for commands
   * that need to span subsystems. The Subsystem class has helper methods, such as the startEnd
   * method used here, to create these commands.
   */
  public Command liftArms(double speed, boolean reverse) {
    // The startEnd helper method takes a method to call when the command is initialized and one to
    // call when it ends

    return this.startEnd(
        // When the command is initialized, set the wheels to the intake speed values
        () -> {
          System.out.println("> lifArms");  
          System.out.println(speed);  
          System.out.println(reverse);  
          liftArm(speed, reverse);
        },
        // When the command stops, stop the wheels
        () -> {
          stop();
        });
  }

  

  // An accessor method to set the speed (technically the output percentage) of the launch wheel
  public void liftArm(double speed, boolean reverse) {
    System.out.println(">> lifArm");  
    System.out.println(speed);  
    System.out.println(reverse);  
    if (reverse) {
        speed = speed * -1;
    }
    System.out.println(">>> actual speed");  
    System.out.println(speed);  
    m_rightArm.set(speed);
    m_leftArm.set(speed * -1);
  }


  // A helper method to stop both wheels. You could skip having a method like this and call the
  // individual accessors with speed = 0 instead
  public void stop() {
    m_leftArm.set(0);
    m_rightArm.set(0);
  }
}
