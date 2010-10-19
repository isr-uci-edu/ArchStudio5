package org.archstudio.bna.logics.background;

import org.archstudio.bna.AbstractThingLogic;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.facets.IHasMutableRotatingOffset;
import org.archstudio.bna.logics.tracking.IThingSetListener;
import org.archstudio.bna.logics.tracking.ThingSetChangedEvent;
import org.archstudio.bna.logics.tracking.TypedThingSetTrackingLogic;

public class RotatingOffsetLogic extends AbstractThingLogic implements IThingSetListener {

	protected TypedThingSetTrackingLogic<IHasMutableRotatingOffset> tttl = null;
	protected RotatingOffsetTimer timer = null;

	public RotatingOffsetLogic(TypedThingSetTrackingLogic<IHasMutableRotatingOffset> tttl) {
		this.tttl = tttl;
	}

	public void init() {
		timer = new RotatingOffsetTimer();
		tttl.addThingSetListener(this);
	}

	public void destroy() {
		if (timer != null) {
			timer.terminate();
		}
		tttl.removeThingSetListener(this);
	}

	public void thingSetChanged(ThingSetChangedEvent evt) {
		synchronized (timer) {
			if (timer != null)
				timer.notifyAll();
		}
	}

	public class RotatingOffsetTimer extends Thread {
		protected boolean shouldTerminate = false;

		protected RotatingOffsetTimer() {
			setDaemon(true);
			start();
		}

		public synchronized void terminate() {
			shouldTerminate = true;
			this.notifyAll();
		}

		public void run() {
			while (true) {
				if (shouldTerminate)
					return;

				try {
					Thread.sleep(800);
				}
				catch (InterruptedException e) {
				}

				synchronized (this) {
					IHasMutableRotatingOffset[] things = tttl.getThings();
					if (things.length == 0) {
						try {
							wait();
						}
						catch (InterruptedException e) {
						}
					}
					IBNAModel m = getBNAModel();
					if (m != null) {
						m.beginBulkChange();
					}
					for (IHasMutableRotatingOffset t : things) {
						t.incrementRotatingOffset();
					}
					if (m != null) {
						m.endBulkChange();
					}
				}
			}
		}
	}

}
