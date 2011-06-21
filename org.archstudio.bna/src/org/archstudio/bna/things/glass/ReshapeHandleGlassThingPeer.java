package org.archstudio.bna.things.glass;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.things.AbstractBoundedAnchorPointThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.swtutils.constants.Orientation;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public class ReshapeHandleGlassThingPeer<T extends ReshapeHandleGlassThing> extends
		AbstractBoundedAnchorPointThingPeer<T> {

	public static final int HANDLE_WIDTH = 6;
	public static final int HANDLE_HEIGHT = 6;

	public ReshapeHandleGlassThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, Graphics g, IResources r) {
	}

	@Override
	public boolean isInThing(IBNAView view, int worldX, int worldY) {
		Point p = t.getAnchorPoint();
		Point lp = BNAUtils.worldToLocal(view.getCoordinateMapper(), p);
		Rectangle lbb = new Rectangle();
		Orientation o = t.getOrientation();
		BNAUtils.alignRectangle(lbb, lp, HANDLE_WIDTH, HANDLE_HEIGHT, o);

		int localX = view.getCoordinateMapper().worldXtoLocalX(worldX);
		int localY = view.getCoordinateMapper().worldYtoLocalY(worldY);

		return lbb.contains(localX, localY);
	}
}
