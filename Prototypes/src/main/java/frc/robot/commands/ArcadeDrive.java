// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DrivetrainSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

import java.util.function.DoubleSupplier;



public class ArcadeDrive extends CommandBase {
  private final DrivetrainSubsystem drivetrain;
  private final DoubleSupplier xaxisSpeedSupplier;
  private final DoubleSupplier zaxisRotateSupplier;


  public ArcadeDrive(
      DrivetrainSubsystem drivetrain,
      DoubleSupplier xaxisSpeedSupplier,
      DoubleSupplier zaxisRotateSupplier) {
      this.drivetrain = drivetrain;
      this.xaxisSpeedSupplier = xaxisSpeedSupplier;
      this.zaxisRotateSupplier = zaxisRotateSupplier;
      addRequirements(drivetrain);
    }
  
  
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drivetrain.arcadeDrive(xaxisSpeedSupplier.getAsDouble(), zaxisRotateSupplier.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.stop();
  }
  @Override
  public boolean isFinished() {
    return false;
  }
}




