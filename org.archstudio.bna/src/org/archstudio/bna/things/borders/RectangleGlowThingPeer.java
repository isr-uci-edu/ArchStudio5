package org.archstudio.bna.things.borders;

import java.awt.Dimension;

import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.things.AbstractRectangleThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

public class RectangleGlowThingPeer<T extends RectangleGlowThing> extends AbstractRectangleThingPeer<T> {

	public RectangleGlowThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, GL2 gl, Rectangle clip, IResources r) {
		Rectangle lbb = BNAUtils.getLocalBoundingBox(cm, t);
		Point p1 = new Point(lbb.x, lbb.y);
		Point p2 = new Point(lbb.x + lbb.width, lbb.y + lbb.height);
		Dimension s = t.getSize();
		RGB color = t.getColor();

		if (color != null) {
			gl.glBegin(GL2.GL_TRIANGLE_STRIP);

			r.setColor(color, 0.5f);
			gl.glVertex2i(p1.x, p1.y);
			r.setColor(color, 0f);
			gl.glVertex2i(p1.x - s.width, p1.y - s.height);

			r.setColor(color, 0.5f);
			gl.glVertex2i(p2.x, p1.y);
			r.setColor(color, 0f);
			gl.glVertex2i(p2.x + s.width, p1.y - s.height);

			r.setColor(color, 0.5f);
			gl.glVertex2i(p2.x, p2.y);
			r.setColor(color, 0f);
			gl.glVertex2i(p2.x + s.width, p2.y + s.height);

			r.setColor(color, 0.5f);
			gl.glVertex2i(p1.x, p2.y);
			r.setColor(color, 0f);
			gl.glVertex2i(p1.x - s.width, p2.y + s.height);

			r.setColor(color, 0.5f);
			gl.glVertex2i(p1.x, p1.y);
			r.setColor(color, 0f);
			gl.glVertex2i(p1.x - s.width, p1.y - s.height);

			gl.glEnd();
		}
	}

	@Override
	public boolean isInThing(IBNAView view, ICoordinateMapper cm, ICoordinate location) {
		return false;
	}

}
