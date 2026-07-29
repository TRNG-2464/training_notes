package com.revature.oop.abstraction;

import com.revature.oop.abstraction.abstractclasses.AquaticJet;
import com.revature.oop.abstraction.abstractclasses.Vehicle;
import com.revature.oop.abstraction.interfaces.Aquatic;
import com.revature.oop.abstraction.interfaces.Flyable;

public class Simulator {
	public static void main(String[] args) {
		Vehicle jet = new AquaticJet();
		jet.steer();
		if (jet instanceof Flyable)
			((Flyable)jet).fly();
		
		if (jet instanceof Aquatic)
			((Aquatic)jet).floatOnWater();
	}
}
