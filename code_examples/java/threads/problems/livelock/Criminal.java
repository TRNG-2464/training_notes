package com.revature.threads.problems.livelock;

// Represents the other side of the livelock: refuses to release the
// hostage until the police send the ransom first (see LivelockSimulator for details).
public class Criminal {
	private boolean hostageReleased = false;

	public void releaseHostage(Police police) {
		while (!police.isRansomSent()) {
			System.out.println("Criminal: waiting for police to give ransom.");

			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}

		// Unreachable in practice, since the police are waiting on this line to run first.
		System.out.println("Criminal: Releasing Hostage");
		this.hostageReleased = true;
	}

	public boolean isHostageReleased() {
		return this.hostageReleased;
	}
}