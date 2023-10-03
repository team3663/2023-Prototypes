// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public final class Autos {
  /** Example static factory for an autonomous command. */
  public static CommandBase exampleAuto(ExampleSubsystem subsystem) {
    return Commands.sequence(subsystem.exampleMethodCommand(), new ExampleCommand(subsystem));
  }

  public static Command testAuto(Arm arm, Drivetrain drivetrain, Intake intake) {
    return Commands.sequence(
      arm.home(),
      drivetrain.driveForward(Units.feetToMeters(2.5))
        .alongWith(arm.moveToAngle(Rotation2d.fromDegrees(45.0))),
      intake.spin(0.5).withTimeout(5.0),
      drivetrain.driveForward(Units.feetToMeters(-2.5))
        .alongWith(arm.moveToAngle(Rotation2d.fromDegrees(0.0)))
    );
  }

  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }
}
