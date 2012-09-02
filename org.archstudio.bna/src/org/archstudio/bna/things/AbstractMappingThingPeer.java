package org.archstudio.bna.things;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Point;

public abstract class AbstractMappingThingPeer<T extends AbstractMappingThing> extends AbstractAnchorPointThingPeer<T> {

	public AbstractMappingThingPeer(T thing) {
		super(thing);
	}

	@Override
	public boolean isInThing(IBNAView view, ICoordinateMapper cm, ICoordinate location) {
		IBNAView iView = BNAUtils.getInternalView(view, t.getInternalEndpointWorldThingID());
		if (iView == null) {
			return false;
		}

		Point lp1 = view.getCoordinateMapper().worldToLocal(t.getAnchorPoint());
		Point lp2 = iView.getCoordinateMapper().worldToLocal(t.getInternalEndpoint());
		Point p = location.getLocalPoint();

		if (Line2D.ptSegDistSq(lp1.x, lp1.y, lp2.x, lp2.y, p.x, p.y) <= 25) {
			return Point2D.distanceSq(lp2.x, lp2.y, p.x, p.y) > 25;
		}
		return false;
	}
}
