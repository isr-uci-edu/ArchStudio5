package org.archstudio.bna.things.glass;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.things.AbstractMappingThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class MappingGlassThingPeer<T extends MappingGlassThing> extends AbstractMappingThingPeer<T> {

	public MappingGlassThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, GL2 gl, Rectangle clip, IResources r) {
		if (t.isSelected()) {
			IBNAView iView = BNAUtils.getInternalView(view, t.getInternalEndpointWorldThingID());
			if (iView == null) {
				return;
			}

			Point lp1 = view.getCoordinateMapper().worldToLocal(t.getAnchorPoint());
			Point lp2 = iView.getCoordinateMapper().worldToLocal(t.getInternalEndpoint());

			gl.glLineWidth(1f);

			gl.glColor3f(1f, 1f, 1f);
			gl.glBegin(GL.GL_LINES);
			gl.glVertex2i(lp1.x, lp1.y);
			gl.glVertex2i(lp2.x, lp2.y);
			gl.glEnd();

			gl.glColor3f(0f, 0f, 0f);
			gl.glLineStipple(1, (short) (0x0f0f0f0f >> t.getRotatingOffset() % 8));
			gl.glBegin(GL.GL_LINES);
			gl.glVertex2i(lp1.x, lp1.y);
			gl.glVertex2i(lp2.x, lp2.y);
			gl.glEnd();
		}
	}

}
