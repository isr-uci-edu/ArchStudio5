package org.archstudio.bna.things.labels;

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
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class ArrowheadThingPeer<T extends ArrowheadThing> extends AbstractThingPeer<T> implements IThingPeer<T> {

	public ArrowheadThingPeer(T thing) {
		super(thing);
	}

	public void draw(IBNAView view, ICoordinateMapper cm, GL2 gl, Rectangle clip, IResources r) {

		Point lp1 = cm.worldToLocal(t.getAnchorPoint());
		Point lp2 = cm.worldToLocal(t.getSecondaryAnchorPoint());

		ArrowheadShape arrowheadShape = t.getArrowheadShape();
		if (arrowheadShape == null || arrowheadShape == ArrowheadShape.NONE) {
			return;
		}

		int arrowheadSize = t.getArrowheadSize();
		int[] xyPoints = ArrowheadUtils.calculateTriangularArrowhead(lp2.x, lp2.y, lp1.x, lp1.y, arrowheadSize);
		if (xyPoints == null) {
			return;
		}
		if (r.setColor(t, IHasColor.COLOR_KEY)) {
			gl.glBegin(GL.GL_TRIANGLES);
			gl.glVertex2d(xyPoints[0], xyPoints[1]);
			gl.glVertex2d(xyPoints[2], xyPoints[3]);
			gl.glVertex2d(xyPoints[4], xyPoints[5]);
			gl.glEnd();
		}
		if (r.setColor(t, IHasEdgeColor.EDGE_COLOR_KEY)) {
			gl.glBegin(arrowheadShape == ArrowheadShape.WEDGE ? GL.GL_LINE_STRIP : GL.GL_LINE_LOOP);
			gl.glVertex2d(xyPoints[0] + 0.5f, xyPoints[1] + 0.5f);
			gl.glVertex2d(xyPoints[2] + 0.5f, xyPoints[3] + 0.5f);
			gl.glVertex2d(xyPoints[4] + 0.5f, xyPoints[5] + 0.5f);
			gl.glEnd();
		}
	}

	public boolean isInThing(IBNAView view, ICoordinateMapper cm, ICoordinate location) {
		return false;
	}

}
