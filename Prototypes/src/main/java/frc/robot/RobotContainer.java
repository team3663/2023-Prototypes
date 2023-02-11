// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.ControllerPorts;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;


/**
 * This class is where the bulk of the robot should be declared.
 */
public class RobotContainer {

  private final CommandXboxController driverController = new CommandXboxController(ControllerPorts.DRIVER);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    createSubsystems();
    createCommands();
    configureBindings();
  }

  public void createSubsystems() {

  }

  public void createCommands() {

  }

  /**
   * Use this method to define your trigger->command mappings.
   */
  private void configureBindings() {

    driverController.a().onTrue(new InstantCommand(() -> System.out.println("Hello World!")));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return null;
  }
}
