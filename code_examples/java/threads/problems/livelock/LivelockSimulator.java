package com.revature.threads.problems.livelock;

/*
 * LIVELOCK: An issue in which a program runs forever because threads
 * stay active but keep reacting to each other's state in a way that
 * never lets either one make progress.
 *
 * Here, the Police won't send the ransom until the hostage is released,
 * and the Criminal won't release the hostage until the ransom is sent --
 * so both threads loop and wait on each other forever.
 */
public class LivelockSimulator {
	static final Police police = new Police();
	static final Criminal criminal = new Criminal();

	// Notice that this code will never stop...
	public static void main(String[] args) {
		Thread t1 = new Thread(() -> {
			police.giveRansom(criminal);
		});
		t1.start();

		Thread t2 = new Thread(() -> {
			criminal.releaseHostage(police);
		});
		t2.start();
	}
}