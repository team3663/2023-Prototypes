// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSubsystem extends SubsystemBase {
	private final CANSparkMax shoulderMotor;
	private final TalonFX elbowMotor;
	private final TalonFX wristMotor;
	private final CANSparkMax intakeMotor;

	public ArmSubsystem(int shoulderMotorId, int elbowMotorId,
		int wristMotorId, int intakeMotorId) {
		// Neo shoulder motor
		shoulderMotor = new CANSparkMax(shoulderMotorId, MotorType.kBrushless);
		shoulderMotor.restoreFactoryDefaults();
    	shoulderMotor.setIdleMode(IdleMode.kBrake);
		shoulderMotor.setInverted(true);

		// Neo elbow motor
		elbowMotor = new TalonFX(elbowMotorId);
		elbowMotor.configFactoryDefault();
		elbowMotor.setNeutralMode(NeutralMode.Brake);
		elbowMotor.setInverted(TalonFXInvertType.CounterClockwise);

		// talon wrist motor
		wristMotor = new TalonFX(wristMotorId);
		wristMotor.configFactoryDefault();
		wristMotor.setNeutralMode(NeutralMode.Brake);
		wristMotor.setInverted(TalonFXInvertType.CounterClockwise);

		// Brushed PLG intake motor
		intakeMotor = new CANSparkMax(intakeMotorId, MotorType.kBrushed);
		intakeMotor.restoreFactoryDefaults();
    	intakeMotor.setIdleMode(IdleMode.kBrake);
		intakeMotor.setInverted(true);
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}

	public void setPower(double shoulderPower, double elbowPower,
		 	double wristPower, double intakePower){
		shoulderMotor.set(shoulderPower);
		elbowMotor.set(ControlMode.PercentOutput, elbowPower);
		wristMotor.set(ControlMode.PercentOutput, wristPower);
		intakeMotor.set(intakePower);
	}
}