package org.archstudio.bna.things.shapes;

import java.awt.Dimension;

import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.things.AbstractAnchorPointThingPeer;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class ReshapeHandleThingPeer<T extends ReshapeHandleThing> extends AbstractAnchorPointThingPeer<T> implements
		IThingPeer<T> {

	public ReshapeHandleThingPeer(T thing) {
		super(thing);
	}

	public void draw(IBNAView view, ICoordinateMapper cm, GL2 gl, Rectangle clip, IResources r) {
		Rectangle lbb = getLocalBounds(view, cm);
		Point p1 = new Point(lbb.x, lbb.y);
		Point p2 = new Point(lbb.x + lbb.width, lbb.y + lbb.height);

		if (r.setColor(t, IHasColor.COLOR_KEY)) {
			gl.glBegin(GL2.GL_QUADS);
			gl.glVertex2i(p1.x, p1.y);
			gl.glVertex2i(p2.x, p1.y);
			gl.glVertex2i(p2.x, p2.y);
			gl.glVertex2i(p1.x, p2.y);
			gl.glEnd();
		}
	}

	public boolean isInThing(IBNAView view, ICoordinateMapper cm, ICoordinate location) {
		return getLocalBounds(view, cm).contains(location.getLocalPoint());
	}

	public Rectangle getLocalBounds(IBNAView view, ICoordinateMapper cm) {
		Point lap = cm.worldToLocal(t.getAnchorPoint());
		Dimension size = t.getSize();
		return new Rectangle(lap.x - size.width / 2, lap.y - size.height / 2, size.width, size.height);
	}
}
