package org.archstudio.bna.things.shapes;

import java.awt.geom.Point2D;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.facets.IHasEdgeColor;
import org.archstudio.bna.things.AbstractMappingThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class MappingThingPeer<T extends MappingThing> extends AbstractMappingThingPeer<T> {

	public MappingThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, GL2 gl, Rectangle clip, IResources r) {
		IBNAView iView = BNAUtils.getInternalView(view, t.getInternalEndpointWorldThingID());
		if (iView == null) {
			return;
		}

		if (r.setColor(t, IHasEdgeColor.EDGE_COLOR_KEY)) {
			Point lp1 = view.getCoordinateMapper().worldToLocal(t.getAnchorPoint());
			Point lp2 = iView.getCoordinateMapper().worldToLocal(t.getInternalEndpoint());

			t.setMappingPoint(cm.localToWorld(new Point2D.Double(lp2.x, lp2.y)));

			gl.glBegin(GL.GL_LINES);
			gl.glVertex2i(lp1.x, lp1.y);
			gl.glVertex2i(lp2.x, lp2.y);
			gl.glEnd();
		}
	}
}
