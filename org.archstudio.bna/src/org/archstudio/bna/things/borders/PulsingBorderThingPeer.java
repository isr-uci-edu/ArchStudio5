package org.archstudio.bna.things.borders;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.things.AbstractThingPeer;
import org.archstudio.bna.ui.IUIResources;
import org.eclipse.swt.graphics.Rectangle;

public class PulsingBorderThingPeer<T extends PulsingBorderThing> extends AbstractThingPeer<T> {

	public static final int MAX_ROTATION = 8;
	public static final int PULSE_WIDTH = 8;

	public PulsingBorderThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	@Override
	public boolean draw(Rectangle localBounds, IUIResources r) {
		Rectangle lbb = cm.worldToLocal(t.getBoundingBox());
		if (!localBounds.intersects(lbb)) {
			return false;
		}

		int offset = t.getRotatingOffset() % MAX_ROTATION * PULSE_WIDTH;
		Shape localShape = new Rectangle2D.Float(lbb.x - offset, lbb.y - offset, lbb.width + 2 * offset, lbb.height + 2
				* offset);

		r.glowShape(localShape, t.getColor(), PULSE_WIDTH, 1);

		return true;
	}
}
