package org.archstudio.bna.things.shapes;

import java.awt.Dimension;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.things.AbstractThingPeer;
import org.archstudio.bna.ui.IUIResources;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

@NonNullByDefault
public class LocalShapeThingPeer<T extends LocalShapeThing> extends AbstractThingPeer<T> {

	public LocalShapeThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	protected Shape createLocalShape() {
		Path2D shape = new Path2D.Double(t.getRawShape());
		Dimension size = t.getRawSize();
		shape.transform(AffineTransform.getScaleInstance(size.getWidth(), size.getHeight()));
		Point2D localPoint = cm.worldToLocal(t.getRawAnchorPoint());
		shape.transform(AffineTransform.getTranslateInstance(localPoint.getX(), localPoint.getY()));
		return shape;
	}

	@Override
	public boolean draw(Rectangle localBounds, IUIResources r) {
		Shape localShape = createLocalShape();

		RGB glowColor = t.getRawGlowColor();
		if (glowColor != null) {
			r.glowShape(localShape, glowColor, t.getRawGlowWidth(), t.getRawGlowAlpha());
		}
		RGB color = t.getRawColor();
		if (color != null) {
			r.fillShape(localShape, color, t.isRawGradientFilled() ? t.getRawSecondaryColor() : null);
		}
		if (r.setLineStyle(t)) {
			r.drawShape(localShape);
			r.resetLineStyle();
		}
		if (t.isRawSelected()) {
			r.selectShape(localShape, t.getRawRotatingOffset());
		}

		return true;
	}

	@Override
	public boolean isInThing(ICoordinate location) {
		Point localPoint = location.getLocalPoint();
		return createLocalShape().contains(localPoint.x, localPoint.y);
	}

}
