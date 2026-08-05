/**
 * @author Wezley Singleton
 */

package com.revature.designpatterns.observer;

public class HexObserver extends Observer {
	
	public HexObserver(Subject subject) {
		this.subject = subject;
		this.subject.add(this);
	}

	@Override
	public void update() {
		System.out.println(subject.getState() + " as hex: " + Integer.toHexString(subject.getState()));
	}

}
