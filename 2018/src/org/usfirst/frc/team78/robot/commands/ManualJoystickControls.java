package org.usfirst.frc.team78.robot.commands;

import org.usfirst.frc.team78.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ManualJoystickControls extends Command {

    public ManualJoystickControls() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.armavator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.armavator.manualJoystickControls();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.armavator.stopArm();
    	Robot.armavator.stopElevator();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.armavator.stopArm();
    	Robot.armavator.stopElevator();
    }
}
