package org.usfirst.frc.team78.robot.commands;

import org.usfirst.frc.team78.robot.RobotMap;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;

public class XboxTriggerButton extends Button {
	
	XboxController m_joystick;
	int m_axis;
	
	public XboxTriggerButton(XboxController manipulatorStick, int axis) {
		m_joystick = manipulatorStick;
		m_axis = axis;
	}
	
	public boolean get() {
		if(Math.abs(m_joystick.getRawAxis(m_axis)) < RobotMap.STICK_DEADZONE) return false;
		else return true;
	}
	
}