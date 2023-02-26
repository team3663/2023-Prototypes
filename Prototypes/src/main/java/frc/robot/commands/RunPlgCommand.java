package frc.robot.commands;

import frc.robot.subsystems.PlgMotorSubsystem;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class RunPlgCommand extends CommandBase {

  private final PlgMotorSubsystem subsystem;
  private final DoubleSupplier powerSupplier;
  
  public RunPlgCommand(PlgMotorSubsystem subsystem, DoubleSupplier powerSupplier) {
    this.subsystem = subsystem;
    this.powerSupplier = powerSupplier;

    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    subsystem.setPower(0.0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    subsystem.setPower(powerSupplier.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    subsystem.setPower(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
