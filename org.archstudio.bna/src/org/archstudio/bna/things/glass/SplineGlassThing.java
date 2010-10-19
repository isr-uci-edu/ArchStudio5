package org.archstudio.bna.things.glass;

import org.archstudio.bna.*;
import org.archstudio.bna.facets.*;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Point;

public class SplineGlassThing extends AbstractThing implements IHasMutableEndpoints, IHasMutableMidpoints, IMarqueeSelectable, IHasMutableSelected,
        IHasBoundingBox, IHasMutableRotatingOffset, ISplineReshapable, IHasMutableToolTip, IDragMovable {

	private Rectangle boundingBox = new Rectangle(0, 0, 0, 0);

	public SplineGlassThing() {
		this(BNAUtils.generateUID(SplineGlassThing.class.getName()));
	}

	public SplineGlassThing(String id) {
		super(id);
		initProperties();
	}

	protected void initProperties() {
		setEndpoint1(new Point(0, 0));
		setEndpoint2(new Point(0, 0));
		setMidpoints(new Point[0]);
		setProperty(ROTATING_OFFSET_PROPERTY_NAME, 0);
		setSelected(false);
		setUserEditable(true);
	}

	protected void updateBoundingBox() {
		synchronized (propertyLockObject) {
			int x1 = Integer.MAX_VALUE;
			int x2 = Integer.MIN_VALUE;
			int y1 = Integer.MAX_VALUE;
			int y2 = Integer.MIN_VALUE;

			Point p = getEndpoint1();
			if (p != null) {
				if (p.x < x1)
					x1 = p.x;
				if (p.x > x2)
					x2 = p.x;
				if (p.y < y1)
					y1 = p.y;
				if (p.y > y2)
					y2 = p.y;
			}

			p = getEndpoint2();
			if (p != null) {
				if (p.x < x1)
					x1 = p.x;
				if (p.x > x2)
					x2 = p.x;
				if (p.y < y1)
					y1 = p.y;
				if (p.y > y2)
					y2 = p.y;
			}

			Point[] midpoints = getMidpoints();
			if (midpoints != null) {
				for (Point m : midpoints) {
					if (m.x < x1)
						x1 = m.x;
					if (m.x > x2)
						x2 = m.x;
					if (m.y < y1)
						y1 = m.y;
					if (m.y > y2)
						y2 = m.y;
				}
			}
			boundingBox.x = x1;
			boundingBox.y = y1;
			boundingBox.width = x2 - x1;
			boundingBox.height = y2 - y1;
		}
	}

	public Point getEndpoint1() {
		Point p = getProperty(ENDPOINT_1_PROPERTY_NAME);
		if (p == null)
			return null;
		return new Point(p.x, p.y);
	}

	public void setEndpoint1(Point endpoint1) {
		setProperty(ENDPOINT_1_PROPERTY_NAME, endpoint1);
		updateBoundingBox();
	}

	public Point getEndpoint2() {
		Point p = getProperty(ENDPOINT_2_PROPERTY_NAME);
		if (p == null)
			return null;
		return new Point(p.x, p.y);
	}

	public void setEndpoint2(Point endpoint2) {
		setProperty(ENDPOINT_2_PROPERTY_NAME, endpoint2);
		updateBoundingBox();
	}

	public Point[] getMidpoints() {
		synchronized (propertyLockObject) {
			Point[] midpoints = getProperty(MIDPOINTS_PROPERTY_NAME);
			if (midpoints == null)
				return null;
			Point[] ps = new Point[midpoints.length];
			for (int i = 0; i < midpoints.length; i++) {
				ps[i] = new Point(midpoints[i].x, midpoints[i].y);
			}
			return ps;
		}
	}

	public void setMidpoints(Point[] midpoints) {
		synchronized (propertyLockObject) {
			setProperty(MIDPOINTS_PROPERTY_NAME, midpoints);
		}
		updateBoundingBox();
	}

	public Rectangle getBoundingBox() {
		synchronized (propertyLockObject) {
			return boundingBox;
		}
	}

	public void moveRelative(int dx, int dy) {
		synchronized (propertyLockObject) {
			Point endpoint1 = getEndpoint1();
			Point endpoint2 = getEndpoint2();
			Point[] midpoints = getMidpoints();

			endpoint1.x += dx;
			endpoint1.y += dy;
			setEndpoint1(endpoint1);

			endpoint2.x += dx;
			endpoint2.y += dy;
			setEndpoint2(endpoint2);

			if (midpoints != null) {
				for (int i = 0; i < midpoints.length; i++) {
					midpoints[i].x += dx;
					midpoints[i].y += dy;
				}
			}
			setMidpoints(midpoints);
		}
	}

	public boolean isUserEditable() {
		return getProperty(USER_EDITABLE_PROPERTY_NAME);
	}

	public void setUserEditable(boolean userEditable) {
		setProperty(USER_EDITABLE_PROPERTY_NAME, userEditable);
	}

	public boolean isSelected() {
		return getProperty(SELECTED_PROPERTY_NAME);
	}

	public void setSelected(boolean selected) {
		setProperty(SELECTED_PROPERTY_NAME, selected);
	}

	public int getRotatingOffset() {
		return getProperty(ROTATING_OFFSET_PROPERTY_NAME);
	}

	public void incrementRotatingOffset() {
		if (isSelected()) {
			setProperty(ROTATING_OFFSET_PROPERTY_NAME, (getRotatingOffset() + 1) % 6);
		}
	}

	public String getToolTip() {
		return getProperty(TOOL_TIP_PROPERTY_NAME);
	}

	public void setToolTip(String toolTip) {
		setProperty(TOOL_TIP_PROPERTY_NAME, toolTip);
	}

	public Point getReferencePoint() {
		Point p = getEndpoint1();
		if (p != null) {
			return p;
		}
		p = getEndpoint2();
		if (p != null) {
			return p;
		}
		Point[] midpoints = getMidpoints();
		if (midpoints.length > 0) {
			return midpoints[0];
		}
		return null;
	}

	public void setReferencePoint(Point p) {
		Point rp = getReferencePoint();
		int dx = p.x - rp.x;
		int dy = p.y - rp.y;

		Point ep1 = getEndpoint1();
		if (ep1 != null) {
			setEndpoint1(new Point(ep1.x + dx, ep1.y + dy));
		}

		Point ep2 = getEndpoint2();
		if (ep2 != null) {
			setEndpoint2(new Point(ep2.x + dx, ep2.y + dy));
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
