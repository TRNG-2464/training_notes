package com.revature.threads.safety;

import java.util.ArrayList;
import java.util.Vector;

public class ThreadsafeExample {
	/*
	 * A simple example which shows why using the synchronized Vector class is necessary for multi-threading
	 *  - each ThreadsafeJob adds 1000 strings to both the arraylist and vector objects
	 *  - we start two threads and then wait for them to finish executing
	 *  - then we report the number of exceptions thrown and null values inserted using arraylist
	 *  - thus we can see directly the advantage of Vector in multithreading applications
	 *  
	 *  the unexpected behavior with ArrayList may be sporadic or unpredictable as it depends on thread execution,
	 *  but without a synchronized class we cannot be sure that we won't have problems
	 */
	public static ArrayList<String> notsafe;
	public static Vector<String> safe;
	public static int numArrayNullValues;
	public static int numVectorNullValues;

	public static void main(String[] args) {
		runExample();

		// uncomment the code below for aggregate statistics:
		for (int i=0;i<10;i++) {
			runExample(); // run the example multiple times
		}
		// display resulting average statistics
		System.out.println("\t::: ::: ARRAYLIST STATS::: :::");
		float avgArrayExceptions = (float) ThreadsafeJob.arrayListExceptionsCaught / 10;
		float avgArrayNulls = (float) numArrayNullValues / 10;
		System.out.println("# Total ArrayList exceptions: " + ThreadsafeJob.arrayListExceptionsCaught);
		System.out.println("# Total ArrayList nulls: " + numArrayNullValues);
		System.out.println("# Avg ArrayList exceptions: " + avgArrayExceptions);
		System.out.println("# Avg ArrayList nulls: " + avgArrayNulls);

		System.out.println("\t::: ::: VECTOR STATS::: :::");
		float avgVectorExceptions = (float) ThreadsafeJob.vectorExceptionsCaught / 10;
		float avgVectorNulls = (float) numVectorNullValues / 10;
		System.out.println("# Total Vector exceptions: " + ThreadsafeJob.vectorExceptionsCaught);
		System.out.println("# Total Vector nulls: " + numVectorNullValues);
		System.out.println("# Avg Vector exceptions: " + avgVectorExceptions);
		System.out.println("# Avg Vector nulls: " + avgVectorNulls);

	}
	
	public static void runExample() {
		notsafe = new ArrayList<>();
		safe = new Vector<>();
		Thread t1 = new Thread(new ThreadsafeJob(notsafe, safe));
		Thread t2 = new Thread(new ThreadsafeJob(notsafe, safe));
		System.out.println("Starting threads...");
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
			System.out.println("Threads have finished executing");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("ArrayList: "+notsafe);
		System.out.println("Vector:    "+safe);
		for (String s : notsafe) {
			if (s == null) {
				numArrayNullValues++;
			}
		}

		for (String s : safe) {
			if (s == null) {
				numVectorNullValues++;
			}
		}
		System.out.println("# Exceptions thrown while adding to ArrayList: " + ThreadsafeJob.arrayListExceptionsCaught);
		System.out.println("# null values in ArrayList: " + numArrayNullValues);

		System.out.println("# Exceptions thrown while adding to Vector: " + ThreadsafeJob.vectorExceptionsCaught);
		System.out.println("# null values in Vector: " + numVectorNullValues);
	}
}
