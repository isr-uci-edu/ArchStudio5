package org.archstudio.bna.things.labels;

import java.util.Arrays;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.constants.ArrowheadShape;
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.facets.IHasEdgeColor;
import org.archstudio.bna.things.AbstractThingPeer;
import org.archstudio.bna.utils.ArrowheadUtils;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class ArrowheadThingPeer<T extends ArrowheadThing> extends AbstractThingPeer<T> implements IThingPeer<T> {

	public ArrowheadThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, GL2 gl, Rectangle clip, IResources r) {

		Point lp1 = cm.worldToLocal(t.getAnchorPoint());
		Point lp2 = cm.worldToLocal(t.getReferencePoint());

		ArrowheadShape arrowheadShape = t.getArrowheadShape();
		if (arrowheadShape == null || arrowheadShape == ArrowheadShape.NONE) {
			return;
		}

		int arrowheadSize = t.getArrowheadSize();
		int[] points = ArrowheadUtils.calculateTriangularArrowhead(lp2.x, lp2.y, lp1.x, lp1.y, arrowheadSize);
		if (points == null) {
			return;
		}
		Point[] pointArray = BNAUtils.toPointArray(points);
		BNAUtils.worldToLocal(cm, Arrays.asList(pointArray));
		points = BNAUtils.toXYArray(Arrays.asList(pointArray));

		if (r.setColor(t, IHasColor.COLOR_KEY)) {
			gl.glBegin(GL.GL_TRIANGLES);
			gl.glVertex2d(points[0], points[1]);
			gl.glVertex2d(points[2], points[3]);
			gl.glVertex2d(points[4], points[5]);
			gl.glEnd();
		}
		if (r.setColor(t, IHasEdgeColor.EDGE_COLOR_KEY)) {
			gl.glBegin(arrowheadShape == ArrowheadShape.WEDGE ? GL.GL_LINE_STRIP : GL.GL_LINE_LOOP);
			gl.glVertex2d(points[0] + 0.5f, points[1] + 0.5f);
			gl.glVertex2d(points[2] + 0.5f, points[3] + 0.5f);
			gl.glVertex2d(points[4] + 0.5f, points[5] + 0.5f);
			gl.glEnd();
		}
	}

	@Override
	public boolean isInThing(IBNAView view, ICoordinateMapper cm, ICoordinate location) {
		return false;
	}

	@Override
	public Rectangle getLocalBounds(IBNAView view, ICoordinateMapper cm) {
		Point a = t.getAnchorPoint();
		int arrowheadSize = t.getArrowheadSize();
		return new Rectangle(a.x - arrowheadSize, a.y - arrowheadSize, arrowheadSize * 2, arrowheadSize * 2);
	}
}
