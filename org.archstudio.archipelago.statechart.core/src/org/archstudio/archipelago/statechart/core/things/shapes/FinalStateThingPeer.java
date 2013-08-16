package org.archstudio.archipelago.statechart.core.things.shapes;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.things.shapes.EllipseThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Rectangle;

public class FinalStateThingPeer<T extends FinalStateThing> extends EllipseThingPeer<T> {

	public FinalStateThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, GL2 gl, Rectangle clip, IResources r) {
		Rectangle lbb = BNAUtils.getLocalBoundingBox(cm, t);
		if (!clip.intersects(lbb)) {
			return;
		}

		Shape outerShape = new Ellipse2D.Float(lbb.x, lbb.y, lbb.width, lbb.height);
		Shape innerShape = new Ellipse2D.Float(lbb.x + 4f, lbb.y + 4f, lbb.width - 8, lbb.height - 8);

		BNAUtils.renderShapeEdge(t, view, cm, gl, clip, r, outerShape);
		BNAUtils.renderShapeFill(t, view, cm, gl, clip, r, innerShape);
		BNAUtils.renderShapeEdge(t, view, cm, gl, clip, r, innerShape);
		BNAUtils.renderShapeSelected(t, view, cm, gl, clip, r, outerShape);
	}

}
