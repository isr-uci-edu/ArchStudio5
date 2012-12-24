package org.archstudio.bna.things.shapes;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.facets.IHasEdgeColor;
import org.archstudio.bna.things.AbstractPolygonThingPeer;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class PolygonThingPeer<T extends PolygonThing> extends AbstractPolygonThingPeer<T> implements IThingPeer<T> {

	public PolygonThingPeer(T thing) {
		super(thing);
	}

	public void draw(IBNAView view, ICoordinateMapper cm, GL2 gl, Rectangle clip, IResources r) {
		Point a = t.getAnchorPoint();
		if (r.setColor(t, IHasEdgeColor.EDGE_COLOR_KEY) && r.setLineStyle(t)) {
			gl.glBegin(GL.GL_LINE_LOOP);
			for (Point p : t.getPoints()) {
				p.x += a.x;
				p.y += a.y;
				p = cm.worldToLocal(p);
				gl.glVertex2f(p.x + 0.5f, p.y + 0.5f);
			}
			gl.glEnd();
		}
	}
}
