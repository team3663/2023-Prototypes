package frc.robot;

import frc.robot.commands.RunPlgCommand;
import frc.robot.subsystems.PlgMotorSubsystem;
import frc.robot.Constants.OperatorConstants;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

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

  private final CommandXboxController driveController = new CommandXboxController(OperatorConstants.kDriverControllerPort);

  public RobotContainer() {

    subsystem = new PlgMotorSubsystem();

    command = new RunPlgCommand(subsystem,() -> driveController.getLeftTriggerAxis());
    subsystem.setDefaultCommand(command);      

    driveController.start().onTrue(new InstantCommand(() -> subsystem.resetEncoder(), subsystem));
  }

  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return null;
  }
}
