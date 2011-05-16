package org.archstudio.bna.things;

import org.archstudio.bna.facets.IHasMutableAnchorPoint;
import org.archstudio.bna.facets.IHasMutableEllipse;
import org.eclipse.draw2d.geometry.Point;

public abstract class EllipseEssenceThing extends AbstractBoxThing implements IHasMutableEllipse,
		IHasMutableAnchorPoint {

	public EllipseEssenceThing(Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		setReferencePointFractionOffset(new float[] { 0.5f, 0.5f });
	}

	public Point getAnchorPoint() {
		return getReferencePoint();
	}

	public void setAnchorPoint(Point newAnchorPoint) {
		setReferencePoint(newAnchorPoint);
	}
}
