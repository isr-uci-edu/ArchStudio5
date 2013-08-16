package org.archstudio.bna.things.shapes;

import java.awt.Dimension;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.things.AbstractPreciseAnchorPointThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class PreciselyAnchoredShapeThingPeer<T extends PreciselyAnchoredShapeThing> extends
		AbstractPreciseAnchorPointThingPeer<T> {

	public PreciselyAnchoredShapeThingPeer(T thing) {
		super(thing);
	}

	@Override
	public boolean isInThing(IBNAView view, ICoordinateMapper cm, ICoordinate location) {
		Path2D shape = t.getShape();
		Dimension size = t.getSize();
		shape.transform(AffineTransform.getScaleInstance(size.getWidth(), size.getHeight()));
		Point2D localPoint = cm.worldToLocal(t.getPreciseAnchorPoint());
		shape.transform(AffineTransform.getTranslateInstance(localPoint.getX(), localPoint.getY()));
		Point lPoint = location.getLocalPoint();
		return shape.getBounds().contains(lPoint.x, lPoint.y) && shape.contains(lPoint.x, lPoint.y);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, GL2 gl, Rectangle clip, IResources r) {
		Path2D shape = t.getShape();
		Dimension size = t.getSize();
		shape.transform(AffineTransform.getScaleInstance(size.getWidth(), size.getHeight()));
		Point2D localPoint = cm.worldToLocal(t.getPreciseAnchorPoint());
		shape.transform(AffineTransform.getTranslateInstance(localPoint.getX(), localPoint.getY()));

		BNAUtils.renderShapeFill(t, view, cm, gl, clip, r, shape);
		BNAUtils.renderShapeSelected(t, view, cm, gl, clip, r, shape);
	}
}
