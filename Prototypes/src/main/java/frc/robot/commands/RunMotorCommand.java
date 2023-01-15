// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.MotorSubsystem;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;


public class RunMotorCommand extends CommandBase {

  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final MotorSubsystem motorSubsystem;
  private final DoubleSupplier powerSupplier;

  /**
   * Creates a new RunMotorCommand.
   *
   * @param motorSubsystem The subsystem used by this command.
   */
  public RunMotorCommand(MotorSubsystem motorSubsystem, DoubleSupplier powerSupplier) {
    this.motorSubsystem = motorSubsystem;
    this.powerSupplier = powerSupplier;
    
    addRequirements(motorSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double power = powerSupplier.getAsDouble();
    System.out.println("Power: " + Double.toString((power)));

    motorSubsystem.setPower(power);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    motorSubsystem.setPower(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
