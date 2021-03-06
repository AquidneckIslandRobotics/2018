package org.usfirst.frc.team78.robot.commands.autos;

import org.usfirst.frc.team78.robot.RobotMap;
import org.usfirst.frc.team78.robot.commands.IntakeCube;
import org.usfirst.frc.team78.robot.commands.OuttakeCube;
import org.usfirst.frc.team78.robot.commands.SetArmavatorPID;
import org.usfirst.frc.team78.robot.commands.SetSideSpeed;
import org.usfirst.frc.team78.robot.commands.Turn;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AUTO_rightScaleLeft extends CommandGroup {

    public AUTO_rightScaleLeft() {
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
    	addSequential(new SetSideSpeed( 0.85, -0.85),2.62); 
    	addSequential(new Turn(-90));
    	addSequential(new IntakeCube(RobotMap.HOLD_CUBE), 1);
    	addParallel(new SetArmavatorPID(RobotMap.HIGH_SCALE_ARM_PRESET, RobotMap.HIGH_SCALE_ELEVATOR_PRESET, true), 3);
    	addSequential(new SetSideSpeed( 0.75, -0.75),3.0); 
    	addSequential(new Turn(23), 2);
    	addSequential(new SetSideSpeed(0.50, -0.50), 1.3);
    	addSequential(new OuttakeCube(0.4), 2);
    }
}
