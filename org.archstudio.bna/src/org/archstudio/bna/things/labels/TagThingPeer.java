package org.archstudio.bna.things.labels;

import java.awt.geom.Point2D;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Region;
import org.eclipse.swt.graphics.Transform;
import org.eclipse.swt.widgets.Display;

import org.archstudio.bna.AbstractThingPeer;
import org.archstudio.bna.BNAUtils;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IThing;
import org.archstudio.bna.ResourceUtils;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.swtutils.constants.FontStyle;

public class TagThingPeer extends AbstractThingPeer {
	protected TagThing lt;

	public TagThingPeer(IThing t) {
		super(t);
		if (!(t instanceof TagThing)) {
			throw new IllegalArgumentException("TagThingPeer can only peer for TagThing");
		}
		this.lt = (TagThing) t;
	}

	protected Rectangle localBoundingBox = new Rectangle(0, 0, 0, 0);

	protected void updateLocalBoundingBox(ICoordinateMapper cm) {
		localBoundingBox = BNAUtils.worldToLocal(cm, BNAUtils.normalizeRectangle(lt.getBoundingBox()));
	}

	protected static int calculateFontSize(ICoordinateMapper cm, int origFontSize, boolean dontIncreaseFontSize) {
		double scale = cm.getScale();
		double nfs = (double) origFontSize;
		if (dontIncreaseFontSize) {
			if (scale < 1.0d) {
				nfs = (double) origFontSize * scale;
			}
		}
		else {
			nfs = (double) origFontSize * scale;
		}
		return (int) nfs;
	}

	protected int[] lastTextShape = new int[8];

	public static void computeTextShape(Point textExtent, Transform t, int[] dest) {
		if (dest.length != 8) {
			throw new IllegalArgumentException("This shouldn't happen.");
		}

		float[] coords = new float[] { 0, 0, textExtent.x, 0, textExtent.x, textExtent.y, 0, textExtent.y };
		t.transform(coords);
		for (int i = 0; i < 8; i++) {
			dest[i] = Math.round(coords[i]);
		}
	}

	public void draw(IBNAView view, GC g) {
		for (int i = 0; i < 8; i++)
			lastTextShape[i] = Integer.MIN_VALUE;

		boolean visible = lt.isVisible();
		String text = lt.getText();

		if (!visible)
			return;
		if ((text == null) || (text.length() == 0)) {
			return;
		}
		text = ' ' + text + ' ';

		Point ap = lt.getAnchorPoint();
		Point ip = lt.getIndicatorPoint();

		Color fg = ResourceUtils.getColor(getDisplay(), lt.getColor());
		if (fg == null) {
			fg = getDisplay().getSystemColor(SWT.COLOR_BLACK);
		}

		String fontName = lt.getFontName();
		int fontSize = lt.getFontSize();
		FontStyle fontStyle = lt.getFontStyle();
		boolean dontIncreaseFontSize = lt.getDontIncreaseFontSize();

		int angle = lt.getAngle();

		Point lap = BNAUtils.worldToLocal(view.getCoordinateMapper(), ap);
		Point lip = (ip == null) ? null : BNAUtils.worldToLocal(view.getCoordinateMapper(), ip);

		if (lip != null) {
			if ((!g.getClipping().contains(lap)) && (!g.getClipping().contains(lip)))
				return;
		}
		else {
			if (!g.getClipping().contains(lap))
				return;
		}

		g.setForeground(fg);

		Display d = getDisplay();
		int nfs = calculateFontSize(view.getCoordinateMapper(), fontSize, dontIncreaseFontSize);
		Font f = ResourceUtils.getFont(d, fontName, (int) nfs, fontStyle);
		g.setFont(f);

		Point textExtent = g.textExtent(text);

		int textX = lap.x;
		int textY = lap.y - (textExtent.y / 2);

		if (angle != 0) {
			Transform t = new Transform(d);
			t.translate(textX, textY);
			t.rotate((float) angle);
			g.setTransform(t);

			g.drawText(text, 0, 0, true);
			g.setTransform(null);

			computeTextShape(textExtent, t, lastTextShape);

			ICoordinateMapper cm = view.getCoordinateMapper();
			Rectangle boundingBox = getBounds(cm, lastTextShape);

			if (lip != null) {
				float[] coords = new float[] { 0, textExtent.y / 2, textExtent.x, textExtent.y / 2 };
				t.transform(coords);

				double dist1 = Point2D.distance(coords[0], coords[1], lip.x, lip.y);
				double dist2 = Point2D.distance(coords[2], coords[3], lip.x, lip.y);
				if (dist1 < dist2) {
					g.drawLine(Math.round(coords[0]), Math.round(coords[1]), lip.x, lip.y);
				}
				else {
					g.drawLine(Math.round(coords[2]), Math.round(coords[3]), lip.x, lip.y);
				}
				boundingBox.add(new Rectangle(cm.localXtoWorldX(lip.x), cm.localYtoWorldY(lip.y), 1, 1));
			}
			lt.setProperty("#" + IHasBoundingBox.BOUNDING_BOX_PROPERTY_NAME, boundingBox);

			t.dispose();
		}
		else {
			lastTextShape[0] = textX;
			lastTextShape[1] = textY;
			lastTextShape[2] = textX + textExtent.x;
			lastTextShape[3] = textY;
			lastTextShape[4] = textX + textExtent.x;
			lastTextShape[5] = textY + textExtent.y;
			lastTextShape[6] = textX;
			lastTextShape[7] = textY + textExtent.y;

			g.drawText(text, textX, textY, true);

			ICoordinateMapper cm = view.getCoordinateMapper();
			Rectangle boundingBox = new Rectangle(cm.localXtoWorldX(lastTextShape[0]), cm.localXtoWorldX(lastTextShape[1]), textExtent.x, textExtent.y);

			if (lip != null) {
				double dist1 = Point2D.distance(lap.x, lap.y, lip.x, lip.y);
				double dist2 = Point2D.distance(lap.x + textExtent.x, lap.y, lip.x, lip.y);
				if (dist1 < dist2) {
					g.drawLine(lap.x, lap.y, lip.x, lip.y);
				}
				else {
					g.drawLine(lap.x + textExtent.x, lap.y, lip.x, lip.y);
				}
				boundingBox.add(new Rectangle(cm.localXtoWorldX(lip.x), cm.localYtoWorldY(lip.y), 1, 1));
			}
			lt.setProperty("#" + IHasBoundingBox.BOUNDING_BOX_PROPERTY_NAME, boundingBox);
		}

	}

	public boolean isInThing(IBNAView view, int worldX, int worldY) {
		boolean visible = lt.isVisible();
		if (!visible)
			return false;

		String text = lt.getText();
		if ((text == null) || (text.length() == 0)) {
			return false;
		}
		text = ' ' + text + ' ';

		if (lastTextShape[0] == Integer.MIN_VALUE) {
			return false;
		}

		if (lt.getAngle() == 0) {
			Rectangle r = new Rectangle(lastTextShape[0], lastTextShape[1], lastTextShape[2] - lastTextShape[0], lastTextShape[5] - lastTextShape[1]);
			return r.contains(view.getCoordinateMapper().worldXtoLocalX(worldX), view.getCoordinateMapper().worldYtoLocalY(worldY));
		}

		Region r = new Region(getDisplay());
		r.add(lastTextShape);
		boolean c = r.contains(view.getCoordinateMapper().worldXtoLocalX(worldX), view.getCoordinateMapper().worldYtoLocalY(worldY));
		r.dispose();

		return c;
	}

	protected static Rectangle getBounds(ICoordinateMapper cm, int[] coords) {
		int minX = Integer.MAX_VALUE;
		int minY = Integer.MAX_VALUE;
		int maxX = Integer.MIN_VALUE;
		int maxY = Integer.MIN_VALUE;

		for (int i = 0; i < coords.length; i += 2) {
			int x = cm.localXtoWorldX(coords[i]);
			int y = cm.localYtoWorldY(coords[i + 1]);

			if (x < minX)
				minX = x;
			if (x > maxX)
				maxX = x;
			if (y < minY)
				minY = y;
			if (y > maxY)
				maxY = y;
		}
		return new Rectangle(minX, minY, maxX - minX, maxY - minY);
	}
}
