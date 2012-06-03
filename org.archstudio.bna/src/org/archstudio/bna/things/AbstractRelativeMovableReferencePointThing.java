package org.archstudio.bna.things;

import org.archstudio.bna.facets.IHasMutableReferencePoint;
import org.eclipse.draw2d.geometry.Point;

public abstract class AbstractRelativeMovableReferencePointThing extends AbstractRelativeMovableThing implements
		IHasMutableReferencePoint {

	public AbstractRelativeMovableReferencePointThing(Object id) {
		super(id);
	}

	@Override
	public void setReferencePoint(final Point worldPoint) {
		Point referencePoint = getReferencePoint();
		moveRelative(new Point(worldPoint.x - referencePoint.x, worldPoint.y - referencePoint.y));
	}

}