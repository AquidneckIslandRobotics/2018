package org.usfirst.frc.team78.robot.commands;


import org.usfirst.frc.team78.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Shift extends Command {

    public Shift() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(Robot.chassis.shiftIsHigh) {
    		Robot.chassis.shift(false);
    	}else if(!Robot.chassis.shiftIsHigh) {
    		Robot.chassis.shift(true);
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.chassis.shiftIsHigh = !Robot.chassis.shiftIsHigh;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
