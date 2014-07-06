package org.archstudio.bna.things.shapes;

import java.awt.Dimension;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.facets.peers.IHasShadowPeer;
import org.archstudio.bna.things.AbstractThingPeer;
import org.archstudio.bna.ui.IUIResources;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

@NonNullByDefault
public class ShapeThingPeer<T extends ShapeThing> extends AbstractThingPeer<T> implements IHasShadowPeer<T> {

	public ShapeThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	protected Shape createLocalShape() {
		Path2D shape = new Path2D.Double(t.getRawShape());
		Dimension size = t.getRawSize();
		shape.transform(AffineTransform.getScaleInstance(size.getWidth(), size.getHeight()));
		Point2D localPoint = t.getRawAnchorPoint();
		shape.transform(AffineTransform.getTranslateInstance(localPoint.getX(), localPoint.getY()));
		return BNAUtils.worldToLocal(cm, shape);
	}

	@Override
	public boolean draw(Rectangle localBounds, IUIResources r) {
		Rectangle lbb = cm.worldToLocal(t.getRawBoundingBox());
		if (!localBounds.intersects(lbb)) {
			return false;
		}

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
		Point worldPoint = location.getWorldPoint();

		Rectangle wbb = t.getBoundingBox();
		if (!wbb.contains(worldPoint)) {
			return false;
		}

		Point localPoint = location.getLocalPoint();
		Shape shape = createLocalShape();
		return shape.contains(localPoint.x, localPoint.y);
	}

}
