package org.archstudio.bna.logics.background;

import java.util.List;

import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.facets.IHasMutableRotatingOffset;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.tracking.ThingTypeTrackingLogic;
import org.archstudio.swtutils.SWTWidgetUtils;
import org.eclipse.swt.widgets.Display;

import com.google.common.collect.Lists;

public final class RotatingOffsetLogic extends AbstractThingLogic {

	protected static final boolean DEBUG = false;
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

					if (DEBUG) {
						continue;
					}
					SWTWidgetUtils.sync(Display.getDefault(), new Runnable() {
						@Override
						public void run() {
							for (IBNAWorld world : worlds) {
								boolean changedSomething = false;
								try {
									ThingTypeTrackingLogic typesLogic = world.getThingLogicManager().addThingLogic(
											ThingTypeTrackingLogic.class);
									for (IHasMutableRotatingOffset t : typesLogic
											.getThings(IHasMutableRotatingOffset.class)) {
										if (t.shouldIncrementRotatingOffset()) {
											if (!changedSomething) {
												world.getBNAModel().beginBulkChange();
												changedSomething = true;
											}
											t.incrementRotatingOffset();
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
		updater.setName(RotatingOffsetLogic.class.getName());
		updater.setDaemon(true);
		updater.start();
	}

	public RotatingOffsetLogic(IBNAWorld world) {
		super(world);
		worlds.add(world);
	}

	@Override
	synchronized public void dispose() {
		worlds.remove(world);
		super.dispose();
	}
}