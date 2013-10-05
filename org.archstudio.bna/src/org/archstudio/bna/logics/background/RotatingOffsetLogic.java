package org.archstudio.bna.logics.background;

import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.facets.IHasMutableRotatingOffset;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.eclipse.swt.widgets.Display;

import com.google.common.collect.Iterables;

public class RotatingOffsetLogic extends AbstractThingLogic {

	protected final RotatingOffsetIncrementer timer;

	public RotatingOffsetLogic(IBNAWorld world) {
		super(world);
		timer = new RotatingOffsetIncrementer();
		timer.setName(this.getClass().getName());
		timer.setDaemon(true);
		timer.start();
	}

	@Override
	synchronized public void dispose() {
		if (timer != null) {
			timer.terminate();
		}
		super.dispose();
	}

	public class RotatingOffsetIncrementer extends Thread {

		protected volatile boolean shouldTerminate = false;

		protected RotatingOffsetIncrementer() {
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
							for (IHasMutableRotatingOffset t : Iterables.filter(model.getAllThings(),
									IHasMutableRotatingOffset.class)) {
								if (t.shouldIncrementRotatingOffset()) {
									t.incrementRotatingOffset();
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