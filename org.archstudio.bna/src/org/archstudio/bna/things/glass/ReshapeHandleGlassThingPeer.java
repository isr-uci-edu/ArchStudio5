package org.archstudio.bna.things.glass;

import java.awt.Dimension;

import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.things.AbstractAnchorPointThingPeer;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class ReshapeHandleGlassThingPeer<T extends ReshapeHandleGlassThing> extends AbstractAnchorPointThingPeer<T>
		implements IThingPeer<T> {

	public ReshapeHandleGlassThingPeer(T thing) {
		super(thing);
	}

	public void draw(IBNAView view, ICoordinateMapper cm, GL2 gl, Rectangle clip, IResources r) {
	}

	public boolean isInThing(IBNAView view, ICoordinateMapper cm, ICoordinate location) {
		return getLocalBounds(view, cm).contains(location.getLocalPoint());
	}

	public Rectangle getLocalBounds(IBNAView view, ICoordinateMapper cm) {
		Point ap = cm.worldToLocal(t.getAnchorPoint());
		Dimension size = t.getSize();
		return new Rectangle(ap.x - size.width / 2, ap.y - size.height / 2, size.width, size.height);
	}
}
