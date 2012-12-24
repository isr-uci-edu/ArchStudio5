package org.archstudio.sysutils;

/**
 * This is a utility base Thread class for executing an activity once after a
 * given delay where additional requests to execute the activity during the
 * delay period reset the delay. This is useful, for example, in dealing with
 * cases where each of a burst of events would cause a computationally-intensive
 * operation that is only dependent upon the last event. So, let's say we get
 * 1000 events inside two seconds. Each of them should trigger the
 * recomputation, but we don't want to actually recompute 1000 times when once
 * at the end would be enough. This allows us to ignore all but the last
 * request, and, after a short delay, perform recomputation once.
 */
public class DelayedExecuteOnceThread extends Thread {

	protected boolean needsExecution = false;
	protected boolean shouldTerminate = false;
	protected boolean waitingForExecution = false;

	protected int delay;
	protected Runnable r;

	public DelayedExecuteOnceThread(int delay, Runnable r) {
		this(delay, r, false, false);
	}

	public DelayedExecuteOnceThread(int delay, Runnable r, boolean daemon, boolean start) {
		this.delay = delay;
		this.r = r;
		setDaemon(daemon);
		if (start) {
			start();
		}
	}

	public synchronized void execute() {
		needsExecution = true;
		this.notifyAll();
	}

	public synchronized void executeAndWait() {
		waitingForExecution = true;
		execute();
		while (waitingForExecution) {
			try {
				this.wait();
			}
			catch (InterruptedException ie2) {
			}
		}
	}

	public synchronized void terminate() {
		shouldTerminate = true;
		this.notifyAll();
	}

	public void run() {
		while (true) {
			boolean doExecution = false;
			synchronized (this) {

				while (!needsExecution && !shouldTerminate) {
					try {
						this.wait();
					}
					catch (InterruptedException ie) {
					}
				}

				while (needsExecution && !shouldTerminate) {
					needsExecution = false;
					doExecution = true;
					if (!waitingForExecution) {
						try {
							this.wait(delay);
						}
						catch (InterruptedException ie2) {
						}
					}
				}
			}
			if (shouldTerminate) {
				return;
			}
			if (doExecution) {
				r.run();
			}
			synchronized (this) {
				if (waitingForExecution) {
					waitingForExecution = false;
					this.notifyAll();
				}
			}
		}
	}
}
