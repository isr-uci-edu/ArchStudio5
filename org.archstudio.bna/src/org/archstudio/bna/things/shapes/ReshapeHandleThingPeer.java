package org.archstudio.bna.things.shapes;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

import org.archstudio.bna.AbstractThingPeer;
import org.archstudio.bna.BNAUtils;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IThing;
import org.archstudio.bna.ResourceUtils;
import org.archstudio.swtutils.constants.Orientation;

public class ReshapeHandleThingPeer extends AbstractThingPeer {

	public static final int HANDLE_WIDTH = 5;
	public static final int HANDLE_HEIGHT = 5;

	protected ReshapeHandleThing lt;

	protected Rectangle localBoundingBox = new Rectangle(0, 0, 0, 0);

	public ReshapeHandleThingPeer(IThing t) {
		super(t);
		if (!(t instanceof ReshapeHandleThing)) {
			throw new IllegalArgumentException("ReshapeHandleThingPeer can only peer for ReshapeHandleThing");
		}
		this.lt = (ReshapeHandleThing) t;
	}

	protected void updateLocalBoundingBox(ICoordinateMapper cm) {
		Point p = lt.getAnchorPoint();
		BNAUtils.convertWorldToLocal(cm, p);
		Orientation o = lt.getOrientation();
		BNAUtils.alignRectangle(localBoundingBox, p, HANDLE_WIDTH, HANDLE_HEIGHT, o);
	}

	public void draw(IBNAView view, GC g) {
		ICoordinateMapper cm = view.getCoordinateMapper();
		updateLocalBoundingBox(cm);
		Rectangle wbb = BNAUtils.localToWorld(cm, localBoundingBox);
		lt.setProperty(ReshapeHandleThing.HANDLE_BOUNDING_BOX_PROPERTY_NAME, wbb);
		if (!g.getClipping().intersects(localBoundingBox))
			return;

		Color fg = ResourceUtils.getColor(getDisplay(), lt.getColor());
		if (fg == null) {
			fg = g.getDevice().getSystemColor(SWT.COLOR_BLUE);
		}

		g.setBackground(fg);
		g.fillRectangle(localBoundingBox);
	}

	public boolean isInThing(IBNAView view, int worldX, int worldY) {
		ICoordinateMapper cm = view.getCoordinateMapper();
		updateLocalBoundingBox(cm);
		Rectangle wbb = BNAUtils.localToWorld(cm, localBoundingBox);
		lt.setProperty(ReshapeHandleThing.HANDLE_BOUNDING_BOX_PROPERTY_NAME, wbb);

		int localX = cm.worldXtoLocalX(worldX);
		int localY = cm.worldYtoLocalY(worldY);

		return localBoundingBox.contains(localX, localY);
	}
}
