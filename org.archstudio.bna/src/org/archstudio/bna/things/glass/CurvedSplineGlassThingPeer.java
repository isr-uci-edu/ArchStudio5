package org.archstudio.bna.things.glass;

import java.awt.Shape;
import java.awt.geom.QuadCurve2D;

import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.things.AbstractCurvedSplineThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class CurvedSplineGlassThingPeer<T extends CurvedSplineGlassThing> extends AbstractCurvedSplineThingPeer<T> {

	public CurvedSplineGlassThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, GL2 gl, Rectangle clip, IResources r) {
		Rectangle lbb = BNAUtils.getLocalBoundingBox(cm, t);
		if (!lbb.intersects(clip)) {
			return;
		}

		Point p1 = cm.worldToLocal(t.getEndpoint1());
		Point p2 = cm.worldToLocal(t.getEndpoint2());
		Point ap = cm.worldToLocal(t.getAnchorPoint());
		Shape localShape = new QuadCurve2D.Double(p1.x + 0.5d, p1.y + 0.5d, ap.x + 0.5d, ap.y + 0.5d, p2.x + 0.5d,
				p2.y + 0.5d);

		BNAUtils.renderShapeSelected(t, view, cm, gl, clip, r, localShape);
	}

}
