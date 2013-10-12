package org.archstudio.bna.logics.coordinating;

import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasShapeKeys;
import org.archstudio.bna.facets.IIsSticky;
import org.archstudio.bna.keys.IThingMetakey;
import org.archstudio.bna.keys.IThingRefKey;
import org.archstudio.bna.keys.ThingMetakey;
import org.archstudio.bna.keys.ThingRefMetakey;
import org.archstudio.bna.logics.AbstractKeyCoordinatingThingLogic;
import org.eclipse.swt.graphics.Point;

public class DynamicStickPointLogic extends AbstractKeyCoordinatingThingLogic {

	private static final String STICKY_MODE_KEY_NAME = ".stickyMode";
	private static final String STICKY_THING_ID_KEY_NAME = ".&stickyThingID";

	protected final StickPointLogic stickLogic;

	public DynamicStickPointLogic(IBNAWorld world) {
		super(world, false);
		stickLogic = logics.addThingLogic(StickPointLogic.class);
	}

	synchronized public IThingKey<StickyMode> getStickyModeKey(IThingKey<Point> pointKey) {
		IThingKey<StickyMode> stickyModeKey = ThingMetakey.create(STICKY_MODE_KEY_NAME, pointKey);
		track(stickyModeKey);
		return stickyModeKey;
	}

	synchronized public IThingRefKey<IIsSticky> getStickyThingKey(IThingKey<Point> pointKey) {
		IThingRefKey<IIsSticky> stickyThingKey = ThingRefMetakey.create(STICKY_THING_ID_KEY_NAME, pointKey);
		track(stickyThingKey);
		return stickyThingKey;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected void update(IThing thing, IThingKey<?> key) {
		if (thing instanceof IHasShapeKeys && key instanceof IThingMetakey) {
			IHasShapeKeys pointThing = (IHasShapeKeys) thing;
			IThingKey<Point> pointKey = ((IThingMetakey) key).getKey();
			StickyMode stickyMode = pointThing.get(getStickyModeKey(pointKey));
			IIsSticky stickyThing = getStickyThingKey(pointKey).get(pointThing, model);
			if (stickyMode != null && stickyThing != null) {
				stickLogic.stick(pointThing, pointKey, stickyMode, stickyThing);
			}
			else {
				stickLogic.unstick(pointThing, pointKey);
			}
		}
	}
}
