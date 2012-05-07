package org.archstudio.bna.things.shapes;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.things.AbstractAnchorPointThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public class ReshapeHandleThingPeer<T extends ReshapeHandleThing> extends AbstractAnchorPointThingPeer<T> implements
		IThingPeer<T> {

	public ReshapeHandleThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, IResources r, Graphics g) {
		if (BNAUtils.setBackgroundColor(r, g, t, IHasColor.COLOR_KEY)) {
			Rectangle lbb = getLocalBounds(view, cm, r);
			g.fillRectangle(lbb);
		}
	}

	@Override
	public boolean isInThing(IBNAView view, ICoordinateMapper cm, ICoordinate location) {
		Point lap = cm.worldToLocal(t.getAnchorPoint());
		Dimension size = t.getSize();
		return new Rectangle(lap.x - size.width / 2, lap.y - size.height / 2, size.width, size.height)
				.contains(location.getLocalPoint());
	}

	@Override
	public Rectangle getLocalBounds(IBNAView view, ICoordinateMapper cm, IResources r) {
		Point lap = cm.worldToLocal(t.getAnchorPoint());
		Dimension size = t.getSize();
		return new Rectangle(lap.x - size.width / 2, lap.y - size.height / 2, size.width, size.height);
	}
}
