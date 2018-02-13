package org.usfirst.frc.team78.robot.commands;

import org.usfirst.frc.team78.robot.Robot;
import org.usfirst.frc.team78.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RaiseArmManual extends Command {
	
	double speed;
	boolean canRaise;
	
    public RaiseArmManual(double armSpeed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	speed = armSpeed;
    	canRaise = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(Robot.armavator.getArmPot() < RobotMap.ARM_POT_UPPER_LIMIT) canRaise = true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.armavator.getArmPot() < RobotMap.ARM_POT_UPPER_LIMIT) canRaise = true;
    	else canRaise = false;
    	if(canRaise) Robot.armavator.setArm(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !canRaise;
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
