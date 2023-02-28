package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.util.Color8Bit;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class LedSubsystem extends SubsystemBase {

    // RoboRio PWM ports have a max output frequency of 150kHz
    // We are targeting a PWM frequency of 500Hz to reduce flicker
    private final double maxPulseWidthMs = 2.0;
    private final double minPulseWidthMs = 0.0;
    private final double centerPulseWidthMs = minPulseWidthMs + ((maxPulseWidthMs - minPulseWidthMs) / 2);

    // We are not driving an H-bridge so we don't need to worry about shoot-through, no deadband is needed.
    private final double deadbandMaxMs = 0.0;    
    private final double deadbandMinMs = 0.0;

    private PWM red;
    private PWM green;
    private PWM blue;

    public LedSubsystem(int redChannel, int greenChannel, int blueChannel) {

        red = new PWM(redChannel);
        green = new PWM(greenChannel);
        blue = new PWM(blueChannel);

        red.setBounds(maxPulseWidthMs, deadbandMaxMs, centerPulseWidthMs, deadbandMinMs, minPulseWidthMs);
        green.setBounds(maxPulseWidthMs, deadbandMaxMs, centerPulseWidthMs, deadbandMinMs, minPulseWidthMs);
        blue.setBounds(maxPulseWidthMs, deadbandMaxMs, centerPulseWidthMs, deadbandMinMs, minPulseWidthMs);
    }

    public void setColor(Color8Bit color) {
        red.setRaw(color.red);
        green.setRaw(color.green);
        blue.setRaw(color.blue);
    }  
}
