package com.revature.threads.problems.deadlock;

/*
 * DEADLOCK: A multi-threading issue that occurs when two or more threads
 * each hold a lock another thread needs and refuse to give it up, so
 * threads freeze and wait forever.
 *
 * Here, runImplementation1() locks lock1 then lock2, while
 * runImplementation2() locks lock2 then lock1 -- so if t1 grabs lock1
 * while t2 grabs lock2 at the same time, each thread ends up waiting on
 * a lock the other is holding, and neither ever finishes.
 */
public class DeadLockSimulator {
	public static void main(String[] args) throws InterruptedException {
		Dead d = new Dead();

		for (int i = 0; i < 10; i++) {
			System.out.println("For loop iteration: " + i);
			Runnable r1 = () -> {
				try {
					d.runImplementation1();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			};
			Thread t1 = new Thread(r1, "Run 1");

			Runnable r2 = () -> {
				try {
					d.runImplementation2();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			};
			Thread t2 = new Thread(r2, "Run 2");

			// Notice that this code will not complete...
			t1.start();
			t2.start();
			t1.join();
			t2.join();
		}
	}
}