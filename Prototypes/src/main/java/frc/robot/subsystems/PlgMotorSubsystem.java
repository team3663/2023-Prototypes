package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxRelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.littletonrobotics.junction.Logger;

public class PlgMotorSubsystem extends SubsystemBase {

  private CANSparkMax spark;
  private RelativeEncoder encoder;

  public PlgMotorSubsystem() {
    spark = new CANSparkMax(1, MotorType.kBrushed);
    spark.restoreFactoryDefaults();

    // This countsPerRev value (176) seems odd, based on expirements we are guessing that it means
    // the number of edges (both rising and falling) per revolution for BOTH quadrature
    // channels combined.
    // So the specifed 44 pulses per revolution (both A & B combined) times 4 (4 edges per cycle).
    // This seems weird but it results the correct velocity from from getVelocity()
    encoder = spark.getEncoder(SparkMaxRelativeEncoder.Type.kQuadrature, 176);
    encoder.setInverted(true);
    encoder.setAverageDepth(64);
  }

  @Override
  public void periodic() { 
    Logger.getInstance().recordOutput("Motor/Velocity", encoder.getVelocity());
    Logger.getInstance().recordOutput("Motor/Position", encoder.getPosition());
    Logger.getInstance().recordOutput("Motor/CountsPerRev", encoder.getCountsPerRevolution());
    Logger.getInstance().recordOutput("Motor/VelocityConversionFactor", encoder.getVelocityConversionFactor());
  }

  public void setPower(Double power){
    spark.set(power);
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
  public void resetEncoder(){
    encoder.setPosition(0);
  }
}
