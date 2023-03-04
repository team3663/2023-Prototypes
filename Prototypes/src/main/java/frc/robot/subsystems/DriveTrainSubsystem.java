// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;



public class DriveTrainSubsystem extends SubsystemBase {
  /** Creates a new DriveTrainSubsystem. */
  private CANSparkMax motorRight;
  private CANSparkMax motorLeft;
	private DifferentialDrive m_diffDrive = new DifferentialDrive(motorRight, motorLeft);
	public DriveTrainSubsystem(int motorRightId, int motorLeftId) {
    motorRight = new CANSparkMax(motorRightId, MotorType.kBrushed);
		// motor.setInverted(true);
		motorRight.restoreFactoryDefaults();

    motorLeft = new CANSparkMax(motorLeftId, MotorType.kBrushed);
		motorLeft.setInverted(true);
		motorLeft.restoreFactoryDefaults();
	}
	public void arcadeDrive(double xaxisSpeed, double zaxisSpeed){
		m_diffDrive.arcadeDrive(xaxisSpeed,zaxisSpeed);
	}

	public void stop(){
		motorLeft.set(0);
		motorRight.set(0);

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


  
