package org.archstudio.bna.things;

import org.archstudio.bna.facets.IHasMutableIndicatorPoint;
import org.eclipse.swt.graphics.Point;

public abstract class AbstractIndicatorPointThing extends AbstractMutableAnchorPointThing implements IHasMutableIndicatorPoint {

	public AbstractIndicatorPointThing(Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		setIndicatorPoint(new Point(0, 0));
		addShapeModifyingKey(INDICATOR_POINT_KEY);
	}

	@Override
	public Point getIndicatorPoint() {
		return get(INDICATOR_POINT_KEY);
	}

	@Override
	public void setIndicatorPoint(Point p) {
		set(INDICATOR_POINT_KEY, p);
	}

}
