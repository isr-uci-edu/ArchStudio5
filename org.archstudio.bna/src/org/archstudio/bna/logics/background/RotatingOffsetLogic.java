package org.archstudio.bna.logics.background;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.facets.IHasMutableRotatingOffset;
import org.archstudio.bna.logics.AbstractThingLogic;

import com.google.common.collect.Iterables;

public class RotatingOffsetLogic extends AbstractThingLogic {

	protected RotatingOffsetIncrementer timer = null;

	public RotatingOffsetLogic() {
	}

	@Override
	protected void init() {
		super.init();
		timer = new RotatingOffsetIncrementer();
		timer.setName("RotatingOffsetIncrementer");
		timer.setDaemon(true);
		timer.start();
	}

	@Override
	protected void destroy() {
		if (timer != null) {
			timer.terminate();
			timer = null;
		}
		super.destroy();
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
					Thread.sleep(500);
				}
				catch (InterruptedException e) {
				}

				final IBNAModel model = getBNAModel();
				if (model != null) {
					model.beginBulkChange();
					try {
						for (IHasMutableRotatingOffset t : Iterables.filter(model.getThings(),
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
			}
		}
	}
}