package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.LedSubsystem;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

/**
 * This class is where the bulk of the robot should be declared.
 */
public class RobotContainer {

  // The robot's subsystems and commands are defined here...
  private final LedSubsystem ledSubsystem = new LedSubsystem(0, 1, 2);

  private final CommandXboxController driveController = new CommandXboxController(
      OperatorConstants.DRIVE_CONTROLLER_PORT);

  private final Color8Bit red = new Color8Bit(255, 0, 0);
  private final Color8Bit green = new Color8Bit(0, 255, 0);
  private final Color8Bit blue = new Color8Bit(0, 0, 255);
  private final Color8Bit yellow = new Color8Bit(255, 255, 0);
  private final Color8Bit cubeColor = new Color8Bit(170, 0, 255);
  private final Color8Bit coneColor = new Color8Bit(255, 234, 0);
  private final Color8Bit darkRed = new Color8Bit(128,0,0);

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

    driveController.b().onTrue(new InstantCommand(() -> ledSubsystem.setColor(red)));
    driveController.a().onTrue(new InstantCommand(() -> ledSubsystem.setColor(green)));
    driveController.x().onTrue(new InstantCommand(() -> ledSubsystem.setColor(blue)));
    driveController.y().onTrue(new InstantCommand(() -> ledSubsystem.setColor(yellow)));
    driveController.rightBumper().onTrue(new InstantCommand(() -> ledSubsystem.setColor(cubeColor)));
    driveController.leftBumper().onTrue(new InstantCommand(() -> ledSubsystem.setColor(coneColor)));

    driveController.povLeft().onTrue(new InstantCommand(() -> ledSubsystem.setColor(darkRed)));
    driveController.povRight().onTrue(new InstantCommand(() -> ledSubsystem.setColor(red)));
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
