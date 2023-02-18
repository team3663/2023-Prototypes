// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ArmSubsystem;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class RunArmCommand extends CommandBase {
    private final ArmSubsystem arm;
    private DoubleSupplier shoulderPower;
    private DoubleSupplier elbowPower;
    private DoubleSupplier wristPower;
    private DoubleSupplier intakePower;

    public RunArmCommand(ArmSubsystem arm,
            DoubleSupplier shoulderPower, 
            DoubleSupplier elbowPower,
            DoubleSupplier wristPower,
            DoubleSupplier intakePower) {
        this.arm = arm;
        this.shoulderPower = shoulderPower;
        this.elbowPower = elbowPower;
        this.wristPower = wristPower;
        this.intakePower = intakePower;

        addRequirements(arm);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        arm.setPower(0, 0, 0, 0);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        arm.setPower(shoulderPower.getAsDouble(),
            elbowPower.getAsDouble(),
            wristPower.getAsDouble(),
            intakePower.getAsDouble());
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        arm.setPower(0, 0, 0, 0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
