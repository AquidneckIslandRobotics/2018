package org.usfirst.frc.team78.robot.commands;

import org.usfirst.frc.team78.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Turn extends Command {

	double angle;
	
    public Turn() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	Robot.chassis.turnController.enable();
//    	Robot.chassis.turn(angle);
    	Robot.chassis.turnController.setContinuous(true);
    	Robot.chassis.turnController.setInputRange(-180.0f, 180.0f);
    	Robot.chassis.turnController.setOutputRange(-1.0, 1.0);
    }
   

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double speed = Robot.chassis.turnSpeed.getSpeed();
    	Robot.chassis.setSpeed(speed, speed);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !(Robot.chassis.turnController.isEnabled());
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
