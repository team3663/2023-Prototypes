package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.CanIds;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.IntakeCommand;
import frc.robot.subsystems.IntakeSubsystem;

/**
 * This class is where the bulk of the robot should be declared.
 */
public class RobotContainer {

  private final CommandXboxController driverController = new CommandXboxController(
      OperatorConstants.DRIVE_CONTROLLER_PORT);


  private IntakeSubsystem intake;
  private IntakeCommand intakeCommand;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    createSubsystems();
    createCommands();
    configureBindings();
  }

  public void createSubsystems() {

    intake = new IntakeSubsystem(CanIds.WRIST_MOTOR, CanIds.INTAKE_MOTOR);
  }

  public void createCommands() {

    intakeCommand = new IntakeCommand(
      intake,
      () -> -driverController.getLeftY(),
      () -> -driverController.getRightY()
    );


    intake.setDefaultCommand(intakeCommand);
  }

  /**
   * Use this method to define your trigger->command mappings.
   */
  private void configureBindings() {

    driverController.a().onTrue(new InstantCommand(() -> System.out.println("Click")));

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
