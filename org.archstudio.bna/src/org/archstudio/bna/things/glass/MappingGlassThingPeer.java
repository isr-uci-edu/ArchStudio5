package org.archstudio.bna.things.glass;

import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.Resources;
import org.archstudio.bna.things.AbstractMappingThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class MappingGlassThingPeer<T extends MappingGlassThing> extends AbstractMappingThingPeer<T> {

	public MappingGlassThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	@Override
	public void draw(GL2 gl, Rectangle localBounds, Resources r) {
		IBNAView iView = BNAUtils.getInternalView(view, t.getInternalEndpointWorldThingID());
		if (iView == null || !t.isSelected()) {
			return;
		}

		Point lp1 = view.getCoordinateMapper().worldToLocal(t.getAnchorPoint());
		Point lp2 = iView.getCoordinateMapper().worldToLocal(t.getInternalEndpoint());
		t.setExternalEndpoint(cm.localToWorld(new Point2D.Double(lp2.x, lp2.y)));

		Shape localShape = new Line2D.Float(lp1.x, lp1.y, lp2.x, lp2.y);

		BNAUtils.renderShapeSelected(gl, localBounds, localShape, t.getRotatingOffset());
	}
}
