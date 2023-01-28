package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.CanIds;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.RunMotorCommand;
import frc.robot.subsystems.TalonSubsystem;

/**
 * This class is where the bulk of the robot should be declared.
 */
public class RobotContainer {

  private final CommandXboxController driverController = new CommandXboxController(
      OperatorConstants.DRIVE_CONTROLLER_PORT);

  private TalonSubsystem motorSubsystem;
  private RunMotorCommand runMotorCommand;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    createSubsystems();
    createCommands();
    configureBindings();
  }

  public void createSubsystems() {

    motorSubsystem = new TalonSubsystem(CanIds.MOTOR1_ID);

  }

  public void createCommands() {

    runMotorCommand = new RunMotorCommand(motorSubsystem, () -> driverController.getLeftY());
    motorSubsystem.setDefaultCommand(runMotorCommand);
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
