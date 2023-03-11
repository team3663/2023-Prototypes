// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.DefaultDriveCommand;
import frc.robot.subsystems.DrivetrainSubsystem;

/**
 * This class is where the bulk of the robot should be declared.
 */
public class RobotContainer {

  // The robot's subsystems and commands are defined here...
  // private final DrivetrainSubsystem m_exampleSubsystem = new DrivetrainSubsystem();

  private final CommandXboxController driverController = new CommandXboxController(
      OperatorConstants.DRIVER_CONTROLLER_PORT);
  private DrivetrainSubsystem drivetrain;
  private Command defaultDriveCommand;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    createSubsystems();
    createCommands();
    configureBindings();
    drivetrain.setDefaultCommand(defaultDriveCommand);
  }

  public void createSubsystems() {
    drivetrain = new DrivetrainSubsystem(Constants.CanIds.LEFT_MOTOR_ID, Constants.CanIds.RIGHT_MOTOR_ID);

  }

  public void createCommands() {
    defaultDriveCommand = new DefaultDriveCommand(drivetrain, () -> -driverController.getLeftY(), () -> -driverController.getRightX());
  }

  /**
   * Use this method to define your trigger->command mappings.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    // new Trigger(m_exampleSubsystem::exampleCondition)
    //     .onTrue(new ExampleCommand(m_exampleSubsystem));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is
    // pressed,
    // cancelling on release.
    // m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    // return Autos.exampleAuto(m_exampleSubsystem);
    return new PrintCommand("Auto Command");
    }
}
