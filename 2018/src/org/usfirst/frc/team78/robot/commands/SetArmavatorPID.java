package org.usfirst.frc.team78.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SetArmavatorPID extends CommandGroup {

    public SetArmavatorPID(double armTarget, double elevatorTarget, boolean toHoldOrNotToHold) {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	addParallel(new SetArmPID(armTarget, toHoldOrNotToHold));
    	addSequential(new SetElevatorPID(elevatorTarget, toHoldOrNotToHold));
    }
    
    public SetArmavatorPID(double armTarget, double elevatorTarget, boolean toHoldOrNotToHold, double maxSpeed) {
    	addParallel(new SetArmPID(armTarget, toHoldOrNotToHold, maxSpeed));
    	addSequential(new SetElevatorPID(elevatorTarget, toHoldOrNotToHold, maxSpeed));
    }
    
    public SetArmavatorPID(double armTarget, double elevatorTarget) {
    	addParallel(new SetArmPID(armTarget, false));
    	addSequential(new SetElevatorPID(elevatorTarget, false));
    }
    
    
}
