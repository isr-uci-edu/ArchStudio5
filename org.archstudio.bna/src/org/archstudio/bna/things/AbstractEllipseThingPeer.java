package org.archstudio.bna.things;

import java.awt.geom.Ellipse2D;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public abstract class AbstractEllipseThingPeer<T extends AbstractEllipseThing> extends AbstractThingPeer<T> {

	public AbstractEllipseThingPeer(T thing) {
		super(thing);
	}

	@Override
	public boolean isInThing(IBNAView view, ICoordinateMapper cm, ICoordinate location) {
		Rectangle lbb = cm.worldToLocal(t.getBoundingBox());
		Point lp = location.getLocalPoint(new Point());
		return new Ellipse2D.Double(lbb.x, lbb.y, lbb.width, lbb.height).contains(lp.x, lp.y);
	}

	@Override
	public void getLocalBounds(IBNAView view, ICoordinateMapper cm, Graphics g, IResources r, Rectangle boundsResult) {
		cm.worldToLocal(boundsResult.setBounds(t.getBoundingBox()));
		if (this instanceof IHasShadowThingPeer) {
			BNAUtils.drawShadowExpandBoundingBox(boundsResult);
		}
	}
}
