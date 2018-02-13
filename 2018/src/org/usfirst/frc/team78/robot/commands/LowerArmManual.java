package org.usfirst.frc.team78.robot.commands;

import org.usfirst.frc.team78.robot.Robot;
import org.usfirst.frc.team78.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LowerArmManual extends Command {
	
	double speed;
	boolean canLower;
	
    public LowerArmManual(double armSpeed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	speed = armSpeed;
    	canLower = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(Robot.armavator.getArmPot() > RobotMap.ARM_POT_LOWER_LIMIT) canLower = true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.armavator.getArmPot() > RobotMap.ARM_POT_LOWER_LIMIT) canLower = true;
    	else canLower = false;
    	if(canLower) Robot.armavator.setArm(-speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !canLower;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.armavator.stopArm();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.armavator.stopArm();
    }
}
