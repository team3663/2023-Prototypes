// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxAnalogSensor.Mode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;



public class DriveTrainSubsystem extends SubsystemBase {
  /** Creates a new DriveTrainSubsystem. */
  private final CANSparkMax motor;

	public DrivetrainSubsystem(int SPARKmotorIdRIGHT, int SPARKmotorIdLEFT) {
    motor = new CANSparkMax(SPARKmotorIdRIGHT, MotorType.kBrushed);
		motor.setInverted(true);
		motor.restoreFactoryDefaults();

    motor = new CANSparkMax(SPARKmotorIdLEFT, MotorType.kBrushed);
		motor.setInverted(true);
		motor.restoreFactoryDefaults();
	}

	@Override
	public void periodic() {
		System.out.println(motor.getOutputCurrent());
	}

	public void setPower(double power) {
		motor.set(power);
		System.out.println("===== output current: " +motor.getOutputCurrent());
	}
}


  
