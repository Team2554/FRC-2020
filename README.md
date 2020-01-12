# FRC-2020
RoboRIO code for the First Robotics Competition Infinite Recharge Game

# Features
## Vision
The robot uses a custom built vision system that tracks the top power-port target.
With this information, the angle and distance to target is tracked. The code for the
vision tracking is in the HawkVision 2020 repository. In this repository is code to
align with the vision target.

##  Motion Profiling
This year's code features trajectory planning and motion control using the Talon SRX.
The drivetrain consists of a Talon SRX and a Victor SPX on each side, with motion magic
following a trajectory calculated onboard the RoboRIO.

## Control Panel Control
Setting a color of and spinning the color panel is completely automated. The RevRobotics
Color Sensor V3 is used to detect the current color of the color wheel, along with a Magnetic
encoder to measure the position of the color wheel.

## Line Detection
The robot has a second RevRobotics Color Sensor V3 to detect the line on the playing field.
This is used to provide information to the driver about the location of the robot, so that
there is a reference point to base driving off of, and a failsafe to target in case the vision
does not work.