package com.revature.threads.problems.livelock;

// Represents one side of the livelock: refuses to send the ransom until
// the criminal releases the hostage first (see LivelockSimulator for details).
public class Police {
	private boolean ransomSent = false;

	public void giveRansom(Criminal criminal) {
		while (!criminal.isHostageReleased()) {
			System.out.println("Police: waiting for criminal to release hostage.");

			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}

		// Unreachable in practice, since the criminal is waiting on this line to run first.
		System.out.println("Police: sent ransom");
		this.ransomSent = true;
	}

	public boolean isRansomSent() {
		return this.ransomSent;
	}
}