package org.usfirst.frc.team78.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class MotionProfile extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public int unitSize = 1; //feet

	public double getDistanceFromPoints(double x1, double y1, double x2, double y2) {
		double dist = 0;
		double xRadius = 0;
		double yRadius = 0;
		
		if(x1 == x2) {
			dist = y2 - y1;
		}else if(y1 == y2) {
			dist = x2 - x1;
		}else {
			if(x2 > x1) {
				xRadius = x2 - x1;
				//xCenter += x1;
			}else if(x2 < x1) {
				xRadius = x2 - x1;
				//xCenter -= x1;
			}
			
			if(y2 > y1) {
				yRadius = y2 - y1;
				//yCenter += y1;
			}else if(y2 < y1) {
				yRadius = y2 - y1;
				//yCenter -= y1; 
			}
			
			double arcLength = (2 * Math.PI * Math.abs(xRadius) / 4);
			dist = arcLength;
		}
		
		return dist;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

