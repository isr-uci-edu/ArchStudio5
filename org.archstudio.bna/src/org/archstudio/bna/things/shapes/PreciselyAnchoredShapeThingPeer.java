package org.archstudio.bna.things.shapes;

import java.awt.Dimension;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.facets.IHasEdgeColor;
import org.archstudio.bna.things.AbstractPreciseAnchorPointThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class PreciselyAnchoredShapeThingPeer<T extends PreciselyAnchoredShapeThing> extends
		AbstractPreciseAnchorPointThingPeer<T> {

	public PreciselyAnchoredShapeThingPeer(T thing) {
		super(thing);
	}

	public Rectangle getLocalBounds(IBNAView view, ICoordinateMapper cm) {
		Point lp = BNAUtils.toPoint(cm.worldToLocal(t.getPreciseAnchorPoint()));
		Dimension size = t.getSize();
		return new Rectangle(lp.x - size.width / 2, lp.y - size.height / 2, size.width, size.height);
	}

	public boolean isInThing(IBNAView view, ICoordinateMapper cm, ICoordinate location) {
		return getLocalBounds(view, cm).contains(location.getLocalPoint());
	}

	public void draw(IBNAView view, ICoordinateMapper cm, GL2 gl, Rectangle clip, IResources r) {
		Rectangle lbb = getLocalBounds(view, cm);

		switch (t.getShape()) {
		case CIRCLE: {
			float[] points = BNAUtils.getEllipsePoints(lbb);
			if (r.setColor(t, IHasColor.COLOR_KEY)) {
				gl.glBegin(GL.GL_TRIANGLE_FAN);
				for (int i = 0; i < points.length; i += 2) {
					gl.glVertex2f(points[i], points[i + 1]);
				}
				gl.glEnd();
			}
			lbb.width -= 1;
			lbb.height -= 1;
			points = BNAUtils.getEllipsePoints(lbb);
			if (r.setLineStyle(t) && r.setColor(t, IHasEdgeColor.EDGE_COLOR_KEY)) {
				gl.glBegin(GL.GL_LINE_LOOP);
				for (int i = 0; i < points.length; i += 2) {
					gl.glVertex2f(points[i] + 0.5f, points[i + 1] + 0.5f);
				}
				gl.glEnd();
			}
		}
			break;
		case SQUARE: {
			float[] points = new float[] { lbb.x, lbb.y,//
					lbb.x + lbb.width, lbb.y,//
					lbb.x + lbb.width, lbb.y + lbb.height,//
					lbb.x, lbb.y + lbb.height };
			if (r.setColor(t, IHasColor.COLOR_KEY)) {
				gl.glBegin(GL.GL_TRIANGLE_FAN);
				for (int i = 0; i < points.length; i += 2) {
					gl.glVertex2f(points[i], points[i + 1]);
				}
				gl.glEnd();
			}
			points = new float[] { lbb.x, lbb.y,//
					lbb.x + lbb.width - 1, lbb.y,//
					lbb.x + lbb.width - 1, lbb.y + lbb.height - 1,//
					lbb.x, lbb.y + lbb.height - 1 };
			if (r.setLineStyle(t) && r.setColor(t, IHasEdgeColor.EDGE_COLOR_KEY)) {
				gl.glBegin(GL.GL_LINE_LOOP);
				for (int i = 0; i < points.length; i += 2) {
					gl.glVertex2f(points[i] + 0.5f, points[i + 1] + 0.5f);
				}
				gl.glEnd();
			}
		}
			break;
		}
	}
}
