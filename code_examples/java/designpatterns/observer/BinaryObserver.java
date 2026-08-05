/**
 * @author Wezley Singleton
 */

package com.revature.designpatterns.observer;

public class BinaryObserver extends Observer {
	
	public BinaryObserver(Subject subject) {
		this.subject = subject;
		this.subject.add(this);
	}

	@Override
	public void update() {
		System.out.println(subject.getState() + " as binary: " + Integer.toBinaryString(subject.getState()));
	}

}
