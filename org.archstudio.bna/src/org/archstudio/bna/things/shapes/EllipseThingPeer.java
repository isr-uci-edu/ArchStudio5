package org.archstudio.bna.things.shapes;

import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.facets.IHasEdgeColor;
import org.archstudio.bna.things.AbstractEllipseThingPeer;
import org.eclipse.swt.graphics.Rectangle;

public class EllipseThingPeer<T extends EllipseThing> extends AbstractEllipseThingPeer<T> implements IThingPeer<T> {

	public EllipseThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, GL2 gl, Rectangle clip, IResources r) {

		Rectangle lbb = cm.worldToLocal(t.getBoundingBox());
		float error = 0.25f;
		float radius = Math.max(lbb.width, lbb.height) / 2;
		float radianDelta = (float) Math.acos(2 * (1 - error / radius) * (1 - error / radius) - 1);
		float cx = lbb.x + (float) lbb.width / 2;
		float cy = lbb.y + (float) lbb.height / 2;

		if (r.setColor(t, IHasColor.COLOR_KEY) && r.setLineStyle(t)) {
			gl.glBegin(GL2.GL_TRIANGLE_FAN);
			for (float radians = 0; radians < 2 * Math.PI; radians += radianDelta) {
				float x = cx + (float) Math.cos(radians) * lbb.width / 2f;
				float y = cy + (float) Math.sin(radians) * lbb.height / 2f;
				gl.glVertex2f(x, y);
			}
			gl.glVertex2f(cx + lbb.width / 2, cy);
			gl.glEnd();
		}
		if (r.setColor(t, IHasEdgeColor.EDGE_COLOR_KEY) && r.setLineStyle(t)) {
			gl.glBegin(GL2.GL_LINE_LOOP);
			for (float radians = 0; radians < 2 * Math.PI; radians += radianDelta) {
				float x = cx + (float) Math.cos(radians) * lbb.width / 2f;
				float y = cy + (float) Math.sin(radians) * lbb.height / 2f;
				gl.glVertex2f(x, y);
			}
			gl.glVertex2f(cx + lbb.width / 2, cy);
			gl.glEnd();
		}
	}
}
