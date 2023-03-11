package frc.robot.utility;

import edu.wpi.first.math.MathUtil;

public class DeadBand {
    private static double deadbandWidth = 0.1;

    public static double modifyAxis(double value) {
        value = MathUtil.applyDeadband(value, deadbandWidth);
        return Math.copySign(value * value * value, value);
    }

    public static double modifyToNoPower(double value) {
        return value * 0;
    }
}
