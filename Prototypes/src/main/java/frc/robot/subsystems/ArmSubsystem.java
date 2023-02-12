// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSubsystem extends SubsystemBase {
	private final CANSparkMax shoulderMotor;
	private final CANSparkMax elbowMotorRight;
	private final CANSparkMax elbowMotorLeft;
	private final TalonFX wristMotor;
	private final CANSparkMax intakeMotor;

	public ArmSubsystem(int shoulderMotorId, int elbowRightMotorId, int elbowLeftMotorId,
		int wristMotorId, int intakeMotorId) {
		// Neo shoulder motor
		shoulderMotor = new CANSparkMax(shoulderMotorId, MotorType.kBrushless);
		shoulderMotor.restoreFactoryDefaults();
		// Neo elbow motor
		elbowMotorRight = new CANSparkMax(elbowRightMotorId, MotorType.kBrushless);
		elbowMotorRight.restoreFactoryDefaults();

		// Neo elbow motors
		elbowMotorLeft = new CANSparkMax(elbowLeftMotorId, MotorType.kBrushless);
		elbowMotorLeft.restoreFactoryDefaults();
		elbowMotorLeft.setInverted(true);
		elbowMotorLeft.follow(elbowMotorRight);
		// talon wrist motor
		wristMotor = new TalonFX(wristMotorId);
		// wristMotor.restoreFactoryDefaults();

		// Brushed PLG intake motor
		intakeMotor = new CANSparkMax(intakeMotorId, MotorType.kBrushed);
		intakeMotor.restoreFactoryDefaults();
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}

	public void setPower(double shoulderPower, double elbowPower,
		 	double wristPower, double intakePower){
		shoulderMotor.set(shoulderPower);
		elbowMotorRight.set(elbowPower);
		wristMotor.set(ControlMode.PercentOutput, wristPower);
		intakeMotor.set(intakePower);
	}
}