// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DrivetrainSubsystem extends SubsystemBase {
  private final CANSparkMax leftMotor = new CANSparkMax(Constants.CanIds.L_MOTOR_ID, MotorType.kBrushed);
  private final CANSparkMax rightMotor = new CANSparkMax(Constants.CanIds.R_MOTOR_ID, MotorType.kBrushed);
  /** Creates a new ExampleSubsystem. */
  public DrivetrainSubsystem() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  public void setPower(double powerL) {
    leftMotor.set(powerL);
    rightMotor.set(-powerL);
  }
}