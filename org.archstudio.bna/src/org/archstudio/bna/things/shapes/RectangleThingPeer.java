package org.archstudio.bna.things.shapes;

import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.facets.IHasEdgeColor;
import org.archstudio.bna.facets.IHasSecondaryColor;
import org.archstudio.bna.things.AbstractRectangleThingPeer;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class RectangleThingPeer<T extends RectangleThing> extends AbstractRectangleThingPeer<T> implements
		IThingPeer<T> {

	public RectangleThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, GL2 gl, Rectangle clip, IResources r) {
		Rectangle lbb = cm.worldToLocal(t.getBoundingBox());
		Point p1 = new Point(lbb.x, lbb.y);
		Point p2 = new Point(lbb.x + lbb.width, lbb.y + lbb.height);
		if (r.setColor(t, IHasColor.COLOR_KEY)) {
			gl.glBegin(GL2.GL_QUADS);
			gl.glVertex2i(p1.x, p1.y);
			gl.glVertex2i(p2.x, p1.y);
			if (t.isGradientFilled()) {
				r.setColor(t, IHasSecondaryColor.SECONDARY_COLOR_KEY);
			}
			gl.glVertex2i(p2.x, p2.y);
			gl.glVertex2i(p1.x, p2.y);
			gl.glEnd();
		}
		if (r.setColor(t, IHasEdgeColor.EDGE_COLOR_KEY) && r.setLineStyle(t)) {
			int count = t.getCount();
			int width = t.getLineWidth();
			while (count > 0) {
				int inset = (count - 1) * width * 2;
				gl.glBegin(GL2.GL_LINE_LOOP);
				gl.glVertex2f(p1.x + inset + 0.5f, p1.y + inset + 0.5f);
				gl.glVertex2f(p2.x - inset - 0.5f, p1.y + inset + 0.5f);
				gl.glVertex2f(p2.x - inset - 0.5f, p2.y - inset - 0.5f);
				gl.glVertex2f(p1.x + inset + 0.5f, p2.y - inset - 0.5f);
				gl.glEnd();
				count--;
			}
		}
	}

//	@Override
//	public void drawShadow(IBNAView view, ICoordinateMapper cm, GL2 gl, IResources r) {
//		Rectangle lbb = cm.worldToLocal(t.getBoundingBox());
//		Point p1 = new Point(lbb.x, lbb.y);
//		Point p2 = new Point(lbb.x + lbb.width, lbb.y + lbb.height);
//		gl.glBegin(GL2.GL_QUADS);
//		gl.glVertex2i(p1.x, p1.y);
//		gl.glVertex2i(p2.x, p1.y);
//		gl.glVertex2i(p2.x, p2.y);
//		gl.glVertex2i(p1.x, p2.y);
//		gl.glEnd();
//	}

}
