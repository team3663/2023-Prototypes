// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.DefaultDriveCommand;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.utility.DeadBand;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

/**
 * This class is where the bulk of the robot should be declared.
 */
public class RobotContainer {

    // The robot's subsystems and commands are defined here...
    DrivetrainSubsystem tankDriveSubsystem;
    DefaultDriveCommand driveCommand;
    private final CommandXboxController driverController = new CommandXboxController(
            OperatorConstants.DRIVE_CONTROLLER_PORT);

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        createSubsystems();
        createCommands();
        configureBindings();
        driverController.a().toggleOnTrue(new InstantCommand(() -> driveCommand.slow()));
        driverController.b().toggleOnTrue(new InstantCommand(() -> driveCommand.boost()));
        driverController.x().toggleOnTrue(new InstantCommand(() -> driveCommand.stop()));
        driverController.y().toggleOnTrue(new InstantCommand(() -> driveCommand.full()));
    }

    public void createSubsystems() {
        tankDriveSubsystem = new DrivetrainSubsystem();
    }

    public void createCommands() {
        driveCommand = new DefaultDriveCommand(tankDriveSubsystem,
            () -> DeadBand.modifyAxis(-driverController.getLeftY()));

        tankDriveSubsystem.setDefaultCommand(driveCommand);
    }

  /**
 * 
 */
    private void configureBindings() {
        
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An example command will be run in autonomous
        // return Autos.exampleAuto(m_tankDriveSubsystem,
        //         () -> DeadBand.modifyToNoPower(m_driverController.getLeftTriggerAxis()));
        return new PrintCommand("getAutonomousCommand");
    }
}
