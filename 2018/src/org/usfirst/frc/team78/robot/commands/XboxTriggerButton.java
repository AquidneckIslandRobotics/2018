package org.usfirst.frc.team78.robot.commands;

import org.usfirst.frc.team78.robot.RobotMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

public class XboxTriggerButton extends Button {
	
	Joystick m_joystick;
	int m_axis;
	
	public XboxTriggerButton(Joystick joystick, int axis) {
		m_joystick = joystick;
		m_axis = axis;
	}
	
	public boolean get() {
		if(Math.abs(m_joystick.getRawAxis(m_axis)) < RobotMap.STICK_DEADZONE) return false;
		else return true;
	}
	
}