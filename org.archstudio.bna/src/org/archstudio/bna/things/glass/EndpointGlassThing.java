package org.archstudio.bna.things.glass;

import org.archstudio.bna.*;
import org.archstudio.bna.facets.*;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class EndpointGlassThing extends AbstractThing implements IHasMutableAnchorPoint, IHasBoundingBox, IHasMutableUserEditable, IDragMovable,
        IHasMutableBoundingBoxRail, IHasStickyBox, IHasMutableToolTip {

	public EndpointGlassThing() {
		this(BNAUtils.generateUID(EndpointGlassThing.class.getName()));
	}

	public EndpointGlassThing(String id) {
		super(id);
		initProperties();
	}

	protected void initProperties() {
		setAnchorPoint(new Point(0, 0));
		setUserEditable(true);
	}

	protected int getHeight() {
		return 10;
	}

	protected int getWidth() {
		return 10;
	}

	public Point getAnchorPoint() {
		Point p = getProperty(ANCHOR_POINT_PROPERTY_NAME);
		return new Point(p.x, p.y);
	}

	public void setAnchorPoint(Point p) {
		setProperty(ANCHOR_POINT_PROPERTY_NAME, p);
	}

	public Point getReferencePoint() {
		return getAnchorPoint();
	}

	public void setReferencePoint(Point p) {
		setAnchorPoint(p);
	}

	public Rectangle getBoundingBox() {
		Point p = getAnchorPoint();
		int w = getWidth();
		int h = getHeight();
		return new Rectangle(p.x - (w / 2), p.y - (w / 2), w, h);
	}

	public boolean isUserEditable() {
		return getProperty(USER_EDITABLE_PROPERTY_NAME);
	}

	public void setUserEditable(boolean userEditable) {
		setProperty(USER_EDITABLE_PROPERTY_NAME, userEditable);
	}

	public String getBoundingBoxRailMasterThingID() {
		return getProperty(BOUNDING_BOX_RAIL_MASTER_THING_ID_PROPERTY_NAME);
	}

	public void setBoundingBoxRailMasterThingID(String boundingBoxRailMasterThingID) {
		setProperty(BOUNDING_BOX_RAIL_MASTER_THING_ID_PROPERTY_NAME, boundingBoxRailMasterThingID);
	}

	public Rectangle getStickyBox() {
		Point p = getProperty(ANCHOR_POINT_PROPERTY_NAME);
		return new Rectangle(p.x, p.y, 0, 0);
	}

	public String getToolTip() {
		return getProperty(TOOL_TIP_PROPERTY_NAME);
	}

	public void setToolTip(String toolTip) {
		setProperty(TOOL_TIP_PROPERTY_NAME, toolTip);
	}

}
