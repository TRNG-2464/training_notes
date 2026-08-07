package com.revature.threads.basics;

public class ThreadChild extends Thread {
	
	@Override
	public void run() {
		System.out.println("I am a thread which extended the Thread class");
		
		for (int i = 0; i < 5; i++) {
			System.out.println(Thread.currentThread().getName() + " is running");
			
			try {
				// Put the Thread in waiting for 750 MS
				Thread.sleep(750);
			} catch (InterruptedException e) {
				e.printStackTrace();
				
				/*
				 *  If the thread is interrupted, we want this loop
				 *  to stop, so that we do not potentially cause a
				 *  stop in our system 
				 */
				break;
			}
		}
		
		System.out.println(Thread.currentThread().getName() + " has finished");
	}
}
