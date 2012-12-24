package org.archstudio.bna.things;

import org.archstudio.bna.facets.IHasMutableReferencePoint;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.Point;

@NonNullByDefault
public abstract class AbstractRelativeMovableReferencePointThing extends AbstractRelativeMovableThing implements
		IHasMutableReferencePoint {

	public AbstractRelativeMovableReferencePointThing(@Nullable Object id) {
		super(id);
	}

	public void setReferencePoint(Point worldPoint) {
		Point referencePoint = getReferencePoint();
		moveRelative(new Point(worldPoint.x - referencePoint.x, worldPoint.y - referencePoint.y));
	}

}