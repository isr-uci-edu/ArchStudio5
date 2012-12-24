package org.archstudio.bna.things.shapes;

import java.util.List;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.facets.IHasEdgeColor;
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
		if (r.setColor(t, IHasEdgeColor.EDGE_COLOR_KEY) && r.setLineStyle(t)) {
			List<Point> localPoints = BNAUtils.worldToLocal(cm, t.getPoints());
			gl.glBegin(GL.GL_LINE_STRIP);
			for (Point p : localPoints) {
				gl.glVertex2f(p.x + 0.5f, p.y + 0.5f);
			}
			gl.glEnd();
		}
	}
}
