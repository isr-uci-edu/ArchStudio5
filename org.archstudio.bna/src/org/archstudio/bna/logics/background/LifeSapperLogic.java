package org.archstudio.bna.logics.background;

import java.util.List;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingLogicManager;
import org.archstudio.bna.facets.IHasLife;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.tracking.ThingValueTrackingLogic;

import com.google.common.collect.Lists;

public final class LifeSapperLogic extends AbstractThingLogic {

	private static List<LifeSapperLogic> instances = Lists.newCopyOnWriteArrayList();
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
					for (LifeSapperLogic instance : instances) {
						IBNAWorld world = instance.world;
						IBNAModel model = world.getBNAModel();
						IThingLogicManager logics = world.getThingLogicManager();
						model.beginBulkChange();
						try {
							ThingValueTrackingLogic valuesLogic = logics.addThingLogic(ThingValueTrackingLogic.class);
							for (IThing t : valuesLogic.getThings(IHasLife.LIFE_KEY)) {
								Integer life = t.get(IHasLife.LIFE_KEY);
								if (life == null || life.intValue() <= 0) {
									model.removeThingAndChildren(t);
									continue;
								}
								t.set(IHasLife.LIFE_KEY, life.intValue() - 125);
							}
						}
						finally {
							model.endBulkChange();
						}
					}
				}
			}
		});
		updater.setName(LifeSapperLogic.class.getName());
		updater.setDaemon(true);
		updater.start();
	}

	public LifeSapperLogic(IBNAWorld world) {
		super(world);
		instances.add(this);
	}

	@Override
	synchronized public void dispose() {
		instances.remove(this);
		super.dispose();
	}
}
