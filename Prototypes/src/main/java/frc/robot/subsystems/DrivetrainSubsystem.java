// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DrivetrainSubsystem extends SubsystemBase {
	private CANSparkMax leftMotor;
	private CANSparkMax rightMotor;
	private final DifferentialDrive diffDrive;

	public DrivetrainSubsystem(int leftMotorId, int rightMotorId) {
		leftMotor = new CANSparkMax(leftMotorId, MotorType.kBrushed);
		leftMotor.restoreFactoryDefaults();
		leftMotor.setInverted(false);
		rightMotor = new CANSparkMax(rightMotorId, MotorType.kBrushed);
		rightMotor.restoreFactoryDefaults();
		rightMotor.setInverted(true);

		diffDrive = new DifferentialDrive(leftMotor, rightMotor);
	}

	public void arcadeDrive(double xAxisSpeed, double zAxisRotate) {
		diffDrive.arcadeDrive(xAxisSpeed,zAxisRotate);

	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}

	public void stop() {
		leftMotor.set(0);
		rightMotor.set(0);
	}

	public void driveLeft(double speed) {
		leftMotor.set(speed);

	}

	public void driveRight(double speed) {
		rightMotor.set(speed);
		
	}

}
