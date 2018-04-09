package org.usfirst.frc.team78.robot.commands;

import org.usfirst.frc.team78.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SetElevatorPID extends Command {
	
	private double targetValue, outputSpeed;
	private boolean holdPosition = false;
	private double maxSpeed = 1;
	
	public SetElevatorPID(double target) {
		requires(Robot.elevator);
		targetValue = target;
	}
	
    public SetElevatorPID(double target, boolean hold) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.elevator);
    	targetValue = target;
    	holdPosition = hold;
    }
    
    public SetElevatorPID(double target, boolean hold, double speedMax) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.elevator);
    	targetValue = target;
    	holdPosition = hold;
    	maxSpeed = speedMax;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.elevator.elevatorPIDInit(maxSpeed, targetValue);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.elevator.elevatorPIDExecute();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(holdPosition) return false;
        else return Robot.elevator.elePID.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	new StopElevator();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
