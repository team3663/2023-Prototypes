// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.CanIds;
import frc.robot.Constants.ControllerPorts;
import frc.robot.commands.RunArmCommand;
import frc.robot.subsystems.ArmSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
// import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

/**
 * This class is where the bulk of the robot should be declared.
 */
public class RobotContainer {

    private final CommandXboxController driverController = new CommandXboxController(ControllerPorts.DRIVER);

    private ArmSubsystem arm;

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        createSubsystems();
        createCommands();
        configureBindings();
    }

    public void createSubsystems() {
        arm = new ArmSubsystem(CanIds.SHOULDER_MOTOR, CanIds.ELBOW_MOTOR,
                CanIds.WRIST_MOTOR, CanIds.INTAKE_MOTOR);
    }

    public void createCommands() {
        arm.setDefaultCommand(getArmCommand());
    }

    public Command getArmCommand(){
        return new RunArmCommand(arm,
            () -> -ControllerHelper.modifyAxis(driverController.getLeftY()),
            () -> -ControllerHelper.modifyAxis(driverController.getLeftX()),
            () -> -ControllerHelper.modifyAxis(driverController.getRightY()),
            () -> -ControllerHelper.modifyAxis(driverController.getRightX()));
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
    public Command getAutonomousCommand() {
        // An example command will be run in autonomous
        return null;
    }
}
