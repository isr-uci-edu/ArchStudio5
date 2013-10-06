package org.archstudio.bna.things.glass;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.things.AbstractBoundedAnchorPointThingPeer;
import org.archstudio.bna.ui.IUIResources;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Rectangle;

public class EndpointGlassThingPeer<T extends EndpointGlassThing> extends AbstractBoundedAnchorPointThingPeer<T> {

	public EndpointGlassThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	@Override
	public boolean draw(Rectangle localBounds, IUIResources r) {
		Rectangle lbb = BNAUtils.getLocalBoundingBox(cm, t);
		if (!lbb.intersects(localBounds) || !t.isSelected()) {
			return false;
		}

		Shape localShape = new Rectangle2D.Float(lbb.x, lbb.y, lbb.width, lbb.height);

		r.selectShape(localShape, t.getRotatingOffset());

		return true;
	}
}
