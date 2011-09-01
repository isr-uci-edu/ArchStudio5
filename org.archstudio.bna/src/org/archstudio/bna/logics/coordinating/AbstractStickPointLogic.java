package org.archstudio.bna.logics.coordinating;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasEndpoints;
import org.archstudio.bna.facets.IHasMidpoints;
import org.archstudio.bna.facets.IHasPoints;
import org.archstudio.bna.facets.IIsSticky;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.things.glass.MappingGlassThing;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public abstract class AbstractStickPointLogic extends AbstractPropagateValueLogic<IIsSticky, IThing, IThingKey<Point>> {

	protected static final IThingKey<StickyMode> STICKY_MODE_KEY = ThingKey.create("stickyMode");

	public AbstractStickPointLogic() {
		super(IIsSticky.class, IThing.class);
	}

	public IThingKey<StickyMode> getStickyModeKey(IThingKey<Point> forPointKey) {
		return getSettingKey(forPointKey, STICKY_MODE_KEY);
	}

	public StickyMode getStickyMode(IThing pointThing, IThingKey<Point> forPointKey) {
		StickyMode stickyMode = getSetting(pointThing, forPointKey, STICKY_MODE_KEY);
		if (stickyMode == null) {
			stickyMode = StickyMode.CENTER;
		}
		return stickyMode;
	}

	public void setStickyMode(IThing pointThing, IThingKey<Point> forPointKey, StickyMode stickyMode) {
		setSetting(pointThing, forPointKey, STICKY_MODE_KEY, stickyMode);
	}

	public void unstick(IThing pointThing, IThingKey<Point> forPointKey) {
		unpropagate(pointThing, forPointKey);
	}

	@Override
	protected <EK extends IThingKey<EV>, EV> void fromThingChangedSync(BNAModelEvent<IIsSticky, EK, EV> evt) {
		if (evt.getTargetThing().isShapeModifyingKey(evt.getThingEvent().getPropertyName())) {
			super.fromThingChangedSync(evt);
		}
	}

	@Override
	protected void doSynchronizedPropagation(IBNAModel fromModel, IIsSticky fromThing,
			ThingEvent<IIsSticky, ?, ?> fromThingEvent, IThing toThing, ThingEvent<IThing, ?, ?> toThingEvent,
			IThingKey<Point> toKey) {

		StickyMode stickyMode = getSetting(toThing, toKey, STICKY_MODE_KEY);
		if (stickyMode == null) {
			return;
		}
		Point nearPoint = getNearPoint(toThing, toKey, stickyMode);
		if (nearPoint == null) {
			return;
		}

		// adjust the point proportionally if the 'stickyThing' has a rectangle and was just resized/moved
		if (fromThingEvent != null) {
			if (IHasBoundingBox.BOUNDING_BOX_KEY.equals(fromThingEvent.getPropertyName())) {
				nearPoint = BNAUtils.movePointWith((Rectangle) fromThingEvent.getOldPropertyValue(),
						(Rectangle) fromThingEvent.getNewPropertyValue(), nearPoint);
			}
		}

		// calculate the closest sticky point on the sticky thing, given the current point as reference
		Point stickyPoint = fromThing.getStickyPointNear(stickyMode, nearPoint);

		// update the actual stuck point and secondary point if necessary
		toThing.set(toKey, stickyPoint);
		updateSecondaryPoint(fromModel, fromThing, null, toThing, null, toKey);
	}

	protected Point getNearPoint(IThing pointThing, IThingKey<Point> pointKey, StickyMode stickyMode) {
		if (stickyMode.isDependsOnSecondaryPoint() && pointKey.equals(IHasEndpoints.ENDPOINT_1_KEY)) {
			return ((IHasPoints) pointThing).getPoint(1);
		}
		if (stickyMode.isDependsOnSecondaryPoint() && pointKey.equals(IHasEndpoints.ENDPOINT_2_KEY)) {
			return ((IHasPoints) pointThing).getPoint(-2);
		}
		if (stickyMode.isDependsOnSecondaryPoint() && pointKey.equals(MappingGlassThing.WORLD_POINT_KEY)) {
			Point p = ((IHasPoints) pointThing).getPoint(-2);
			// approximate inner world coordinates
			p.translate(((IHasPoints) pointThing).getPoint(-1).getNegated());
			p.translate(pointThing.get(MappingGlassThing.WORLD_POINT_KEY));
			return p;
		}
		return pointThing.get(pointKey);
	}

	boolean updatingSecondaryPoint = false;

	protected void updateSecondaryPoint(IBNAModel model, IIsSticky fromThing,
			ThingEvent<IIsSticky, ?, ?> fromThingEvent, IThing toThing, ThingEvent<IThing, ?, ?> toThingEvent,
			IThingKey<Point> toKey) {
		if (updatingSecondaryPoint) {
			return;
		}
		try {
			updatingSecondaryPoint = true;
			// check whether another endpoint depends on this point as a StickyMode#isDependsOnSecondaryPoint
			if (toThing instanceof IHasEndpoints) {
				IThingKey<Point> otherEndpointKey = null;
				if (IHasEndpoints.ENDPOINT_1_KEY.equals(toKey)) {
					otherEndpointKey = IHasEndpoints.ENDPOINT_2_KEY;
				}
				else if (IHasEndpoints.ENDPOINT_2_KEY.equals(toKey) || MappingGlassThing.WORLD_POINT_KEY.equals(toKey)) {
					otherEndpointKey = IHasEndpoints.ENDPOINT_1_KEY;
				}
				// if midpoints are present, then they are the potential secondary points
				if (toThing instanceof IHasMidpoints && !((IHasMidpoints) toThing).getMidpoints().isEmpty()) {
					otherEndpointKey = null;
				}
				if (otherEndpointKey != null) {
					StickyMode otherEndpointStickyMode = getStickyMode(toThing, otherEndpointKey);
					// only update if the the other point depends on a secondary point 
					if (otherEndpointStickyMode != null && otherEndpointStickyMode.isDependsOnSecondaryPoint()) {
						IThing otherFromThing = model.getThing(getThingRef(toThing, otherEndpointKey));
						if (fromThingClass.isInstance(otherFromThing)) {
							doSynchronizedPropagation(model, (IIsSticky) otherFromThing, null, toThing, null,
									otherEndpointKey);
						}
					}
				}
			}
		}
		finally {
			updatingSecondaryPoint = false;
		}
	}

}