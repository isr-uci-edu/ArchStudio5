package org.archstudio.bna;

import org.eclipse.swt.widgets.Composite;

class PeriodicPaintThread extends Thread {
	protected Composite c;
	//30fps default
	protected int period = 33;
	protected boolean needsToPaint = false;
	protected boolean terminate = false;

	public PeriodicPaintThread(Composite c) {
		this.c = c;
		//this.setPriority(Thread.MAX_PRIORITY);
	}

	public void setPeriodInMillis(int periodInMillis) {
		this.period = periodInMillis;
	}

	public void redraw() {
		synchronized (this) {
			needsToPaint = true;
			this.notifyAll();
		}
	}

	public void terminate() {
		synchronized (this) {
			this.terminate = true;
			this.notifyAll();
		}
	}

	public void run() {
		while (true) {
			synchronized (this) {
				if (terminate) {
					return;
				}
				if (!needsToPaint) {
					try {
						this.wait();
					}
					catch (InterruptedException e) {
					}
				}
			}
			if (terminate) {
				return;
			}
			try {
				sleep(period);
			}
			catch (InterruptedException e) {
			}
			redrawNow();
			needsToPaint = false;
		}
	}

	private Runnable painter = new Runnable() {
		public void run() {
			if (!c.isDisposed()) {
				c.redraw();
			}
		}
	};

	public void redrawNow() {
		if (!c.isDisposed())
			c.getDisplay().asyncExec(painter);
	}

}
