package org.archstudio.bna.things.labels;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;

import org.archstudio.bna.AbstractThing;
import org.archstudio.bna.BNAUtils;
import org.archstudio.bna.constants.ArrowheadShape;
import org.archstudio.bna.facets.*;

public class ArrowheadThing extends AbstractThing implements IHasMutableAnchorPoint, IHasMutableColor, IHasMutableSecondaryColor, IHasMutableArrowhead,
        IMutableMirrorsEndpoint {

	public ArrowheadThing() {
		this(BNAUtils.generateUID(ArrowheadThing.class.getName()));
	}

	public ArrowheadThing(String id) {
		super(id);
		initProperties();
	}

	protected void initProperties() {
		setArrowheadShape(ArrowheadShape.NONE);
		setArrowheadSize(5);
		setArrowheadFilled(false);
		setAnchorPoint(new Point(0, 0));
		setSecondaryPoint(new Point(0, 0));
		setColor(new RGB(0, 0, 0));
		setSecondaryColor(new RGB(0, 0, 0));
		setEndpointNumber(1);
	}

	public Point getAnchorPoint() {
		Point p = getProperty(ANCHOR_POINT_PROPERTY_NAME);
		return new Point(p.x, p.y);
	}

	public void setAnchorPoint(Point p) {
		setProperty(ANCHOR_POINT_PROPERTY_NAME, p);
	}

	public Point getSecondaryPoint() {
		Point p = getProperty("secondaryPoint");
		return new Point(p.x, p.y);
	}

	public void setSecondaryPoint(Point p2) {
		setProperty("secondaryPoint", p2);
	}

	public int getArrowheadSize() {
		return getProperty(ARROWHEAD_SIZE_PROPERTY_NAME);
	}

	public void setArrowheadSize(int arrowheadSize) {
		setProperty(ARROWHEAD_SIZE_PROPERTY_NAME, arrowheadSize);
	}

	public void setColor(RGB c) {
		setProperty(COLOR_PROPERTY_NAME, c);
	}

	public RGB getColor() {
		return getProperty(COLOR_PROPERTY_NAME);
	}

	public void setSecondaryColor(RGB c) {
		setProperty(SECONDARY_COLOR_PROPERTY_NAME, c);
	}

	public RGB getSecondaryColor() {
		return getProperty(SECONDARY_COLOR_PROPERTY_NAME);
	}

	public ArrowheadShape getArrowheadShape() {
		return getProperty(ARROWHEAD_SHAPE_PROPERTY_NAME);
	}

	public void setArrowheadShape(ArrowheadShape arrowheadShape) {
		setProperty(ARROWHEAD_SHAPE_PROPERTY_NAME, arrowheadShape);
	}

	public boolean isArrowheadFilled() {
		return getProperty(ARROWHEAD_FILLED_PROPERTY_NAME);
	}

	public void setArrowheadFilled(boolean arrowheadFilled) {
		setProperty(ARROWHEAD_FILLED_PROPERTY_NAME, arrowheadFilled);
	}

	public String getEndpointMasterThingID() {
		return getProperty(ENDPOINT_MASTER_THING_ID_PROPERTY_NAME);
	}

	public void setEndpointMasterThingID(String endpointMasterThingID) {
		setProperty(ENDPOINT_MASTER_THING_ID_PROPERTY_NAME, endpointMasterThingID);
	}

	public int getEndpointNumber() {
		return getProperty(ENDPOINT_NUMBER_PROPERTY_NAME);
	}

	public void setEndpointNumber(int endpointNumber) {
		setProperty(ENDPOINT_NUMBER_PROPERTY_NAME, endpointNumber);
	}

}
