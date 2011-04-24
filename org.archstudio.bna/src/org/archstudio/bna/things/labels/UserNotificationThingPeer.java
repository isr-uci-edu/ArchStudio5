package org.archstudio.bna.things.labels;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;

import org.archstudio.bna.*;
import org.archstudio.swtutils.constants.FontStyle;
import org.archstudio.swtutils.constants.HorizontalAlignment;
import org.archstudio.swtutils.constants.VerticalAlignment;

public class UserNotificationThingPeer extends AbstractThingPeer {

	protected UserNotificationThing lt;

	protected Point localAnchorPoint = new Point(0, 0);

	public UserNotificationThingPeer(IThing t) {
		super(t);
		if (!(t instanceof UserNotificationThing)) {
			throw new IllegalArgumentException("UserNotificationThingPeer can only peer for UserNotificationThing");
		}
		this.lt = (UserNotificationThing) t;
	}

	protected void updateLocalAnchorPoint(ICoordinateMapper cm) {
		localAnchorPoint = BNAUtils.worldToLocal(cm, lt.getAnchorPoint());
	}

	public void draw(IBNAView view, GC g) {
		updateLocalAnchorPoint(view.getCoordinateMapper());
		//if(!g.getClipping().intersects(localBoundingBox)) return;

		//BNAComposite bnaComposite = getBNAComposite(context);

		String text = lt.getText();
		String fontName = lt.getFontName();
		int fontSize = lt.getFontSize();
		FontStyle fontStyle = lt.getFontStyle();

		VerticalAlignment verticalAlignment = lt.getVerticalAlignment();
		HorizontalAlignment horizontalAlignment = lt.getHorizontalAlignment();

		Point textExtent = g.textExtent(text);

		Point p = new Point(localAnchorPoint.x, localAnchorPoint.y);

		switch (horizontalAlignment) {
		case CENTER:
			p.x = p.x - (textExtent.x / 2);
			break;
		case RIGHT:
			p.x = p.x - (textExtent.x);
			break;
		}

		switch (verticalAlignment) {
		case MIDDLE:
			p.y = p.y - (textExtent.y / 2);
			break;
		case BOTTOM:
			p.y = p.y - (textExtent.y);
			break;
		}

		//Life drops from 16 to 0
		int life = lt.getLife();

		//Step goes from 0 to 8 back to 0
		int step = 8 - Math.abs(life - 8);
		int alpha = step * 64;
		if (alpha > 255)
			alpha = 255;
		g.setAlpha(alpha);

		//Draw a little shadow
		g.setForeground(g.getDevice().getSystemColor(SWT.COLOR_DARK_GRAY));
		g.drawLine(p.x - 1, p.y - 1 + textExtent.y + 4, p.x - 1 + textExtent.x + 4, p.y - 1 + textExtent.y + 4);
		g.drawLine(p.x - 1 + textExtent.x + 4, p.y - 1, p.x - 1 + textExtent.x + 4, p.y - 1 + textExtent.y + 4);

		Color bg = ResourceUtils.getColor(getDisplay(), lt.getSecondaryColor());
		if (bg == null) {
			bg = g.getDevice().getSystemColor(SWT.COLOR_GRAY);
		}
		g.setBackground(bg);

		//Fill the bg
		g.fillRectangle(p.x - 2, p.y - 2, textExtent.x + 4, textExtent.y + 4);

		Color fg = ResourceUtils.getColor(getDisplay(), lt.getColor());
		if (fg == null) {
			fg = g.getDevice().getSystemColor(SWT.COLOR_BLACK);
		}
		g.setForeground(fg);

		//Draw the text
		g.drawText(text, p.x, p.y, true);
	}

	public boolean isInThing(IBNAView view, int worldX, int worldY) {
		return false;
	}

}
