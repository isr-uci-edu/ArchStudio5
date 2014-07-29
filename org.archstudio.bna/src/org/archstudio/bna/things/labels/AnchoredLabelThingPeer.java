package org.archstudio.bna.things.labels;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.things.AbstractThingPeer;
import org.archstudio.bna.ui.IUIResources;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.swtutils.constants.HorizontalAlignment;
import org.archstudio.swtutils.constants.LineStyle;
import org.archstudio.swtutils.constants.VerticalAlignment;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

public class AnchoredLabelThingPeer<T extends AnchoredLabelThing> extends AbstractThingPeer<T> {

	private static final boolean DEBUG = false;

	Shape lastTextLocalShape = null;
	int SPACING = 4;

	public AnchoredLabelThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	@Override
	public boolean draw(Rectangle localBounds, IUIResources r) {
		String text = t.getText().trim();
		if (text.length() == 0) {
			return false;
		}

		int angle = t.getRawAngle();
		HorizontalAlignment horizontal = t.getRawHorizontalAlignment();
		VerticalAlignment vertical = t.getRawVerticalAlignment();

		Point2D ap = t.getRawAnchorPoint();
		Point2D ip = t.getRawIndicatorPoint();
		Font font = r.getFont(t.getRawFontName(), t.getRawFontStyle(), t.getRawFontSize());
		Dimension size = r.getTextSize(font, text);
		double offsetX = -size.width / 2d + horizontal.getDelta() * (size.width / 2d);
		double offsetY = -size.height / 2d + vertical.getDelta() * (size.height / 2d);
		offsetY -= r.getFontMetrics(font).getLeading();

		Rectangle2D bounds = new Rectangle2D.Double(offsetX, offsetY, size.width, size.height);
		AffineTransform transform = new AffineTransform();
		transform.translate(ap.getX(), ap.getY());
		transform.rotate(Math.PI * angle / 180);
		Path2D boundsPath = new Path2D.Double(bounds);
		boundsPath.transform(transform);
		t.setRawBoundingBox(BNAUtils.toRectangle(boundsPath.getBounds()));

		RGB color = t.getRawColor();
		if (color != null) {

			Point2D lap = cm.worldToLocal(ap);
			Point2D lip = ip != null ? cm.worldToLocal(ip) : null;
			int lfontsize = (int) (t.getRawFontSize() * cm.getLocalScale());
			Font lfont = lfontsize > 0 ? r.getFont(t.getRawFontName(), t.getRawFontStyle(), lfontsize) : null;
			Dimension lsize = lfont == null ? new Dimension(0, 0) : r.getTextSize(lfont, text);
			double loffsetX = -lsize.width / 2d + horizontal.getDelta() * (lsize.width / 2d);
			double loffsetY = -lsize.height / 2d + vertical.getDelta() * (lsize.height / 2d);
			loffsetY -= lfont == null ? 0 : r.getFontMetrics(lfont).getLeading();

			if (lfont != null) {
				Rectangle2D lbounds = new Rectangle2D.Double(loffsetX, loffsetY, lsize.width, lsize.height);
				AffineTransform lTransform = new AffineTransform();
				lTransform.translate(lap.getX(), lap.getY());
				lTransform.rotate(Math.PI * angle / 180);
				Path2D lBoundsPath = new Path2D.Double(lbounds);
				lBoundsPath.transform(lTransform);
				lastTextLocalShape = lBoundsPath;
				if (DEBUG) {
					r.drawShape(lastTextLocalShape, new RGB(0, 0, 0), 1, LineStyle.SOLID, 1);
				}

				r.pushMatrix(lap.getX(), lap.getY(), Math.PI * angle / 180);
				try {
					r.drawText(lfont, text, loffsetX, loffsetY, t.getRawColor(), 1);
				}
				finally {
					r.popMatrix();
				}

				RGB edgeColor = t.getRawEdgeColor();
				if (lip != null && edgeColor != null) {
					double spacing = SPACING * cm.getLocalScale();
					Point2D lap2d1 = new Point2D.Double(lbounds.getMinX() - spacing, lbounds.getCenterY());
					Point2D lap2d2 = new Point2D.Double(lbounds.getMaxX() + spacing, lbounds.getCenterY());
					Point2D lip2D = lip;
					lTransform.transform(lap2d1, lap2d1);
					lTransform.transform(lap2d2, lap2d2);
					double dist1 = lap2d1.distance(lip2D);
					double dist2 = lap2d2.distance(lip2D);
					Point2D fromPoint = dist1 < dist2 ? lap2d1 : lap2d2;
					Line2D.Double line = new Line2D.Double(fromPoint, lip2D);
					r.drawShape(line, edgeColor, t.getRawLineWidth(), t.getRawLineStyle(), 1);
				}
			}
			else {
				lastTextLocalShape = new Rectangle2D.Double(lap.getX() - 2, lap.getY() - 2, 4, 4);
				if (lip != null) {
					Line2D.Double line = new Line2D.Double(lip, lap);
					r.drawShape(line, t.getRawEdgeColor(), t.getRawLineWidth(), t.getRawLineStyle(), 1);
				}
			}
		}

		return true;
	}

	@Override
	public boolean isInThing(ICoordinate location) {
		if (lastTextLocalShape != null) {
			Point lPoint = location.getLocalPoint();
			return lastTextLocalShape.contains(lPoint.x, lPoint.y);
		}
		return false;
	}
}
