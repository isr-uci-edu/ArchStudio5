package org.archstudio.bna.things.borders;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.things.AbstractThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;

public class PulsingBorderThingPeer<T extends PulsingBorderThing> extends AbstractThingPeer<T> implements IThingPeer<T> {

	public static final int RADIANT_COUNT = 8;
	public static final int SPACER_WIDTH = 2;

	public PulsingBorderThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, IResources r, Graphics g) {
		if (BNAUtils.setForegroundColor(r, g, t, IHasColor.COLOR_KEY)) {
			Rectangle lbb = cm.worldToLocal(t.getBoundingBox());
			int offset = t.getRotatingOffset();
			int pulse = offset % RADIANT_COUNT;
			Rectangle elbb = lbb.getExpanded(SPACER_WIDTH * pulse, SPACER_WIDTH * pulse);

			g.setLineCap(SWT.CAP_SQUARE);
			g.setLineStyle(SWT.LINE_SOLID);
			g.setLineWidth(SPACER_WIDTH * pulse / 2);
			g.setAlpha(Math.max(Math.min(255 - pulse * 255 / RADIANT_COUNT, 255), 0));
			g.drawRectangle(elbb);
		}
	}

	@Override
	public boolean isInThing(IBNAView view, ICoordinateMapper cm, ICoordinate location) {
		return false;
	}

	@Override
	public Rectangle getLocalBounds(IBNAView view, ICoordinateMapper cm, IResources r) {
		Rectangle lbb = cm.worldToLocal(t.getBoundingBox());
		Rectangle elbb = lbb.getExpanded(SPACER_WIDTH * RADIANT_COUNT * 2 + 2, SPACER_WIDTH * RADIANT_COUNT * 2 + 2);
		return elbb;
	}
}
