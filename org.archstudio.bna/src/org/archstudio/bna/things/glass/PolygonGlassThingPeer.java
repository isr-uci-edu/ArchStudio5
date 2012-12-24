package org.archstudio.bna.things.glass;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.facets.IHasSelected;
import org.archstudio.bna.things.AbstractPolygonThingPeer;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class PolygonGlassThingPeer<T extends PolygonGlassThing> extends AbstractPolygonThingPeer<T> implements
		IThingPeer<T> {

	public PolygonGlassThingPeer(T thing) {
		super(thing);
	}

	public void draw(IBNAView view, ICoordinateMapper cm, GL2 gl, Rectangle clip, IResources r) {
		if (Boolean.TRUE.equals(t.get(IHasSelected.SELECTED_KEY))) {
			Point a = t.getAnchorPoint();

			gl.glLineWidth(1f);

			gl.glColor3f(1f, 1f, 1f);
			gl.glBegin(GL.GL_LINE_LOOP);
			for (Point p : t.getPoints()) {
				p.x += a.x;
				p.y += a.y;
				p = cm.worldToLocal(p);
				gl.glVertex2f(p.x + 0.5f, p.y + 0.5f);
			}
			gl.glEnd();

			gl.glColor3f(0f, 0f, 0f);
			gl.glLineStipple(1, (short) (0x0f0f0f0f >> t.getRotatingOffset() % 8));
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
