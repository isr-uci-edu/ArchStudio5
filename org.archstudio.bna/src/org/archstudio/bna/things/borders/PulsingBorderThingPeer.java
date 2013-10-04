package org.archstudio.bna.things.borders;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.Resources;
import org.archstudio.bna.things.AbstractThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Rectangle;

public class PulsingBorderThingPeer<T extends PulsingBorderThing> extends AbstractThingPeer<T> {

	public static final int MAX_ROTATION = 8;
	public static final int PULSE_WIDTH = 4;

	public PulsingBorderThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	@Override
	public void draw(GL2 gl, Rectangle localBounds, Resources r) {
		Rectangle lbb = cm.worldToLocal(t.getBoundingBox());
		if (!localBounds.intersects(lbb)) {
			return;
		}

		int offset = t.getRotatingOffset() % MAX_ROTATION * PULSE_WIDTH / 2;
		Shape localShape = new Rectangle2D.Float(lbb.x - offset, lbb.y - offset, lbb.width + 2 * offset, lbb.height + 2
				* offset);

		BNAUtils.renderShapeGlow(gl, localBounds, localShape, t.getColor(), PULSE_WIDTH, 1);
	}

	@Override
	public boolean isInThing(ICoordinate location) {
		return false;
	}
}
