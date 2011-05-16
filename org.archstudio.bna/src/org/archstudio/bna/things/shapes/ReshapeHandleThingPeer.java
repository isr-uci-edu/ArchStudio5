package org.archstudio.bna.things.shapes;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.things.AbstractThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.swtutils.constants.Orientation;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;

public class ReshapeHandleThingPeer<T extends ReshapeHandleThing> extends AbstractThingPeer<T> {

	public static final int HANDLE_WIDTH = 5;
	public static final int HANDLE_HEIGHT = 5;

	public ReshapeHandleThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, Graphics g, Rectangle clip, ResourceUtils res) {
		Point p = t.getAnchorPoint();
		Point lp = BNAUtils.worldToLocal(view.getCoordinateMapper(), p);
		Rectangle lbb = new Rectangle();
		Orientation o = t.getOrientation();
		BNAUtils.alignRectangle(lbb, lp, HANDLE_WIDTH, HANDLE_HEIGHT, o);

		if (!clip.intersects(lbb)) {
			return;
		}

		g.setBackgroundColor(res.getColor(t.getColor(), SWT.COLOR_BLUE));
		g.fillRectangle(lbb);
	}

	@Override
	public boolean isInThing(IBNAView view, int worldX, int worldY) {
		return false;
	}
}
