package org.archstudio.bna.things.glass;

import java.awt.Shape;
import java.awt.geom.Line2D;
import java.util.List;

import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.Resources;
import org.archstudio.bna.things.AbstractSplineThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class SplineGlassThingPeer<T extends SplineGlassThing> extends AbstractSplineThingPeer<T> {

	public SplineGlassThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	@Override
	public void draw(GL2 gl, Rectangle localBounds, Resources r) {
		Rectangle lbb = BNAUtils.getLocalBoundingBox(cm, t);
		lbb.width += 1;
		lbb.height += 1;
		if (!lbb.intersects(localBounds) || !t.isSelected()) {
			return;
		}

		List<Point> localPoints = BNAUtils.worldToLocal(cm, t.getPoints());
		Point lp1 = localPoints.get(0);
		Point lp2 = localPoints.get(1);
		Shape localShape = new Line2D.Float(lp1.x, lp1.y, lp2.x, lp2.y);

		BNAUtils.renderShapeSelected(gl, localBounds, localShape, t.getRotatingOffset());
	}
}
