// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SparkMaxSubsystem extends SubsystemBase {

	private final CANSparkMax motor;

	public SparkMaxSubsystem(int motorId) {

		motor = new CANSparkMax(motorId, MotorType.kBrushed);
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}

	public void setPower(double power) {
		motor.set(power);
	}
}