package org.usfirst.frc.team78.robot.commands;

import org.usfirst.frc.team78.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GetDistanceFromPoints extends Command {

	double x1, x2, y1, y2;
	
    public GetDistanceFromPoints(double X1, double Y1, double X2, double Y2) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	X1 = x1;
    	Y1 = y1;
    	X2 = x2;
    	Y2 = y2;
    	requires(Robot.motionProfile);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.motionProfile.getDistanceFromPoints(x1, y1, x2, y2);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true; // changed to true so we wont be stuck in the command
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
