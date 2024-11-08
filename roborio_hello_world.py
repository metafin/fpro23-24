import wpilib


class MyRobot(wpilib.TimedRobot):
    def robotInit(self):
        """This function is called when the robot starts"""
        print("Hello World!")

    def teleopPeriodic(self):
        """This function is called periodically during teleop"""
        print("Robot is running!")


if __name__ == "__main__":
    wpilib.run(MyRobot)