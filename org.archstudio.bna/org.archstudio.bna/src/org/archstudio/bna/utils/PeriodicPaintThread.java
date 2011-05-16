package org.archstudio.bna.utils;

import org.archstudio.swtutils.SWTWidgetUtils;
import org.eclipse.swt.widgets.Composite;

class PeriodicPaintThread extends Thread {

	protected Composite c;
	// 30fps default
	protected int period = 33;
	protected boolean needsToPaint = false;
	protected boolean terminate = false;

	public PeriodicPaintThread(Composite c) {
		this.c = c;
		// this.setPriority(Thread.MAX_PRIORITY);
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

	@Override
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
			try {
				if (!c.isDisposed()) {
					c.redraw();
				}
			}
			catch (Throwable t) {
				t.printStackTrace();
			}
		}
	};

	public void redrawNow() {
		SWTWidgetUtils.async(c, painter);
	}
}
