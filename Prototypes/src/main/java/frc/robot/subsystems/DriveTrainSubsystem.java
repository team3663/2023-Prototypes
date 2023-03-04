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
  private CANSparkMax motorRight;
  private CANSparkMax motorLeft;

	public DrivetrainSubsystem(int motorRightId, int motorLeftId) {
    	motorRight = new CANSparkMax(motorRightId, MotorType.kBrushed);
		// motor.setInverted(true);
		motorRight.restoreFactoryDefaults();

    	motorLeft = new CANSparkMax(motorLeftId, MotorType.kBrushed);
		motorLeft.setInverted(true);
		motorLeft.restoreFactoryDefaults();
	}

	@Override
	public void periodic() {
		System.out.println(motorLeft.getOutputCurrent());
    	System.out.println(motorRight.getOutputCurrent());
	}

	public void setPower(double power) {
		motorLeft.set(power);
		System.out.println("===== output current: " +motorLeft.getOutputCurrent());

    	motorRight.set(power);
		System.out.println("===== output current: " +motorRight.getOutputCurrent());
    
	}
}


  
