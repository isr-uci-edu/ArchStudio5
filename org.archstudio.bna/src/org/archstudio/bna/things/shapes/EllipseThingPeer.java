package org.archstudio.bna.things.shapes;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.facets.IHasEdgeColor;
import org.archstudio.bna.things.AbstractEllipseThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Rectangle;

public class EllipseThingPeer<T extends EllipseThing> extends AbstractEllipseThingPeer<T> implements IThingPeer<T> {

	public EllipseThingPeer(T thing) {
		super(thing);
	}

	public void draw(IBNAView view, ICoordinateMapper cm, GL2 gl, Rectangle clip, IResources r) {

		Rectangle lbb = BNAUtils.getLocalBoundingBox(cm, t);

		if (r.setColor(t, IHasColor.COLOR_KEY) && r.setLineStyle(t)) {
			float[] points = BNAUtils.getEllipsePoints(lbb);
			gl.glBegin(GL.GL_TRIANGLE_FAN);
			for (int i = 0; i < points.length; i += 2) {
				gl.glVertex2f(points[i], points[i + 1]);
			}
			gl.glEnd();
		}

		if (r.setColor(t, IHasEdgeColor.EDGE_COLOR_KEY) && r.setLineStyle(t)) {
			lbb.width -= 1;
			lbb.height -= 1;
			float[] points = BNAUtils.getEllipsePoints(lbb);
			gl.glBegin(GL.GL_LINE_LOOP);
			for (int i = 0; i < points.length; i += 2) {
				gl.glVertex2f(points[i] + 0.5f, points[i + 1] + 0.5f);
			}
			gl.glEnd();
		}
	}
}
