package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxRelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.littletonrobotics.junction.Logger;

/**
 * Example of subsystem to control a Johnson Electric JE-PLG-149 brushed DC motor and read it's encoder.
 * using a SparkMAX motor controller.
 * 
 * We used information from this data sheet to write the code and wire the motor to the Spark's encoder port.
 * https://first.wpi.edu/FRC/roborio/Docs/2020-02-12_JE-PLG-149_R2.pdf
 */
public class PlgMotorSubsystem extends SubsystemBase {

  private CANSparkMax spark;
  private RelativeEncoder encoder;

  public PlgMotorSubsystem() {
    spark = new CANSparkMax(5, MotorType.kBrushed);
    spark.restoreFactoryDefaults();

    // The PLG encoder emits 44.4 pulses per revolution of the output shaft.  The two outputs
    // are 45 degrees out of phase so each pulse contains 4 quadrature state transitions.
    // That works out to 44.4 * 4 = 177.6 state transitions per revolution.
    // This is the value REVLib wants for the countsPerRev parameter to getEncoder().
    // Since countsPerRev is an int we round up to 178.
    encoder = spark.getEncoder(SparkMaxRelativeEncoder.Type.kQuadrature, 178);
    encoder.setInverted(true);
    encoder.setAverageDepth(64);
  }

  @Override
  public void periodic() { 

    Logger.getInstance().recordOutput("Motor/Velocity", encoder.getVelocity());
    Logger.getInstance().recordOutput("Motor/Position", encoder.getPosition());
  }

  public void setPower(Double power) {
    spark.set(power);
  }

  public void resetEncoder(){
    encoder.setPosition(0);
  }
}
