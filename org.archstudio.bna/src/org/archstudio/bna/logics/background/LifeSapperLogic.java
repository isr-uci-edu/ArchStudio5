package org.archstudio.bna.logics.background;

import org.archstudio.bna.AbstractThingLogic;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.facets.IHasMutableLife;
import org.archstudio.bna.logics.tracking.IThingSetListener;
import org.archstudio.bna.logics.tracking.ThingSetChangedEvent;
import org.archstudio.bna.logics.tracking.TypedThingSetTrackingLogic;

public class LifeSapperLogic extends AbstractThingLogic implements IThingSetListener {

	protected TypedThingSetTrackingLogic<IHasMutableLife> tttl = null;
	protected LifeSapper sapper = null;

	public LifeSapperLogic(TypedThingSetTrackingLogic<IHasMutableLife> tttl) {
		this.tttl = tttl;
	}

	public void init() {
		sapper = new LifeSapper();
		tttl.addThingSetListener(this);
	}

	public void destroy() {
		if (sapper != null) {
			sapper.terminate();
		}
		tttl.removeThingSetListener(this);
	}

	public void thingSetChanged(ThingSetChangedEvent evt) {
		synchronized (sapper) {
			if (sapper != null)
				sapper.notifyAll();
		}
	}

	public class LifeSapper extends Thread {
		protected boolean shouldTerminate = false;

		protected LifeSapper() {
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
					Thread.sleep(250);
				}
				catch (InterruptedException e) {
				}

				synchronized (this) {
					IHasMutableLife[] things = tttl.getThings();
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
					for (IHasMutableLife t : things) {
						int life = t.getLife();
						int newLife = life - 1;
						if (newLife < 0) {
							m.removeThing(t);
						}
						else {
							t.setLife(newLife);
						}
					}
					if (m != null) {
						m.endBulkChange();
					}
				}
			}
		}
	}

}
