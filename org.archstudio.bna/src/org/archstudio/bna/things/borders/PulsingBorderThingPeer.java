package org.archstudio.bna.things.borders;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.things.AbstractThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.swtutils.SWTWidgetUtils;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;

public class PulsingBorderThingPeer<T extends PulsingBorderThing> extends AbstractThingPeer<T> {

	public static final int RADIANT_COUNT = 3;
	public static final int SPACER_WIDTH = 3;

	protected static final int DEFAULT_BASE_SYSTEM_COLOR = SWT.COLOR_RED;
	protected static final RGB DEFAULT_BASE_RGB = new RGB(255, 0, 0);

	public PulsingBorderThingPeer(T thing) {
		super(t);
	}

	@Override
	public void draw(IBNAView view, Graphics g, Rectangle clip, ResourceUtils res) {
		Rectangle bb = t.getBoundingBox();
		Rectangle lbb = BNAUtils.worldToLocal(view.getCoordinateMapper(), bb);
		int offset = t.getRotatingOffset();
		int pulse = offset % RADIANT_COUNT;
		Rectangle elbb = lbb.getExpanded(SPACER_WIDTH * pulse, SPACER_WIDTH * pulse);

		RGB rgb = t.getColor();
		if (rgb == null) {
			rgb = DEFAULT_BASE_RGB;
		}

		RGB ergb = new RGB(rgb.red, rgb.green, rgb.blue);
		for (int i = 0; i < pulse; i++) {
			SWTWidgetUtils.lighten(ergb);
		}

		g.setForegroundColor(res.getColor(rgb, DEFAULT_BASE_SYSTEM_COLOR));
		g.drawRectangle(lbb);
		g.setForegroundColor(res.getColor(ergb, DEFAULT_BASE_SYSTEM_COLOR));
		g.drawRectangle(elbb);
	}

	@Override
	public boolean isInThing(IBNAView view, int worldX, int worldY) {
		return false;
	}
}
