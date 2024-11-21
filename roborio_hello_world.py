import wpilib
from wpilib.drive import DifferentialDrive

class MyRobot(wpilib.TimedRobot):
    def robotInit(self):
        # Define motor controllers
        self.left_motor = wpilib.PWMSparkMax(0)  # Left motor on PWM port 0
        self.right_motor = wpilib.PWMSparkMax(1)  # Right motor on PWM port 1
        self.right_motor.setInverted(True)  # Invert the right motor direction

        # Create drivetrain
        self.drive = DifferentialDrive(self.left_motor, self.right_motor)

        # Define joystick for control
        self.stick = wpilib.Joystick(0)  # Joystick on port 0

    def teleopPeriodic(self):
        # Arcade drive using joystick inputs
        self.drive.arcadeDrive(-self.stick.getY(), self.stick.getX())

if __name__ == "__main__":
    wpilib.run(MyRobot)
