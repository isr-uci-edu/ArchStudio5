package org.archstudio.bna.things;

import org.archstudio.bna.facets.IRelativeMovable;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;

public abstract class AbstractRelativeMovableThing extends AbstractThing implements IRelativeMovable {

	public AbstractRelativeMovableThing(Object id) {
		super(id);
	}

	@Override
	public abstract Point getReferencePoint();

	@Override
	public abstract void moveRelative(Point worldDelta);

	@Override
	public void moveRelative(Dimension worldDelta) {
		moveRelative(new Point(worldDelta.width, worldDelta.height));
	}

	@Override
	public void setReferencePoint(final Point referencePoint) {
		synchronizedUpdate(new Runnable() {
			@Override
			public void run() {
				moveRelative(referencePoint.getDifference(getReferencePoint()));
			}
		});
	}
}