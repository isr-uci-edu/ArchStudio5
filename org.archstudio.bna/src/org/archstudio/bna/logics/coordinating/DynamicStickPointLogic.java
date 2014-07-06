package org.archstudio.bna.logics.coordinating;

import java.awt.geom.Point2D;

import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasStickyShape;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.IThingMetakey;
import org.archstudio.bna.keys.IThingRefKey;
import org.archstudio.bna.keys.ThingMetakey;
import org.archstudio.bna.keys.ThingRefMetakey;
import org.archstudio.bna.logics.AbstractKeyCoordinatingThingLogic;

public class DynamicStickPointLogic extends AbstractKeyCoordinatingThingLogic {

	private static final String STICKY_MODE_KEY_NAME = ".stickyMode";
	private static final String STICKY_THING_ID_KEY_NAME = ".&stickyThingID";

	protected final StickPointLogic stickLogic;

	public DynamicStickPointLogic(IBNAWorld world) {
		super(world, false);
		stickLogic = logics.addThingLogic(StickPointLogic.class);
	}

	synchronized public IThingKey<StickyMode> getStickyModeKey(IThingKey<Point2D> pointKey) {
		IThingKey<StickyMode> stickyModeKey = ThingMetakey.create(STICKY_MODE_KEY_NAME, pointKey);
		track(stickyModeKey);
		return stickyModeKey;
	}

	synchronized public IThingRefKey<IHasStickyShape> getStickyThingKey(IThingKey<Point2D> pointKey) {
		IThingRefKey<IHasStickyShape> stickyThingKey = ThingRefMetakey.create(STICKY_THING_ID_KEY_NAME, pointKey);
		track(stickyThingKey);
		return stickyThingKey;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected void update(IThing thing, IThingKey<?> key) {
		if (key instanceof IThingMetakey) {
			IThingKey<Point2D> pointKey = ((IThingMetakey) key).getKey();
			StickyMode stickyMode = thing.get(getStickyModeKey(pointKey));
			IHasStickyShape stickyThing = getStickyThingKey(pointKey).get(thing, model);
			if (stickyMode != null && stickyThing != null) {
				stickLogic.stick(thing, pointKey, stickyMode, stickyThing);
			}
			else {
				stickLogic.unstick(thing, pointKey);
			}
		}
	}
}
