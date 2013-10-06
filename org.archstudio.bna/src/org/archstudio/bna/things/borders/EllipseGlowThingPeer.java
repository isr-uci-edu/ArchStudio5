package org.archstudio.bna.things.borders;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.things.AbstractEllipseThingPeer;
import org.archstudio.bna.ui.IUIResources;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Rectangle;

public class EllipseGlowThingPeer<T extends EllipseGlowThing> extends AbstractEllipseThingPeer<T> {

	public EllipseGlowThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	@Override
	public boolean draw(Rectangle localBounds, IUIResources r) {
		Rectangle lbb = BNAUtils.getLocalBoundingBox(cm, t);
		if (!localBounds.intersects(lbb)) {
			return false;
		}

		Shape localShape = new Ellipse2D.Float(lbb.x, lbb.y, lbb.width, lbb.height);

		r.glowShape(localShape, t.getColor(), t.getWidth(), t.getAlpha());

		return true;
	}

}
