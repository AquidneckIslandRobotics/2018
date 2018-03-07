package org.usfirst.frc.team78.robot.commands.autos;

import org.usfirst.frc.team78.robot.RobotMap;
import org.usfirst.frc.team78.robot.commands.SetArmavatorPID;
import org.usfirst.frc.team78.robot.commands.SetSideSpeed;
import org.usfirst.frc.team78.robot.commands.Turn;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AUTO_leftScaleRight extends CommandGroup {

    public AUTO_leftScaleRight() {
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
    	addSequential(new SetSideSpeed( 1, -1),2.45); 
    	addSequential(new Turn(90), 2);
    	addSequential(new SetSideSpeed( 1, -1),2.3); 
    	addSequential(new Turn(-20), 2);
    	addSequential(new SetArmavatorPID(RobotMap.HIGH_SCALE_ARM_PRESET, RobotMap.HIGH_SCALE_ELEVATOR_PRESET, true), 3);
//    	addSequential(new IntakeCube(RobotMap.HOLD_CUBE), 3);
    	addSequential(new SetSideSpeed(0.25, -0.25), 2.3);
//    	addSequential(new OuttakeCubes(0.35), 2);
    }
}
