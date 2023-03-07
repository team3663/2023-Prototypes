// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

public class DefaultDriveCommand extends CommandBase {
    private final DrivetrainSubsystem drivetrain;
    private final DoubleSupplier forwardSpeedSupplier;
    private final DoubleSupplier rotateSpeedSupplier;

  /** Creates a new DefaultDriveCommand. */
  public DefaultDriveCommand(DrivetrainSubsystem drivetrain,
        DoubleSupplier forwardSpeedSupplier,
        DoubleSupplier rotateSpeedSupplier) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.drivetrain = drivetrain;
    this.forwardSpeedSupplier = forwardSpeedSupplier;
    this.rotateSpeedSupplier = rotateSpeedSupplier;
    addRequirements(drivetrain);
  }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        drivetrain.stop();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        drivetrain.arcadeDrive(forwardSpeedSupplier.getAsDouble(), rotateSpeedSupplier.getAsDouble());
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        drivetrain.stop();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
