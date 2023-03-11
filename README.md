# 2023-Prototypes
Prototyping code for 2023 season

This project is using Mechanical Advantage's [Advantage Kit](https://github.com/Mechanical-Advantage/AdvantageKit).

Remember:
1. Extends LoggedRobot instead of TimedRobot for your Robot.java class
2. "Shim" out WPILib in your Build.gradle as described in the Advantage Kit's document. 

```
// This allows AdvantageKit to shim out the underlying WPILib classes that the need to replace
configurations.all {
    exclude group: "edu.wpi.first.wpilibj"
}
```
