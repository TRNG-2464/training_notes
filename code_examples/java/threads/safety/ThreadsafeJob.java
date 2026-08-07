package com.revature.threads.safety;

import java.util.ArrayList;
import java.util.Vector;

public class ThreadsafeJob implements Runnable {
	
	public ArrayList<String> notsafe;
	public Vector<String> safe;
	public static int arrayListExceptionsCaught;
	public static int vectorExceptionsCaught;

	@Override
	public void run() {
		for (int i=0;i<1000;i++) {
			try {
				this.notsafe.add("a");
			} catch (Exception e) {
				arrayListExceptionsCaught++; // count all the exceptions thrown when trying to access an ArrayList
				System.out.println("Uh oh... exception thrown!!!");
			}

			try {
				this.safe.add("a"); // this should not cause us any problems, as Vector is synchronized
			} catch (Exception e) {
				vectorExceptionsCaught++; // count all the exceptions thrown when trying to access an ArrayList
				System.out.println("Uh oh... exception thrown!!!");
			}

		}
	}
	
	public ThreadsafeJob(ArrayList<String> al,Vector<String> v) {
		this.notsafe = al;
		this.safe = v;
	}
}
