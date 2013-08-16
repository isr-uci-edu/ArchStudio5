package org.archstudio.bna.things;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;

import org.archstudio.bna.facets.IHasShape;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.Point;

public class AbstractShapeThing extends AbstractMutableAnchorPointThing implements IHasShape {

	public AbstractShapeThing(@Nullable Object id) {
		super(id);
	}

	@Override
	public Path2D getShape() {
		return (Path2D) get(IHasShape.SHAPE_KEY);
	}

	protected void setShape(Shape shape) {
		set(IHasShape.SHAPE_KEY, shape);
	}

	@Override
	public Shape getStickyShape() {
		Path2D shape = getShape();
		Point anchor = getAnchorPoint();
		shape.transform(AffineTransform.getTranslateInstance(anchor.x, anchor.y));
		return shape;
	}
}
