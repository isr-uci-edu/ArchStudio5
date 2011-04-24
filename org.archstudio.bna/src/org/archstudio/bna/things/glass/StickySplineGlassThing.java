package org.archstudio.bna.things.glass;

import org.eclipse.swt.graphics.Point;

import org.archstudio.bna.*;
import org.archstudio.bna.facets.*;

public class StickySplineGlassThing extends SplineGlassThing implements IHasMutableStickyEndpoints {

	public StickySplineGlassThing() {
		this(BNAUtils.generateUID(StickySplineGlassThing.class.getName()));
	}

	public StickySplineGlassThing(String id) {
		super(id);
	}

	public String getEndpoint1StuckToThingID() {
		return getProperty(ENDPOINT_1_STUCK_TO_THING_ID_PROPERTY_NAME);
	}

	public void setEndpoint1StuckToThingID(String endpoint1StuckToThingID) {
		setProperty(ENDPOINT_1_STUCK_TO_THING_ID_PROPERTY_NAME, endpoint1StuckToThingID);
	}

	public void clearEndpoint1StuckToThingID() {
		removeProperty(ENDPOINT_1_STUCK_TO_THING_ID_PROPERTY_NAME);
	}

	public String getEndpoint2StuckToThingID() {
		return getProperty(ENDPOINT_2_STUCK_TO_THING_ID_PROPERTY_NAME);
	}

	public void setEndpoint2StuckToThingID(String endpoint2StuckToThingID) {
		setProperty(ENDPOINT_2_STUCK_TO_THING_ID_PROPERTY_NAME, endpoint2StuckToThingID);
	}

	public void clearEndpoint2StuckToThingID() {
		removeProperty(ENDPOINT_2_STUCK_TO_THING_ID_PROPERTY_NAME);
	}

	public Point getReferencePoint() {
		if (getEndpoint1StuckToThingID() == null) {
			Point p = getEndpoint1();
			if (p != null) {
				return p;
			}
		}

		if (getEndpoint2StuckToThingID() == null) {
			Point p = getEndpoint2();
			if (p != null) {
				return p;
			}
		}

		Point[] midpoints = getMidpoints();
		if ((midpoints != null) && (midpoints.length > 0)) {
			return midpoints[0];
		}
		return new Point(0, 0);
	}

	public void setReferencePoint(Point p) {
		Point rp = getReferencePoint();
		int dx = p.x - rp.x;
		int dy = p.y - rp.y;

		if (getEndpoint1StuckToThingID() == null) {
			Point ep1 = getEndpoint1();
			if (ep1 != null) {
				setEndpoint1(new Point(ep1.x + dx, ep1.y + dy));
			}
		}

		if (getEndpoint2StuckToThingID() == null) {
			Point ep2 = getEndpoint2();
			if (ep2 != null) {
				setEndpoint2(new Point(ep2.x + dx, ep2.y + dy));
			}
		}

		Point[] midpoints = getMidpoints();
		if ((midpoints != null) && (midpoints.length > 0)) {
			Point[] newMidpoints = new Point[midpoints.length];
			for (int i = 0; i < midpoints.length; i++) {
				newMidpoints[i] = new Point(midpoints[i].x + dx, midpoints[i].y + dy);
			}
			setMidpoints(newMidpoints);
		}
	}

}
