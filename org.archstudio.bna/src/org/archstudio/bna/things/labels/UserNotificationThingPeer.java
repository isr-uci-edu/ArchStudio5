package org.archstudio.bna.things.labels;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.things.AbstractThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.swtutils.constants.FontStyle;
import org.archstudio.swtutils.constants.HorizontalAlignment;
import org.archstudio.swtutils.constants.VerticalAlignment;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.TextLayout;

public class UserNotificationThingPeer<T extends UserNotificationThing> extends AbstractThingPeer<T> {

	class C {
		int textWidth;
		int textHeight;
	}

	public UserNotificationThingPeer(T thing) {
		super(thing);
	}

	private Rectangle getBasicLocalRectangle(IBNAView view, ICoordinateMapper cm, IResources r) {
		String text = t.getText();
		if (text == null || text.trim().length() == 0) {
			return null;
		}

		Point lap = cm.worldToLocal(t.getAnchorPoint());

		String fontName = t.getFontName();
		int fontSize = t.getFontSize();
		FontStyle fontStyle = t.getFontStyle();

		VerticalAlignment verticalAlignment = t.getVerticalAlignment();
		HorizontalAlignment horizontalAlignment = t.getHorizontalAlignment();

		TextLayout tl = null;
		tl = new TextLayout(r.getDevice());
		tl.setText(text);
		tl.setFont(r.getFont(fontName, fontSize, fontStyle));

		Rectangle textExtent = BNAUtils.toRectangle(tl.getBounds());

		int textWidth = textExtent.width;
		int textHeight = textExtent.height;

		switch (horizontalAlignment) {
		case CENTER:
			lap.x = lap.x - textWidth / 2;
			break;
		case RIGHT:
			lap.x = lap.x - textWidth;
			break;
		}

		switch (verticalAlignment) {
		case MIDDLE:
			lap.y = lap.y - textHeight / 2;
			break;
		case BOTTOM:
			lap.y = lap.y - textHeight;
			break;
		}

		if (tl != null) {
			tl.dispose();
		}

		return new Rectangle(lap.x, lap.y, textWidth, textHeight);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, Graphics g, IResources r) {
		String text = t.getText();
		if (text == null || text.trim().length() == 0) {
			return;
		}

		Rectangle basicLocalRectangle = getBasicLocalRectangle(view, cm, r);

		String fontName = t.getFontName();
		int fontSize = t.getFontSize();
		FontStyle fontStyle = t.getFontStyle();

		TextLayout tl = null;
		try {
			tl = new TextLayout(r.getDevice());
			tl.setText(text);
			tl.setFont(r.getFont(fontName, fontSize, fontStyle));

			//Life drops from 16 to 0
			int life = t.getLife();

			//Step goes from 0 to 8 back to 0
			int step = 8 - Math.abs(life - 8);
			int alpha = step * 64;
			if (alpha > 255) {
				alpha = 255;
			}
			g.setAlpha(alpha);

			//Draw a little shadow
			g.setForegroundColor(r.getColor(SWT.COLOR_DARK_GRAY));
			g.drawLine(basicLocalRectangle.x - 1, basicLocalRectangle.y - 1 + basicLocalRectangle.height + 4,
					basicLocalRectangle.x - 1 + basicLocalRectangle.width + 4, basicLocalRectangle.y - 1
							+ basicLocalRectangle.height + 4);
			g.drawLine(basicLocalRectangle.x - 1 + basicLocalRectangle.width + 4, basicLocalRectangle.y - 1,
					basicLocalRectangle.x - 1 + basicLocalRectangle.width + 4, basicLocalRectangle.y - 1
							+ basicLocalRectangle.height + 4);

			RGB secondaryColorRGB = t.getSecondaryColor();
			Color bg = secondaryColorRGB != null ? r.getColor(secondaryColorRGB) : r.getColor(SWT.COLOR_GRAY);
			g.setBackgroundColor(bg);
			g.fillRectangle(basicLocalRectangle.x - 2, basicLocalRectangle.y - 2, basicLocalRectangle.width + 4,
					basicLocalRectangle.height + 4);

			RGB primaryColorRGB = t.getColor();
			Color fg = primaryColorRGB != null ? r.getColor(primaryColorRGB) : r.getColor(SWT.COLOR_BLACK);
			g.setForegroundColor(fg);
			g.drawText(text, basicLocalRectangle.x, basicLocalRectangle.y);

		}
		finally {
			if (tl != null) {
				tl.dispose();
				tl = null;
			}
		}
	}

	@Override
	public boolean isInThing(IBNAView view, ICoordinateMapper cm, ICoordinate location) {
		return false;
	}

	@Override
	public void getLocalBounds(IBNAView view, ICoordinateMapper cm, IResources r, Rectangle boundsResult) {
		Rectangle basicLocalRectangle = getBasicLocalRectangle(view, cm, r);
		boundsResult.setBounds(basicLocalRectangle.expand(5, 5));
	}

}
