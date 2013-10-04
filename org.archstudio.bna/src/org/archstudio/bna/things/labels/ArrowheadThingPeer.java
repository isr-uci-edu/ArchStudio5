package org.archstudio.bna.things.labels;

import java.awt.Polygon;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.Resources;
import org.archstudio.bna.constants.ArrowheadShape;
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.facets.IHasEdgeColor;
import org.archstudio.bna.things.AbstractThingPeer;
import org.archstudio.bna.utils.ArrowheadUtils;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class ArrowheadThingPeer<T extends ArrowheadThing> extends AbstractThingPeer<T> {

	public ArrowheadThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	@Override
	public void draw(GL2 gl, Rectangle localBounds, Resources r) {
		Point lp1 = cm.worldToLocal(t.getAnchorPoint());
		if (!localBounds.contains(lp1)) {
			return;
		}

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
			gl.glVertex2d(xyPoints[0] + 0.5f, localBounds.height - xyPoints[1] + 0.5f);
			gl.glVertex2d(xyPoints[2] + 0.5f, localBounds.height - xyPoints[3] + 0.5f);
			gl.glVertex2d(xyPoints[4] + 0.5f, localBounds.height - xyPoints[5] + 0.5f);
			gl.glEnd();
		}
		if (r.setColor(t, IHasEdgeColor.EDGE_COLOR_KEY)) {
			r.setLineStyle(t);
			gl.glBegin(arrowheadShape == ArrowheadShape.WEDGE ? GL.GL_LINE_STRIP : GL.GL_LINE_LOOP);
			gl.glVertex2d(xyPoints[0] + 0.5f, localBounds.height - xyPoints[1] + 0.5f);
			gl.glVertex2d(xyPoints[2] + 0.5f, localBounds.height - xyPoints[3] + 0.5f);
			gl.glVertex2d(xyPoints[4] + 0.5f, localBounds.height - xyPoints[5] + 0.5f);
			gl.glEnd();
			r.resetLineStyle();
		}
	}

	@Override
	public boolean isInThing(ICoordinate location) {
		Rectangle lbb = BNAUtils.getLocalBoundingBox(cm, t);
		if (lbb.contains(location.getLocalPoint())) {
			ArrowheadShape arrowheadShape = t.getArrowheadShape();
			if (arrowheadShape == null || arrowheadShape == ArrowheadShape.NONE) {
				return false;
			}

			Point lp1 = cm.worldToLocal(t.getAnchorPoint());
			Point lp2 = cm.worldToLocal(t.getSecondaryAnchorPoint());
			int arrowheadSize = t.getArrowheadSize();
			int[] xyPoints = ArrowheadUtils.calculateTriangularArrowhead(lp2.x, lp2.y, lp1.x, lp1.y, arrowheadSize);
			if (xyPoints == null) {
				return false;
			}

			return new Polygon(new int[] { xyPoints[0], xyPoints[2], xyPoints[4] }, new int[] { xyPoints[1],
					xyPoints[3], xyPoints[5] }, 3).contains(location.getLocalPoint().x, location.getLocalPoint().y);
		}

		return false;
	}

}
