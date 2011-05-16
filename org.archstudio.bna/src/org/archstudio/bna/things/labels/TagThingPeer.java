package org.archstudio.bna.things.labels;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.things.AbstractThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.swtutils.constants.FontStyle;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.TextLayout;

public class TagThingPeer<T extends TagThing> extends AbstractThingPeer<T> {

	public TagThingPeer(T thing) {
		super(thing);
	}

	Shape lastTextLocalShape = null;

	protected static int calculateFontSize(ICoordinateMapper cm, int origFontSize, boolean dontIncreaseFontSize) {
		double scale = cm.getScale();
		double nfs = origFontSize;
		if (dontIncreaseFontSize) {
			if (scale < 1.0d) {
				nfs = origFontSize * scale;
			}
		}
		else {
			nfs = origFontSize * scale;
		}
		return (int) nfs;
	}

	@Override
	public void draw(IBNAView view, Graphics g, Rectangle clip, ResourceUtils res) {
		Point ap = t.getAnchorPoint();
		Point ip = t.getIndicatorPoint();
		Point lap = BNAUtils.worldToLocal(view.getCoordinateMapper(), ap);
		Point lip = ip == null ? null : BNAUtils.worldToLocal(view.getCoordinateMapper(), ip);
		lastTextLocalShape = null;

		if (!clip.contains(lap) && (lip == null || !clip.contains(lip))) {
			return;
		}

		String text = t.getText();
		if (text == null || text.trim().length() == 0) {
			return;
		}
		text = ' ' + text + ' ';

		boolean dontIncreaseFontSize = t.getDontIncreaseFontSize();
		int fontSize = calculateFontSize(view.getCoordinateMapper(), t.getFontSize(), dontIncreaseFontSize);
		String fontName = t.getFontName();
		FontStyle fontStyle = t.getFontStyle();
		int angle = t.getAngle();

		g.setForegroundColor(res.getColor(t.getColor(), SWT.COLOR_BLACK));
		Font font = res.getFont(fontName, fontSize, fontStyle.toSWT());
		g.setFont(font);

		TextLayout tl = null;

		try {
			tl = new TextLayout(res.getDevice());
			tl.setFont(font);
			tl.setText(text);
			Rectangle textBounds = BNAUtils.toRectangle(tl.getBounds());

			int textX = lap.x;
			int textY = lap.y;

			if (angle == 0) {
				lastTextLocalShape = new Rectangle2D.Float(textX, textY, textBounds.width, textBounds.height);

				if (lip != null) {
					double dist1 = Point2D.distance(lap.x, lap.y, lip.x, lip.y);
					double dist2 = Point2D.distance(lap.x + textBounds.width, lap.y, lip.x, lip.y);
					if (dist1 < dist2) {
						g.drawLine(lap.x, lap.y, lip.x, lip.y);
					}
					else {
						g.drawLine(lap.x + textBounds.width, lap.y, lip.x, lip.y);
					}
				}

				g.drawString(text, textX, textY);
			}
			else {
				AffineTransform transform = new AffineTransform();
				transform.translate(textX, textY);
				transform.rotate(angle);
				GeneralPath path = new GeneralPath(new Rectangle2D.Float(textX, textY, textBounds.width,
						textBounds.height));
				path.transform(transform);
				lastTextLocalShape = path;

				if (lip != null) {
					Point2D lap2d = new Point2D.Float(lap.x, lap.y);
					Point2D lap2d2 = new Point2D.Float(lap.x + textBounds.width, lap.y);
					transform.transform(lap2d, lap2d);
					transform.transform(lap2d2, lap2d2);
					double dist1 = Point2D.distance(lap2d.getX(), lap2d.getY(), lip.x, lip.y);
					double dist2 = Point2D.distance(lap2d2.getX(), lap2d2.getY(), lip.x, lip.y);
					if (dist1 < dist2) {
						g.drawLine(BNAUtils.round(lap2d.getX()), BNAUtils.round(lap2d.getY()), lip.x, lip.y);
					}
					else {
						g.drawLine(BNAUtils.round(lap2d2.getX()), BNAUtils.round(lap2d2.getY()), lip.x, lip.y);
					}
				}

				g.translate(textX, textY);
				g.rotate(angle);
				g.drawString(text, 0, 0);
			}
		}
		finally {
			if (tl != null) {
				tl.dispose();
				tl = null;
			}
		}
	}

	@Override
	public boolean isInThing(IBNAView view, int worldX, int worldY) {
		if (lastTextLocalShape != null) {
			return lastTextLocalShape.contains(worldX, worldY);
		}
		return false;
	}
}
