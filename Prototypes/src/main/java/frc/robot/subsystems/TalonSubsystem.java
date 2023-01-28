// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TalonSubsystem extends SubsystemBase {

	private final TalonFX motor;

	public TalonSubsystem(int motorId) {
		System.out.println("===== MotorSubsystem Constructor");

		motor = new TalonFX(motorId);
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}

	@Override
	public void simulationPeriodic() {
		// This method will be called once per scheduler run during simulation
	}

	public void setPower(double power) {
		System.out.println("=====  MotorSubsystem.setPower");
		motor.set(ControlMode.PercentOutput, power);
	}
}