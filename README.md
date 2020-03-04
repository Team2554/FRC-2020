# WARHAWKS Team 2554
RoboRIO code for the Infinite Recharge robot of Team 2554

# Key Features
Our robot's programming is so good, even a 5 year old could win a match with it! It comes equipped with unparalleled autonomous and driver assistance technologies, making it easy to perform key tasks in the game.
## Vision
The robot uses a custom-built vision target tracking system along with latency-compensated closed-loop control to automatically lock on to the target. The Raspberry Pi code for vision target tracking can be found in the HawkVision 2.0 repository.

##  Motion Profiling
This year's code features trajectory planning and motion control through the WPILib Trajectory pipeline. It is used for following a trajectory quickly and accurately in order to score autonomous points.

## Automatic Control Panel Manipulation
The robot features a RevRobotics Color Sensor and a motor with an encoder to accurately control the control panel with the click of a joystick button.

## Line Detection
The robot has a second RevRobotics Color Sensor V3 to detect the line on the playing field. This is paired with a auto-stop feature to automatically bring the robot to a halt if it detects a white line if the driver wills to do so. This allows for accurate targeting and consistency during the teleoperated period.
