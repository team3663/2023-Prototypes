// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.RunPlgCommand;
import frc.robot.subsystems.PlgMotorSubsystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final RunPlgCommand command;
  private final PlgMotorSubsystem subsystem;

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final XboxController driveController;
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    driveController = new XboxController(0);
    subsystem = new PlgMotorSubsystem();


    command = new RunPlgCommand(subsystem,() -> driveController.getLeftTriggerAxis());
    subsystem.setDefaultCommand(command);      

    new JoystickButton(driveController, Button.kStart.value).onTrue(new InstantCommand(() -> subsystem.resetEncoder(),subsystem));
  }

  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return null;
  }
}
