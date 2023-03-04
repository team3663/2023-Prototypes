// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.ArcadeDrive;

import frc.robot.subsystems.DriveTrainSubsystem;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;


/**
 * This class is where the bulk of the robot should be declared.
 */
public class RobotContainer {

  // The robot's subsystems and commands are defined here...
  private DriveTrainSubsystem drivetrain;

  private final CommandXboxController driverController = new CommandXboxController(
      OperatorConstants.DRIVE_CONTROLLER_PORT);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    createSubsystems();
    createCommands();
    configureBindings();
  }

  public void createSubsystems() {
    drivetrain= new DriveTrainSubsystem(Constants.CanIds.MOTOR_LEFT_ID, Constants.CanIds.MOTOR_RIGHT_ID);
    drivetrain.setDefaultCommand(getDefaultDriveCommand());
  }

  public void createCommands() {}

  public Command getDefaultDriveCommand(){
    return new ArcadeDrive(drivetrain, () -> -driverController.getLeftY(), () -> driverController.getRightX());
  }

  /**
   * Use this method to define your trigger->command mappings.
   */
  private void configureBindings() {}

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  // public Command getAutonomousCommand() {
  //   // An example command will be run in autonomous
  //   // return Autos.exampleAuto(m_exampleSubsystem);
  //   return null;
  // }
}
