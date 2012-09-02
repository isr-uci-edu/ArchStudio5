package org.archstudio.bna.things.glass;

import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.facets.IHasSelected;
import org.archstudio.bna.things.AbstractEllipseThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Rectangle;

public class EllipseGlassThingPeer<T extends EllipseGlassThing> extends AbstractEllipseThingPeer<T> implements
		IThingPeer<T> {

	public EllipseGlassThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, GL2 gl, Rectangle clip, IResources r) {
		if (Boolean.TRUE.equals(t.get(IHasSelected.SELECTED_KEY))) {
			Rectangle lbb = BNAUtils.getLocalBoundingBox(cm, t);
			lbb.width -= 1;
			lbb.height -= 1;
			float[] points = BNAUtils.getEllipsePoints(lbb);

			gl.glLineWidth(1f);

			gl.glColor3f(1f, 1f, 1f);
			gl.glBegin(GL2.GL_LINE_LOOP);
			for (int i = 0; i < points.length; i += 2) {
				gl.glVertex2f(points[i] + 0.5f, points[i + 1] + 0.5f);
			}
			gl.glEnd();

			gl.glColor3f(0f, 0f, 0f);
			gl.glLineStipple(1, (short) (0x0f0f0f0f >> t.getRotatingOffset() % 8));
			gl.glBegin(GL2.GL_LINE_LOOP);
			for (int i = 0; i < points.length; i += 2) {
				gl.glVertex2f(points[i] + 0.5f, points[i + 1] + 0.5f);
			}
			gl.glEnd();
		}
	}
}
