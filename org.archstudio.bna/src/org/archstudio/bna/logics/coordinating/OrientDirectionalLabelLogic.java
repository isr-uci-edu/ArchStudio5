package org.archstudio.bna.logics.coordinating;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasOrientation;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.things.labels.DirectionalLabelThing;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.swtutils.constants.Orientation;
import org.archstudio.sysutils.FastIntMap;
import org.archstudio.sysutils.FastLongMap;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class OrientDirectionalLabelLogic extends AbstractThingLogic implements IBNAModelListener {

	private static Orientation getOnEdgeOrientation(int x1, int x2, int y1, int y2, int x, int y) {
		if (y == y1) {
			return getOnLineSegmentOrientation(x1, x, x2, Orientation.NORTHWEST, Orientation.NORTH,
					Orientation.NORTHEAST);
		}
		else if (y == y2) {
			return getOnLineSegmentOrientation(x1, x, x2, Orientation.SOUTHWEST, Orientation.SOUTH,
					Orientation.SOUTHEAST);
		}
		else if (x == x1) {
			return getOnLineSegmentOrientation(y1, y, y2, Orientation.NORTHWEST, Orientation.WEST,
					Orientation.SOUTHWEST);
		}
		else if (x == x2) {
			return getOnLineSegmentOrientation(y1, y, y2, Orientation.NORTHEAST, Orientation.EAST,
					Orientation.SOUTHEAST);
		}
		return Orientation.NONE;
	}

	private static Orientation getOnLineSegmentOrientation(int p1, int p, int p2, Orientation o1, Orientation o,
			Orientation o2) {
		if (p == p1) {
			return o1;
		}
		if (p == p2) {
			return o2;
		}
		if (p >= p1 && p <= p2) {
			return o;
		}
		return Orientation.NONE;
	}

	private static class Orient {

		boolean isUpdating = false;

		final Object boundingBoxThingID;
		final Object directionLabelThingID;

		public Orient(Object boundingBoxThingID, Object directionLabelThingID) {
			checkNotNull(boundingBoxThingID);
			checkNotNull(directionLabelThingID);

			this.boundingBoxThingID = boundingBoxThingID;
			this.directionLabelThingID = directionLabelThingID;
		}

		public void apply(IBNAModel model) {
			checkNotNull(model);

			if (isUpdating) {
				return;
			}

			IHasBoundingBox boundingBoxThing = SystemUtils.castOrNull(model.getThing(boundingBoxThingID),
					IHasBoundingBox.class);
			DirectionalLabelThing directionLabelThing = SystemUtils.castOrNull(model.getThing(directionLabelThingID),
					DirectionalLabelThing.class);
			if (boundingBoxThing != null && directionLabelThing != null) {
				isUpdating = true;
				try {
					Rectangle r = boundingBoxThing.getBoundingBox();
					Point p = directionLabelThing.getReferencePoint();
					if (r != null && p != null) {
						Orientation orientation = getOnEdgeOrientation(r.x, r.x + r.width, r.y, r.y + r.height, p.x,
								p.y);
						directionLabelThing.setOrientation(orientation);
					}
				}
				finally {
					isUpdating = false;
				}
			}
		}
	}

	private class Registrar {

		final int boundingBoxThingUID;
		final int directionalThingUID;
		final Orient orient;

		public Registrar(int boundingBoxThingUID, int directionalThingUID, Orient orient) {
			this.boundingBoxThingUID = boundingBoxThingUID;
			this.directionalThingUID = directionalThingUID;
			this.orient = orient;
		}

		public void register() {
			FastLongMap.getList(orients,
					BNAUtils.getThingKeyUID(boundingBoxThingUID, IHasBoundingBox.BOUNDING_BOX_KEY.getUID()), true).add(
					orient);
			FastLongMap.getList(orients,
					BNAUtils.getThingKeyUID(directionalThingUID, IHasAnchorPoint.ANCHOR_POINT_KEY.getUID()), true).add(
					orient);
			FastLongMap.getList(orients,
					BNAUtils.getThingKeyUID(directionalThingUID, IHasBoundingBox.BOUNDING_BOX_KEY.getUID()), true).add(
					orient);
			FastLongMap.getList(orients,
					BNAUtils.getThingKeyUID(directionalThingUID, IHasOrientation.ORIENTATION_KEY.getUID()), true).add(
					orient);
			registrars.put(directionalThingUID, this);
		}

		public void unregister() {
			FastLongMap.getList(orients,
					BNAUtils.getThingKeyUID(boundingBoxThingUID, IHasBoundingBox.BOUNDING_BOX_KEY.getUID()), false)
					.remove(orient);
			FastLongMap.getList(orients,
					BNAUtils.getThingKeyUID(directionalThingUID, IHasAnchorPoint.ANCHOR_POINT_KEY.getUID()), false)
					.remove(orient);
			FastLongMap.getList(orients,
					BNAUtils.getThingKeyUID(directionalThingUID, IHasBoundingBox.BOUNDING_BOX_KEY.getUID()), false)
					.remove(orient);
			FastLongMap.getList(orients,
					BNAUtils.getThingKeyUID(directionalThingUID, IHasOrientation.ORIENTATION_KEY.getUID()), false)
					.remove(orient);
			registrars.remove(directionalThingUID);
		}
	}

	public OrientDirectionalLabelLogic(IBNAWorld world) {
		super(world);
	}

	FastLongMap<List<Orient>> orients = new FastLongMap<>(512);
	FastIntMap<Registrar> registrars = new FastIntMap<Registrar>(128);

	synchronized public void orient(IHasBoundingBox boundingBoxThing, DirectionalLabelThing directionalThing) {
		checkNotNull(boundingBoxThing);
		checkNotNull(directionalThing);

		unorient(directionalThing);

		Orient orient = new Orient(boundingBoxThing.getID(), directionalThing.getID());
		Registrar registrar = new Registrar(boundingBoxThing.getUID(), directionalThing.getUID(), orient);
		registrar.register();

		orient.apply(model);
	}

	synchronized public void unorient(DirectionalLabelThing directionalThing) {
		checkNotNull(directionalThing);

		Registrar registrar = registrars.get(directionalThing.getUID());
		if (registrar != null) {
			registrar.unregister();
		}
	}

	@Override
	synchronized public void bnaModelChanged(BNAModelEvent evt) {
		ThingEvent thingEvent = evt.getThingEvent();
		if (thingEvent != null) {
			for (Orient orient : FastLongMap.getList(orients, thingEvent.getThingKeyUID(), false)) {
				orient.apply(evt.getSource());
			}
		}
	}
}
