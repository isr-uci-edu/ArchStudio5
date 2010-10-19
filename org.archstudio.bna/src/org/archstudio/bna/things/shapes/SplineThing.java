package org.archstudio.bna.things.shapes;

import org.archstudio.bna.*;
import org.archstudio.bna.facets.*;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Point;

public class SplineThing extends AbstractThing implements IHasMutableEndpoints, IHasMutableMidpoints, IHasBoundingBox, IHasMutableColor, IHasMutableLineWidth,
        IHasMutableLineStyle, IMutableMirrorsEndpoints, IMutableMirrorsMidpoints {

	//private Rectangle boundingBox = new Rectangle(0,0,0,0);

	public SplineThing() {
		this(BNAUtils.generateUID(SplineThing.class.getName()));
	}

	public SplineThing(String id) {
		super(id);
		initProperties();
	}

	protected void initProperties() {
		setEndpoint1(new Point(0, 0));
		setEndpoint2(new Point(0, 0));
		setMidpoints(new Point[0]);
		setLineStyle(SWT.LINE_SOLID);
		setColor(new RGB(0, 0, 0));
		setLineWidth(1);
	}

	protected void updateBoundingBox() {
		synchronized (propertyLockObject) {
			Rectangle boundingBox = new Rectangle(0, 0, 0, 0);

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
			setProperty(BOUNDING_BOX_PROPERTY_NAME, boundingBox);
		}
	}

	public Point getEndpoint1() {
		return getProperty(ENDPOINT_1_PROPERTY_NAME);
	}

	public void setEndpoint1(Point endpoint1) {
		setProperty(ENDPOINT_1_PROPERTY_NAME, endpoint1);
		updateBoundingBox();
	}

	public Point getEndpoint2() {
		return getProperty(ENDPOINT_2_PROPERTY_NAME);
	}

	public void setEndpoint2(Point endpoint2) {
		setProperty(ENDPOINT_2_PROPERTY_NAME, endpoint2);
		updateBoundingBox();
	}

	public Point[] getMidpoints() {
		return getProperty(MIDPOINTS_PROPERTY_NAME);
	}

	public void setMidpoints(Point[] midpoints) {
		setProperty(MIDPOINTS_PROPERTY_NAME, midpoints);
		updateBoundingBox();
	}

	public Rectangle getBoundingBox() {
		synchronized (propertyLockObject) {
			return getProperty(BOUNDING_BOX_PROPERTY_NAME);
			//return boundingBox;
		}
	}

	public void setColor(RGB c) {
		setProperty(COLOR_PROPERTY_NAME, c);
	}

	public RGB getColor() {
		return getProperty(COLOR_PROPERTY_NAME);
	}

	public int getLineStyle() {
		return getProperty(LINE_STYLE_PROPERTY_NAME);
	}

	public void setLineStyle(int lineStyle) {
		setProperty(LINE_STYLE_PROPERTY_NAME, lineStyle);
	}

	public int getLineWidth() {
		return getProperty(LINE_WIDTH_PROPERTY_NAME);
	}

	public void setLineWidth(int lineWidth) {
		setProperty(LINE_WIDTH_PROPERTY_NAME, lineWidth);
	}

	public String getEndpointsMasterThingID() {
		return getProperty(ENDPOINTS_MASTER_THING_ID_PROPERTY_NAME);
	}

	public void setEndpointsMasterThingID(String splinePointsMasterThingID) {
		setProperty(ENDPOINTS_MASTER_THING_ID_PROPERTY_NAME, splinePointsMasterThingID);
	}

	public String getMidpointsMasterThingID() {
		return getProperty(MIDPOINTS_MASTER_THING_ID_PROPERTY_NAME);
	}

	public void setMidpointsMasterThingID(String midpointsMasterThingID) {
		setProperty(MIDPOINTS_MASTER_THING_ID_PROPERTY_NAME, midpointsMasterThingID);
	}
}
