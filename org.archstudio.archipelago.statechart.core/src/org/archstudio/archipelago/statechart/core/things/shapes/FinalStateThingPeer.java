package org.archstudio.archipelago.statechart.core.things.shapes;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.Resources;
import org.archstudio.bna.things.shapes.EllipseThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Rectangle;

public class FinalStateThingPeer<T extends FinalStateThing> extends EllipseThingPeer<T> {

	public FinalStateThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	@Override
	public void draw(GL2 gl, Rectangle localBounds, Resources r) {
		Rectangle lbb = BNAUtils.getLocalBoundingBox(cm, t);
		if (!localBounds.intersects(lbb)) {
			return;
		}

		Shape outerShape = new Ellipse2D.Double(lbb.x, lbb.y, lbb.width, lbb.height);
		Shape innerShape = new Ellipse2D.Double(lbb.x + 4f, lbb.y + 4f, lbb.width - 8, lbb.height - 8);

		if (t.getColor() != null) {
			BNAUtils.renderShapeFill(gl, localBounds, innerShape, t.getColor(),
					t.isGradientFilled() ? t.getSecondaryColor() : null);
		}
		if (r.setLineStyle(t)) {
			BNAUtils.renderShapeEdge(gl, localBounds, outerShape);
			BNAUtils.renderShapeEdge(gl, localBounds, innerShape);
			r.resetLineStyle();
		}
		if (t.isSelected()) {
			BNAUtils.renderShapeSelected(gl, localBounds, outerShape, t.getRotatingOffset());
		}
	}

}
