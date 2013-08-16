package org.archstudio.bna.logics.coordinating;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.archstudio.sysutils.SystemUtils.castOrNull;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasEndpoints;
import org.archstudio.bna.facets.IHasPoints;
import org.archstudio.bna.facets.IHasSecondaryAnchorPoint;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.things.labels.ArrowheadThing;
import org.eclipse.swt.graphics.Point;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class ArrowheadLogic extends AbstractThingLogic implements IBNAModelListener {

	private static class MaintainArrowhead {

		boolean isUpdating = false;

		final Object pointsThingID;
		final IThingKey<Point> endpointKey;
		final Object arrowheadThingID;

		public MaintainArrowhead(Object pointsThingID, IThingKey<Point> endpointKey, Object arrowheadThingID) {
			checkNotNull(pointsThingID);
			checkNotNull(endpointKey);
			checkNotNull(arrowheadThingID);

			this.pointsThingID = pointsThingID;
			this.endpointKey = endpointKey;
			this.arrowheadThingID = arrowheadThingID;
		}

		public void apply(IBNAModel model) {
			checkNotNull(model);

			if (isUpdating) {
				return;
			}

			IHasPoints pointsThing = castOrNull(model.getThing(pointsThingID), IHasPoints.class);
			IThing toThing = model.getThing(arrowheadThingID);
			if (pointsThing != null && toThing != null) {
				isUpdating = true;
				try {
					toThing.set(IHasAnchorPoint.ANCHOR_POINT_KEY, pointsThing.get(endpointKey));
					if (endpointKey.equals(IHasEndpoints.ENDPOINT_1_KEY)) {
						toThing.set(IHasSecondaryAnchorPoint.SECONDARY_ANCHOR_POINT_KEY, pointsThing.getPoint(1));
					}
					else if (endpointKey.equals(IHasEndpoints.ENDPOINT_2_KEY)) {
						toThing.set(IHasSecondaryAnchorPoint.SECONDARY_ANCHOR_POINT_KEY, pointsThing.getPoint(-2));
					}
				}
				finally {
					isUpdating = false;
				}
			}
		}
	}

	Multimap<Object, MaintainArrowhead> maintainArrowheads = ArrayListMultimap.create();

	public ArrowheadLogic() {
	}

	public void point(ArrowheadThing arrowheadThing, IHasEndpoints pointsThing, IThingKey<Point> endpoint) {
		MaintainArrowhead ma = new MaintainArrowhead(pointsThing.getID(), endpoint, arrowheadThing.getID());
		maintainArrowheads.put(pointsThing.getID(), ma);
		ma.apply(getBNAModel());
	}

	@Override
	public void bnaModelChanged(BNAModelEvent evt) {
		ThingEvent thingEvent = evt.getThingEvent();
		if (thingEvent != null) {
			for (MaintainArrowhead maintainArrowhead : maintainArrowheads.get(thingEvent.getTargetThing().getID())) {
				maintainArrowhead.apply(evt.getSource());
			}
		}
	}
}
