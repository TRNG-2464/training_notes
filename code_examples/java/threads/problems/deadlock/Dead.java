package com.revature.threads.problems.deadlock;

// Two methods that acquire the same two locks in opposite order, creating
// a deadlock when run concurrently (see DeadLockSimulator for details).
public class Dead {
	private Object lock1 = new Object();
	private Object lock2 = new Object();

	// Acquires lock1, then lock2.
	public void runImplementation1() throws InterruptedException {
		synchronized (lock1) {
			Thread.sleep(10); // gives the other thread time to grab lock2, making the deadlock reliable
			synchronized (lock2) {
				System.out.println("Func 1: ");
				System.out.println(Thread.currentThread().getName());
			}
		}
		System.out.println("Lock 1 Released");
	}

	// Acquires lock2, then lock1 -- the reverse order of runImplementation1(),
	// which is what causes the deadlock.
	public void runImplementation2() throws InterruptedException {
		synchronized (lock2) {
			Thread.sleep(10); // gives the other thread time to grab lock1, making the deadlock reliable
			synchronized (lock1) {
				System.out.println("Func 2: ");
				System.out.println(Thread.currentThread().getName());
			}
		}
		System.out.println("Lock 2 Released");
	}
}