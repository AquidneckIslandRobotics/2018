package org.usfirst.frc.team78.robot;

import edu.wpi.first.wpilibj.PIDOutput;

public class TurnOutput implements PIDOutput{

	double speed;
	
	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		speed = output;
	}

	public double getSpeed() {
		return speed;
	}
	
}
