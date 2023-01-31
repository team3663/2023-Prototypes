// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.IntakeSubsystem;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;


public class IntakeCommand extends CommandBase {

  private final IntakeSubsystem subsystem;
  private final DoubleSupplier wristPowerSupplier;
  private final DoubleSupplier intakePowerSupplier;

  /**
   * Creates a new RunMotorCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public IntakeCommand(IntakeSubsystem subsystem, DoubleSupplier wristPowerSupplier, DoubleSupplier intakePowerSupplier) {

    this.subsystem = subsystem;
    this.wristPowerSupplier = wristPowerSupplier;
    this.intakePowerSupplier = intakePowerSupplier;
    
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    double wristPower = wristPowerSupplier.getAsDouble();
    double intakePower = intakePowerSupplier.getAsDouble();

    System.out.println("===== Execute wrist:" + Double.toString(wristPower) + " intake:" + Double.toString(intakePower));

    subsystem.setWristPower(wristPower);
    subsystem.setIntakePower(intakePower);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    subsystem.setWristPower(0);
    subsystem.setIntakePower(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
