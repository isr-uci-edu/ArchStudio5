package org.archstudio.bna.things.borders;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.things.AbstractThingPeer;
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
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, final Graphics g, IResources r) {
		Rectangle lbb = cm.worldToLocal(t.getBoundingBox());
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

		g.setLineCap(SWT.CAP_SQUARE);
		g.setLineStyle(SWT.LINE_SOLID);
		g.setLineWidth(1);
		g.setForegroundColor(r.getColor(rgb));
		g.drawRectangle(lbb);
		g.setForegroundColor(r.getColor(ergb));
		g.drawRectangle(elbb);
	}

	@Override
	public boolean isInThing(IBNAView view, ICoordinateMapper cm, ICoordinate location) {
		return false;
	}
	
	@Override
	public void getLocalBounds(IBNAView view, ICoordinateMapper cm, IResources r, Rectangle boundsResult) {
		Rectangle lbb = cm.worldToLocal(t.getBoundingBox());
		int offset = t.getRotatingOffset();
		int pulse = offset % RADIANT_COUNT;
		Rectangle elbb = lbb.getExpanded(SPACER_WIDTH * pulse + 2, SPACER_WIDTH * pulse + 2);
		boundsResult.setBounds(elbb);
	}
}
