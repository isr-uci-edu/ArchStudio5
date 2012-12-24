package org.archstudio.bna.things.borders;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.things.AbstractThingPeer;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

public class PulsingBorderThingPeer<T extends PulsingBorderThing> extends AbstractThingPeer<T> implements IThingPeer<T> {

	public static final int RADIANT_COUNT = 8;
	public static final int SPACER_WIDTH = 2;

	public PulsingBorderThingPeer(T thing) {
		super(thing);
	}

	public boolean isInThing(IBNAView view, ICoordinateMapper cm, ICoordinate location) {
		return false;
	}

	public void draw(IBNAView view, ICoordinateMapper cm, GL2 gl, Rectangle clip, IResources r) {
		Rectangle lbb = cm.worldToLocal(t.getBoundingBox());
		Point p1 = new Point(lbb.x, lbb.y);
		Point p2 = new Point(lbb.x + lbb.width, lbb.y + lbb.height);
		int offset = t.getRotatingOffset();
		int pulse = offset % RADIANT_COUNT;
		int size = SPACER_WIDTH * pulse * 2 + SPACER_WIDTH;
		int msize = Math.max(0, size - SPACER_WIDTH);
		float malpha = 0.5f;
		int esize = Math.max(0, size - 4 * SPACER_WIDTH);
		RGB rgb = t.getColor();

		r.setColor(rgb, 1f);
		gl.glBegin(GL.GL_TRIANGLE_STRIP);
		gl.glVertex2i(p1.x - size, p1.y - size);
		gl.glVertex2i(p2.x + size, p1.y - size);
		r.setColor(rgb, malpha);
		gl.glVertex2i(p1.x - msize, p1.y - msize);
		gl.glVertex2i(p2.x + msize, p1.y - msize);
		r.setColor(rgb, 0f);
		gl.glVertex2i(p1.x - esize, p1.y - esize);
		gl.glVertex2i(p2.x + esize, p1.y - esize);
		gl.glEnd();

		r.setColor(rgb, 1f);
		gl.glBegin(GL.GL_TRIANGLE_STRIP);
		gl.glVertex2i(p2.x + size, p1.y - size);
		gl.glVertex2i(p2.x + size, p2.y + size);
		r.setColor(rgb, malpha);
		gl.glVertex2i(p2.x + msize, p1.y - msize);
		gl.glVertex2i(p2.x + msize, p2.y + msize);
		r.setColor(rgb, 0f);
		gl.glVertex2i(p2.x + esize, p1.y - esize);
		gl.glVertex2i(p2.x + esize, p2.y + esize);
		gl.glEnd();

		r.setColor(rgb, 1f);
		gl.glBegin(GL.GL_TRIANGLE_STRIP);
		gl.glVertex2i(p1.x - size, p2.y + size);
		gl.glVertex2i(p2.x + size, p2.y + size);
		r.setColor(rgb, malpha);
		gl.glVertex2i(p1.x - msize, p2.y + msize);
		gl.glVertex2i(p2.x + msize, p2.y + msize);
		r.setColor(rgb, 0f);
		gl.glVertex2i(p1.x - esize, p2.y + esize);
		gl.glVertex2i(p2.x + esize, p2.y + esize);
		gl.glEnd();

		r.setColor(rgb, 1f);
		gl.glBegin(GL.GL_TRIANGLE_STRIP);
		gl.glVertex2i(p1.x - size, p1.y - size);
		gl.glVertex2i(p1.x - size, p2.y + size);
		r.setColor(rgb, malpha);
		gl.glVertex2i(p1.x - msize, p1.y - msize);
		gl.glVertex2i(p1.x - msize, p2.y + msize);
		r.setColor(rgb, 0f);
		gl.glVertex2i(p1.x - esize, p1.y - esize);
		gl.glVertex2i(p1.x - esize, p2.y + esize);
		gl.glEnd();
	}
}
