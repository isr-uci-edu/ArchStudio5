package org.archstudio.bna.things.shapes;

import java.awt.geom.Path2D;
import java.util.List;

import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.things.AbstractSplineThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class SplineThingPeer<T extends SplineThing> extends AbstractSplineThingPeer<T> implements IThingPeer<T> {

	public SplineThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, GL2 gl, Rectangle clip, IResources r) {
		Rectangle lbb = BNAUtils.getLocalBoundingBox(cm, t);
		lbb.width += 1;
		lbb.height += 1;
		if (!clip.intersects(lbb)) {
			return;
		}

		Path2D localShape = new Path2D.Double();
		List<Point> localPoints = BNAUtils.worldToLocal(cm, t.getPoints());
		boolean first = true;
		for (Point p : localPoints) {
			if (first) {
				localShape.moveTo(p.x, p.y);
				first = false;
			}
			else {
				localShape.lineTo(p.x, p.y);
			}
		}

		BNAUtils.renderShapeEdge(t, view, cm, gl, clip, r, localShape);
		BNAUtils.renderShapeSelected(t, view, cm, gl, clip, r, localShape);
	}
}
