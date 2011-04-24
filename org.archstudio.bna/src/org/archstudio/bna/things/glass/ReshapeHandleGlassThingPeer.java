package org.archstudio.bna.things.glass;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

import org.archstudio.bna.AbstractThingPeer;
import org.archstudio.bna.BNAUtils;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IThing;
import org.archstudio.swtutils.constants.Orientation;

public class ReshapeHandleGlassThingPeer extends AbstractThingPeer {

	public static final int HANDLE_WIDTH = 5;
	public static final int HANDLE_HEIGHT = 5;

	protected ReshapeHandleGlassThing lt;

	protected Rectangle localBoundingBox = new Rectangle(0, 0, 0, 0);

	public ReshapeHandleGlassThingPeer(IThing t) {
		super(t);
		if (!(t instanceof ReshapeHandleGlassThing)) {
			throw new IllegalArgumentException("ReshapeHandleGlassThingPeer can only peer for ReshapeHandleGlassThing");
		}
		this.lt = (ReshapeHandleGlassThing) t;
	}

	protected void updateLocalBoundingBox(ICoordinateMapper cm) {
		Point p = lt.getAnchorPoint();
		BNAUtils.convertWorldToLocal(cm, p);
		Orientation o = lt.getOrientation();
		BNAUtils.alignRectangle(localBoundingBox, p, HANDLE_WIDTH, HANDLE_HEIGHT, o);
	}

	public void draw(IBNAView view, GC g) {
	}

	public boolean isInThing(IBNAView view, int worldX, int worldY) {
		updateLocalBoundingBox(view.getCoordinateMapper());

		int localX = view.getCoordinateMapper().worldXtoLocalX(worldX);
		int localY = view.getCoordinateMapper().worldYtoLocalY(worldY);

		return localBoundingBox.contains(localX, localY);
	}
}
