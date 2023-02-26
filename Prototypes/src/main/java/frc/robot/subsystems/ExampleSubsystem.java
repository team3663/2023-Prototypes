// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxRelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxAlternateEncoder.Type;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ExampleSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  private CANSparkMax spark;
  private RelativeEncoder encoder;

  private GenericEntry ntRPM;

  private ShuffleboardTab tab;


  public ExampleSubsystem() {
    spark = new CANSparkMax(1, MotorType.kBrushed);
    encoder = spark.getEncoder(SparkMaxRelativeEncoder.Type.kQuadrature, 45);

    encoder.setPositionConversionFactor(1);//Intake Gear Radio

    tab = Shuffleboard.getTab("Moter");

    ntRPM = tab.add("RPM", 0)
    .withPosition(0, 0)
    .withSize(1, 1)
    .getEntry();
  }

  @Override
  public void periodic() {
    ntRPM.setDouble(encoder.getVelocity());    
  }

  public void setPower(Double power){
    spark.set(power);
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
  public void resetEncoder(){
    encoder.setPosition(0);
  }
}
