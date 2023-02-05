package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.CanIds;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.RunSparkCommand;
import frc.robot.commands.RunTalonCommand;
import frc.robot.commands.utility.ControllerHelper;
import frc.robot.subsystems.SparkMaxSubsystem;
import frc.robot.subsystems.TalonSubsystem;

/**
 * This class is where the bulk of the robot should be declared.
 */
public class RobotContainer {

  private final CommandXboxController driverController = new CommandXboxController(
      OperatorConstants.DRIVE_CONTROLLER_PORT);

  private TalonSubsystem talonSubsystem;
  private SparkMaxSubsystem sparkSubsystem;
  private RunSparkCommand runSparkCommand;
  private RunTalonCommand runTalonCommand;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    createSubsystems();
    createCommands();
    configureBindings();
  }

  public void createSubsystems() {

    talonSubsystem = new TalonSubsystem(CanIds.TALON);
    sparkSubsystem = new SparkMaxSubsystem(CanIds.SPARK);

  }

  public void createCommands() {

    runTalonCommand = new RunTalonCommand(talonSubsystem, () -> driverController.getRightY());
    sparkSubsystem.setDefaultCommand(runTalonCommand);

    runSparkCommand = new RunSparkCommand(sparkSubsystem, () -> driverController.getLeftY());
    sparkSubsystem.setDefaultCommand(runSparkCommand);
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
