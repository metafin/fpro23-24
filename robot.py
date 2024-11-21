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

        # Define Xbox controller
        self.controller = wpilib.XboxController(0)  # Xbox controller on port 0

    def teleopPeriodic(self):
        # Use the Xbox controller's sticks for arcade driving
        forward = -self.controller.getLeftY()  # Forward/backward on the left stick
        turn = self.controller.getRightX()    # Turning on the right stick
        self.drive.arcadeDrive(forward, turn)

if __name__ == "__main__":
    wpilib.run(MyRobot)
