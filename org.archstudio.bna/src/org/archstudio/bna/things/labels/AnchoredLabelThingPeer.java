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
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.facets.peers.IHasLocalBounds;
import org.archstudio.bna.things.AbstractAnchorPointThingPeer;
import org.archstudio.bna.ui.IUIResources;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class AnchoredLabelThingPeer<T extends AnchoredLabelThing> extends AbstractAnchorPointThingPeer<T> implements
		IHasLocalBounds {

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

		if (r.setColor(t, IHasColor.COLOR_KEY)) {

			Point ap = t.getAnchorPoint();
			Point lap = cm.worldToLocal(ap);
			Point ip = t.getIndicatorPoint();
			Point lip = ip != null ? cm.worldToLocal(ip) : null;
			Font font = r.getFont(t, t.getFontSize());
			Font lfont = r.getFont(t, (int) (t.getFontSize() * cm.getLocalScale()));
			int angle = t.getAngle();

			Dimension size = r.getTextSize(font, text);
			Dimension lsize = r.getTextSize(lfont, text);

			double offsetX = -size.width / 2;
			double loffsetX = -lsize.width / 2;
			switch (t.getHorizontalAlignment()) {
			case LEFT:
				offsetX -= size.width / 2;
				loffsetX -= lsize.width / 2;
				break;
			case CENTER:
				break;
			case RIGHT:
				offsetX += size.width / 2;
				loffsetX += lsize.width / 2;
				break;
			}

			double offsetY = -size.height / 2;
			double loffsetY = -lsize.height / 2;
			switch (t.getVerticalAlignment()) {
			case TOP:
				offsetY -= size.height / 2;
				loffsetY -= lsize.height / 2;
				break;
			case MIDDLE:
				break;
			case BOTTOM:
				offsetY += size.height / 2;
				loffsetY += lsize.height / 2;
				break;
			}

			Rectangle2D bounds = new Rectangle2D.Double(offsetX, offsetY, size.width, size.height);
			Rectangle2D lbounds = new Rectangle2D.Double(loffsetX, loffsetY, lsize.width, lsize.height);

			AffineTransform transform = new AffineTransform();
			transform.translate(ap.x, ap.y);
			transform.rotate(Math.PI * angle / 180);
			Path2D boundsPath = new Path2D.Double(bounds);
			boundsPath.transform(transform);
			t.set(IHasBoundingBox.BOUNDING_BOX_KEY, BNAUtils.toRectangle(boundsPath.getBounds()));

			AffineTransform lTransform = new AffineTransform();
			lTransform.translate(lap.x, lap.y);
			lTransform.rotate(Math.PI * angle / 180);
			Path2D lBoundsPath = new Path2D.Double(lbounds);
			lBoundsPath.transform(lTransform);
			lastTextLocalShape = lBoundsPath;

			r.pushMatrix(lap.x, lap.y, Math.PI * angle / 180);
			try {
				r.setColor(t, IHasColor.COLOR_KEY);
				r.drawText(lfont, text, loffsetX, loffsetY);
			}
			finally {
				r.popMatrix();
			}

			if (lip != null && r.setLineStyle(t)) {
				Point2D lap2d1 = new Point2D.Double(lbounds.getMinX() - SPACING, lbounds.getCenterY());
				Point2D lap2d2 = new Point2D.Double(lbounds.getMaxX() + SPACING, lbounds.getCenterY());
				Point2D lip2D = BNAUtils.toPoint2D(lip);
				lTransform.transform(lap2d1, lap2d1);
				lTransform.transform(lap2d2, lap2d2);
				double dist1 = lap2d1.distance(lip2D);
				double dist2 = lap2d2.distance(lip2D);
				Point2D fromPoint = dist1 < dist2 ? lap2d1 : lap2d2;
				Line2D.Double line = new Line2D.Double(fromPoint, lip2D);
				r.drawShape(line);
				r.resetLineStyle();
			}
		}

		return true;
	}

	@Override
	public Rectangle getLocalBounds() {
		if (lastTextLocalShape != null) {
			return BNAUtils.toRectangle(lastTextLocalShape.getBounds2D());
		}
		return null;
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
