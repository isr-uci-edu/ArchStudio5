package org.archstudio.bna.logics.background;

import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasMutableLife;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.tracking.ThingTypeTrackingLogic;
import org.eclipse.swt.widgets.Display;

public class LifeSapperLogic extends AbstractThingLogic {

	protected final ThingTypeTrackingLogic typeLogic;
	protected final LifeSapper sapper;

	public LifeSapperLogic(IBNAWorld world) {
		super(world);
		typeLogic = logics.addThingLogic(ThingTypeTrackingLogic.class);
		sapper = new LifeSapper();
		sapper.setName(this.getClass().toString());
		sapper.setDaemon(true);
		sapper.start();
	}

	@Override
	synchronized public void dispose() {
		if (sapper != null) {
			sapper.terminate();
		}
		super.dispose();
	}

	public class LifeSapper extends Thread {

		protected volatile boolean shouldTerminate = false;

		protected LifeSapper() {
		}

		public synchronized void terminate() {
			shouldTerminate = true;
		}

		@Override
		public void run() {
			while (!shouldTerminate) {
				try {
					Thread.sleep(125);
				}
				catch (InterruptedException e) {
				}

				Display.getDefault().syncExec(new Runnable() {
					@Override
					public void run() {
						model.beginBulkChange();
						try {
							for (final IThing t : model.getThingsByID(typeLogic.getThingIDs(IHasMutableLife.class))) {
								if (t instanceof IHasMutableLife) {
									final IHasMutableLife tl = (IHasMutableLife) t;
									int life;
									tl.setLife(life = tl.getLife() - 1);
									if (life < 0) {
										model.removeThing(tl);
									}
								}
							}
						}
						finally {
							model.endBulkChange();
						}
					}
				});
			}
		}
	}
}
