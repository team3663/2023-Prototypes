// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DrivetrainSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import java.util.function.DoubleSupplier;

/** An example command that uses an example subsystem. */
public class DefaultDriveCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField", "unused"})
  private final DrivetrainSubsystem subsystem;
  private final DoubleSupplier leftPower;
  private Double doublePower;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public DefaultDriveCommand(DrivetrainSubsystem subsystem, DoubleSupplier leftPower) {
    this.subsystem = subsystem;
    this.leftPower = leftPower;
    this.doublePower = leftPower.getAsDouble();
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    subsystem.setPower(0.0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    subsystem.setPower(doublePower);
  //?}
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    subsystem.setPower(0.0);
  }

  public void boost() {
    doublePower = leftPower.getAsDouble() * 1.1;
  }

  public void slow() {
    doublePower = leftPower.getAsDouble() * 0.9;
  }

  public void stop() {
    subsystem.setPower(0.0);
  }

  public void full() {
    subsystem.setPower(1.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  public DrivetrainSubsystem getDrivetrainSubsystem() {
    return subsystem;
  }
}
