package com.revature.threads.basics;

/*
 * The following showcases how to set Thread Priority 
 */
public class ThreadPriority {
	public static void main(String[] args) {
		HighPriorityThread hiThread = new HighPriorityThread();
		hiThread.setPriority( Thread.MAX_PRIORITY );	// Priority 10
		hiThread.start(); // begin work...

		LowPriorityThread loThread = new LowPriorityThread();
		loThread.setPriority( Thread.MIN_PRIORITY );	// Priority 1
		loThread.start(); // begin work...
	}
}

class HighPriorityThread extends Thread {
	@Override
	public void run() {
		// Some functionality here...
	}
}

class LowPriorityThread extends Thread {
	@Override
	public void run() {
		// Some functionality here...
	}
}
