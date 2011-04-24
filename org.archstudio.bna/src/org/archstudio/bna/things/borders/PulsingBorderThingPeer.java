package org.archstudio.bna.things.borders;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

import org.archstudio.bna.*;
import org.archstudio.swtutils.SWTWidgetUtils;

public class PulsingBorderThingPeer extends AbstractThingPeer {

	protected PulsingBorderThing lt;

	public static final int RADIANT_COUNT = 3;
	public static final int SPACER_WIDTH = 3;
	protected static final RGB DEFAULT_BASE_RGB = new RGB(255, 0, 0);

	protected Rectangle localBoundingBox = new Rectangle(0, 0, 0, 0);

	public PulsingBorderThingPeer(IThing t) {
		super(t);
		if (!(t instanceof PulsingBorderThing)) {
			throw new IllegalArgumentException("PulsingBorderThingPeer can only peer for PulsingBorderThing");
		}
		this.lt = (PulsingBorderThing) t;
	}

	protected void updateLocalBoundingBox(ICoordinateMapper cm) {
		Rectangle worldBoundingBox = lt.getBoundingBox();
		worldBoundingBox = BNAUtils.normalizeRectangle(worldBoundingBox);
		localBoundingBox.x = cm.worldXtoLocalX(worldBoundingBox.x);
		localBoundingBox.y = cm.worldYtoLocalY(worldBoundingBox.y);

		int lx2 = cm.worldXtoLocalX(worldBoundingBox.x + worldBoundingBox.width);
		int ly2 = cm.worldYtoLocalY(worldBoundingBox.y + worldBoundingBox.height);

		localBoundingBox.width = lx2 - localBoundingBox.x;
		localBoundingBox.height = ly2 - localBoundingBox.y;
	}

	protected Rectangle r = new Rectangle(0, 0, 0, 0);

	public void draw(IBNAView view, GC g) {
		updateLocalBoundingBox(view.getCoordinateMapper());
		if (!g.getClipping().intersects(localBoundingBox))
			return;

		int offset = lt.getRotatingOffset();
		int pulse = offset % RADIANT_COUNT;

		RGB baseRGB = lt.getColor();
		if (baseRGB == null) {
			baseRGB = DEFAULT_BASE_RGB;
		}
		Color baseColor = ResourceUtils.getColor(getDisplay(), baseRGB);

		RGB lighterRGB = new RGB(baseRGB.red, baseRGB.green, baseRGB.blue);
		for (int i = 0; i < pulse; i++) {
			SWTWidgetUtils.lighten(lighterRGB);
		}
		Color lighterColor = ResourceUtils.getColor(getDisplay(), lighterRGB);

		g.setLineStyle(SWT.LINE_SOLID);
		g.setLineWidth(1);
		g.setLineCap(SWT.CAP_FLAT);

		g.setForeground(baseColor);
		g.drawRectangle(localBoundingBox);

		r.x = localBoundingBox.x - (SPACER_WIDTH * pulse);
		r.y = localBoundingBox.y - (SPACER_WIDTH * pulse);
		r.width = localBoundingBox.width + (SPACER_WIDTH * pulse * 2);
		r.height = localBoundingBox.height + (SPACER_WIDTH * pulse * 2);

		g.setForeground(lighterColor);
		g.drawRectangle(r);
	}

	/*
	 * public Rectangle getLocalBoundingBox(IBNAContext context, GC g,
	 * ICoordinateMapper cm){ updateLocalBoundingBox(cm); return
	 * localBoundingBox; }
	 */

	public boolean isInThing(IBNAView view, int worldX, int worldY) {
		return false;
	}

}
