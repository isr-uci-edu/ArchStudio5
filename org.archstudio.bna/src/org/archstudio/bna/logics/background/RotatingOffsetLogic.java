package org.archstudio.bna.logics.background;

import java.util.List;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThingLogicManager;
import org.archstudio.bna.facets.IHasMutableRotatingOffset;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.tracking.ThingTypeTrackingLogic;

import com.google.common.collect.Lists;

public final class RotatingOffsetLogic extends AbstractThingLogic {

	private static List<RotatingOffsetLogic> instances = Lists.newCopyOnWriteArrayList();
	private static Thread updater;
	static {
		updater = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(125);
					}
					catch (InterruptedException e) {
					}
					for (RotatingOffsetLogic instance : instances) {
						if (instance.DEBUG) {
							continue;
						}
						IBNAWorld world = instance.world;
						IBNAModel model = world.getBNAModel();
						IThingLogicManager logics = world.getThingLogicManager();
						model.beginBulkChange();
						try {
							ThingTypeTrackingLogic typesLogic = logics.addThingLogic(ThingTypeTrackingLogic.class);
							for (IHasMutableRotatingOffset t : typesLogic.getThings(IHasMutableRotatingOffset.class)) {
								if (t.shouldIncrementRotatingOffset()) {
									t.setRotatingOffset(t.getRotatingOffset() + 1);
								}
							}
						}
						finally {
							model.endBulkChange();
						}
					}
				}
			}

		});
		updater.setName(RotatingOffsetLogic.class.getName());
		updater.setDaemon(true);
		updater.start();
	}

	public boolean DEBUG = false;

	public RotatingOffsetLogic(IBNAWorld world) {
		super(world);
		instances.add(this);
	}

	@Override
	synchronized public void dispose() {
		instances.remove(this);
		super.dispose();
	}
}