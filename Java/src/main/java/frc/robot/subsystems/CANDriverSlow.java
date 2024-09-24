// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import static frc.robot.Constants.DrivetrainConstants.*;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CANDriverSlow extends SubsystemBase {
  CANSparkMax leftFront;
  CANSparkMax leftRear;
  CANSparkMax rightFront;
  CANSparkMax rightRear;

  // CANSparkMax m_launchWheel;
  // CANSparkMax m_feedWheel;

  /** Creates a new Launcher. */
  public CANDriverSlow() {
    leftFront = new CANSparkMax(kLeftFrontID, MotorType.kBrushed);
    leftRear = new CANSparkMax(kLeftRearID, MotorType.kBrushed);
    rightFront = new CANSparkMax(kRightFrontID, MotorType.kBrushed);
    rightRear = new CANSparkMax(kRightRearID, MotorType.kBrushed);

    leftFront.setSmartCurrentLimit(kCurrentLimit);
    leftRear.setSmartCurrentLimit(kCurrentLimit);
    rightFront.setSmartCurrentLimit(kCurrentLimit);
    rightRear.setSmartCurrentLimit(kCurrentLimit);
    
  }

  public Command smallMovement(String direction) {

    double speed = 0;

    // set speed based on "direction" variable
    if (direction == "back") {
      speed = -0.1;
    }

    final double thisSpeed = speed;

    return this.startEnd(
        // When the command is initialized, set the wheels to the intake speed values
        () -> {
          leftFront.set(thisSpeed);
          rightFront.set(thisSpeed);

          leftRear.follow(leftFront);
          rightRear.follow(rightFront);
          leftFront.setInverted(true);
          rightFront.setInverted(false);
        },
        // When the command stops, stop the wheels
        () -> {
          stop();
        });
  }

  // A helper method to stop all wheels.
  public void stop() {
    leftFront.set(0);
    rightFront.set(0);
    leftRear.set(0);
    rightRear.set(0);
  }
}
