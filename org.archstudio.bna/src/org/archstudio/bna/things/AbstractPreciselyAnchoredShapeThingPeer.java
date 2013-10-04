package org.archstudio.bna.things;

import java.awt.Dimension;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.eclipse.swt.graphics.Point;

public abstract class AbstractPreciselyAnchoredShapeThingPeer<T extends AbstractPreciselyAnchoredShapeThing> extends
		AbstractPreciseAnchorPointThingPeer<T> {

	public AbstractPreciselyAnchoredShapeThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	protected Shape createLocalShape() {
		Path2D shape = new Path2D.Double(t.getShape());
		Dimension size = t.getSize();
		shape.transform(AffineTransform.getScaleInstance(size.getWidth(), size.getHeight()));
		Point2D localPoint = cm.worldToLocal(t.getPreciseAnchorPoint());
		shape.transform(AffineTransform.getTranslateInstance(localPoint.getX(), localPoint.getY()));
		return shape;
	}

	@Override
	public boolean isInThing(ICoordinate location) {

		Point lPoint = location.getLocalPoint();
		Shape localShape = createLocalShape();

		return localShape.getBounds().contains(lPoint.x, lPoint.y) && localShape.contains(lPoint.x, lPoint.y);
	}
}
