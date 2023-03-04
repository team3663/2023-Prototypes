// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DrivetrainSubsystem extends SubsystemBase {
  /** Creates a new DrivetrainSubsystem. */
  private CANSparkMax rightMotor; // make the variable not final
  private CANSparkMax leftMotor;

  private final DifferentialDrive diffDrive = new DifferentialDrive(leftMotor, rightMotor);

  public DrivetrainSubsystem(int leftMotorId, int rightMotorId) {
    leftMotor = new CANSparkMax(leftMotorId, MotorType.kBrushed);
		leftMotor.setInverted(true);
		leftMotor.restoreFactoryDefaults();

    rightMotor = new CANSparkMax(rightMotorId, MotorType.kBrushed);
		rightMotor.restoreFactoryDefaults();
  }

  public void arcadeDrive(double xAxisSpeed, double zAxisRotate) {
    diffDrive.arcadeDrive(xAxisSpeed, zAxisRotate);
  }

  public void stop(){
    leftMotor.set(0);
    rightMotor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
