package org.archstudio.bna.logics.background;

import java.util.List;

import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.facets.IHasMutableLife;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.tracking.ThingTypeTrackingLogic;
import org.archstudio.swtutils.SWTWidgetUtils;
import org.eclipse.swt.widgets.Display;

import com.google.common.collect.Lists;

public final class LifeSapperLogic extends AbstractThingLogic {

	private static List<IBNAWorld> worlds = Lists.newCopyOnWriteArrayList();
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
					SWTWidgetUtils.sync(Display.getDefault(), new Runnable() {
						@Override
						public void run() {
							for (IBNAWorld world : worlds) {
								boolean changedSomething = false;
								try {
									ThingTypeTrackingLogic typesLogic = world.getThingLogicManager().addThingLogic(
											ThingTypeTrackingLogic.class);
									for (IHasMutableLife t : typesLogic.getThings(IHasMutableLife.class)) {
										int life = t.getLife() - 1;
										if (life <= 0) {
											world.getBNAModel().removeThing(t);
										}
										else {
											if (!changedSomething) {
												world.getBNAModel().beginBulkChange();
												changedSomething = true;
											}
											t.setLife(life);
										}
									}
								}
								finally {
									if (changedSomething) {
										world.getBNAModel().endBulkChange();
									}
								}
							}
						}
					});
				}
			}
		});
		updater.setName(LifeSapperLogic.class.getName());
		updater.setDaemon(true);
		updater.start();
	}

	public LifeSapperLogic(IBNAWorld world) {
		super(world);
		worlds.add(world);
	}

	@Override
	synchronized public void dispose() {
		worlds.remove(world);
		super.dispose();
	}
}
