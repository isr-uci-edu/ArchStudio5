package org.archstudio.bna.things.labels;

import org.archstudio.bna.constants.ArrowheadShape;
import org.archstudio.bna.facets.IHasMutableArrowhead;
import org.archstudio.bna.facets.IHasMutableColor;
import org.archstudio.bna.facets.IHasMutableSecondaryAnchorPoint;
import org.archstudio.bna.facets.IHasMutableSecondaryColor;
import org.archstudio.bna.things.AbstractAnchorPointThing;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;

public class ArrowheadThing extends AbstractAnchorPointThing implements IHasMutableColor, IHasMutableSecondaryColor,
		IHasMutableArrowhead, IHasMutableSecondaryAnchorPoint {

	public ArrowheadThing(Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		setArrowheadShape(ArrowheadShape.NONE);
		setArrowheadSize(5);
		setColor(new RGB(0, 0, 0));
		setSecondaryColor(new RGB(0, 0, 0));
		setSecondaryAnchorPoint(new Point(0, 0));
	}

	@Override
	public int getArrowheadSize() {
		return get(ARROWHEAD_SIZE_KEY);
	}

	@Override
	public void setArrowheadSize(int arrowheadSize) {
		set(ARROWHEAD_SIZE_KEY, arrowheadSize);
	}

	@Override
	public void setColor(RGB c) {
		set(COLOR_KEY, c);
	}

	@Override
	public RGB getColor() {
		return get(COLOR_KEY);
	}

	@Override
	public void setSecondaryColor(RGB c) {
		set(SECONDARY_COLOR_KEY, c);
	}

	@Override
	public RGB getSecondaryColor() {
		return get(SECONDARY_COLOR_KEY);
	}

	@Override
	public ArrowheadShape getArrowheadShape() {
		return get(ARROWHEAD_SHAPE_KEY);
	}

	@Override
	public void setArrowheadShape(ArrowheadShape arrowheadShape) {
		set(ARROWHEAD_SHAPE_KEY, arrowheadShape);
	}

	@Override
	public Point getSecondaryAnchorPoint() {
		return get(SECONDARY_ANCHOR_POINT_KEY);
	}

	@Override
	public void setSecondaryAnchorPoint(Point secondaryAnchorPoint) {
		set(SECONDARY_ANCHOR_POINT_KEY, secondaryAnchorPoint);
	}
}
