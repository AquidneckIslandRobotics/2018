package org.usfirst.frc.team78.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class HowToTestAutoPath extends CommandGroup {

    public HowToTestAutoPath() {
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
    	addSequential(new FollowTrajectory("78 test auto"));
    	addSequential(new FollowTrajectory("78 test auto"));

    	//    	addSequential(new FollowTrajectory("SpenceAuto"));
//    	addSequential(new FollowTrajectory("turntest"));
//    	addSequential(new FollowTrajectory("centerRight"));
//    	addSequential(new FollowTrajectory("centerLeft"));
//    	addSequential(new FollowTrajectory("rightSwitchLeftPt1"));
    }
}
