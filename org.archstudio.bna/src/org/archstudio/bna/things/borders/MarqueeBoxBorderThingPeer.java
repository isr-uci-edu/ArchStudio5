package org.archstudio.bna.things.borders;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.things.AbstractRectangleThingPeer;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class MarqueeBoxBorderThingPeer<T extends MarqueeBoxBorderThing> extends AbstractRectangleThingPeer<T> implements
		IThingPeer<T> {

	public MarqueeBoxBorderThingPeer(T thing) {
		super(thing);
	}

	public void draw(IBNAView view, ICoordinateMapper cm, GL2 gl, Rectangle clip, IResources r) {
		Rectangle lbb = cm.worldToLocal(t.getBoundingBox());
		Point p1 = new Point(lbb.x, lbb.y);
		Point p2 = new Point(lbb.x + lbb.width, lbb.y + lbb.height);

		gl.glLineWidth(1f);

		gl.glColor3f(1f, 1f, 1f);
		gl.glBegin(GL.GL_LINE_LOOP);
		gl.glVertex2f(p1.x + 0.5f, p1.y + 0.5f);
		gl.glVertex2f(p2.x - 0.5f, p1.y + 0.5f);
		gl.glVertex2f(p2.x - 0.5f, p2.y - 0.5f);
		gl.glVertex2f(p1.x + 0.5f, p2.y - 0.5f);
		gl.glEnd();

		gl.glColor3f(0f, 0f, 0f);
		gl.glLineStipple(1, (short) (0x0f0f0f0f << t.getRotatingOffset() % 8 >> 8));
		gl.glBegin(GL.GL_LINE_LOOP);
		gl.glVertex2f(p1.x + 0.5f, p1.y + 0.5f);
		gl.glVertex2f(p2.x - 0.5f, p1.y + 0.5f);
		gl.glVertex2f(p2.x - 0.5f, p2.y - 0.5f);
		gl.glVertex2f(p1.x + 0.5f, p2.y - 0.5f);
		gl.glEnd();

		gl.glLineStipple(1, (short) 0xffff);
	}
}
