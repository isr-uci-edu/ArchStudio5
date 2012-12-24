package org.archstudio.bna.logics.background;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasMutableLife;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.tracking.ThingTypeTrackingLogic;
import org.eclipse.swt.widgets.Display;

public class LifeSapperLogic extends AbstractThingLogic {

	protected ThingTypeTrackingLogic typeLogic;
	protected LifeSapper sapper = null;

	public LifeSapperLogic() {
	}

	protected void init() {
		super.init();
		typeLogic = addThingLogic(ThingTypeTrackingLogic.class);
		sapper = new LifeSapper();
		sapper.setName("LifeSapper");
		sapper.setDaemon(true);
		sapper.start();
	}

	protected void destroy() {
		if (sapper != null) {
			sapper.terminate();
			sapper = null;
		}
		super.destroy();
	}

	public class LifeSapper extends Thread {

		protected volatile boolean shouldTerminate = false;

		protected LifeSapper() {
		}

		public synchronized void terminate() {
			shouldTerminate = true;
		}

		public void run() {
			while (!shouldTerminate) {
				try {
					Thread.sleep(125);
				}
				catch (InterruptedException e) {
				}

				Display.getDefault().syncExec(new Runnable() {
					public void run() {
						final IBNAModel model = getBNAModel();
						if (model != null) {
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
					}
				});
			}
		}
	}
}
