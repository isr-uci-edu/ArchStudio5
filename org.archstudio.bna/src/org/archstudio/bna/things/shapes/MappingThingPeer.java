package org.archstudio.bna.things.shapes;

import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.things.AbstractMappingThingPeer;
import org.archstudio.bna.ui.IUIResources;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class MappingThingPeer<T extends MappingThing> extends AbstractMappingThingPeer<T> {

	public MappingThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	@Override
	public boolean draw(Rectangle localBounds, IUIResources r) {
		IBNAView iView = BNAUtils.getInternalView(view, t.getInternalEndpointWorldThingID());
		if (iView == null) {
			return false;
		}

		Point lp1 = view.getCoordinateMapper().worldToLocal(t.getAnchorPoint());
		Point lp2 = iView.getCoordinateMapper().worldToLocal(t.getInternalEndpoint());
		t.setExternalEndpoint(cm.localToWorld(new Point2D.Double(lp2.x, lp2.y)));

		Shape localShape = new Line2D.Double(lp1.x, lp1.y, lp2.x, lp2.y);

		if (r.setLineStyle(t)) {
			r.drawShape(localShape);
			r.resetLineStyle();
		}

		return true;
	}
}
