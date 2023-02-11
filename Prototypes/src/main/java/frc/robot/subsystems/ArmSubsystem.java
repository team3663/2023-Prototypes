// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSubsystem extends SubsystemBase {

	private final CANSparkMax wristMotor;
	private final CANSparkMax intakeMotor;

	public ArmSubsystem(int wristMotorId, int intakeMotorId) {

		// Neo wrist motor
		wristMotor = new CANSparkMax(wristMotorId, MotorType.kBrushless);
		wristMotor.restoreFactoryDefaults();

		// Brushed PLG intake motor
		intakeMotor = new CANSparkMax(intakeMotorId, MotorType.kBrushed);
		intakeMotor.restoreFactoryDefaults();
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}
	
	public void setWristPower(double power) {
		wristMotor.set(power);
	}

	public void setIntakePower(double power) {
		intakeMotor.set(power);
	}
}
