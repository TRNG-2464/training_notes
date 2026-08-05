/**
 * @author Wezley Singleton
 */

package com.revature.designpatterns.observer;

import java.util.ArrayList;
import java.util.List;

public class Subject {
	
	private List<Observer> observers = new ArrayList<Observer>();
	private int state;
	

	public int getState() {
		return state;
	}

	// When the state of my Subject changes, my observers should be made aware!
	public void setState(int state) {
		this.state = state;
		execute();
	}
	
	public void add(Observer o) {
		observers.add(o);
	}

	// This will execute the update on my Observers!
	private void execute() {
		for(Observer ob : observers) {
			ob.update();
		}
	}

}
