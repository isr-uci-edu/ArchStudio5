package org.archstudio.bna.things.labels;

import java.awt.Font;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.Resources;
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.things.AbstractAnchorPointThingPeer;
import org.archstudio.bna.utils.TextUtils;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class AnchoredLabelThingPeer<T extends AnchoredLabelThing> extends AbstractAnchorPointThingPeer<T> {

	Shape lastTextLocalShape = null;
	int SPACING = 2;

	public AnchoredLabelThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	protected static int calculateFontSize(ICoordinateMapper cm, int origFontSize, boolean dontIncreaseFontSize) {
		double scale = cm.getLocalScale();
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
	public void draw(GL2 gl, Rectangle localBounds, Resources r) {
		if ("".equals(t.getText().trim())) {
			return;
		}

		lastTextLocalShape = null;
		if (r.setColor(t, IHasColor.COLOR_KEY)) {
			String text = t.getText();
			Point lap = cm.worldToLocal(t.getAnchorPoint());
			Point ip = t.getIndicatorPoint();
			Point lip = ip != null ? cm.worldToLocal(ip) : null;
			int fontSize = calculateFontSize(view.getCoordinateMapper(), t.getFontSize(), t.getDontIncreaseFontSize());
			Font font = r.getFont(t, fontSize);
			int angle = t.getAngle();

			TextUtils textUtils = r.getTextUtils();
			lastTextLocalShape = font.getStringBounds(text, textUtils.getFontRenderContext());
			float descent = font.getLineMetrics(text, textUtils.getFontRenderContext()).getDescent();
			Rectangle2D bounds = lastTextLocalShape.getBounds2D();

			double offsetX = 0;
			switch (t.getHorizontalAlignment()) {
			case LEFT:
				offsetX -= bounds.getWidth() / 2;
				break;
			case CENTER:
				break;
			case RIGHT:
				offsetX += bounds.getWidth() / 2;
				break;
			}

			double offsetY = 0;
			switch (t.getVerticalAlignment()) {
			case TOP:
				offsetY -= bounds.getHeight() / 2;
				break;
			case MIDDLE:
				break;
			case BOTTOM:
				offsetY += bounds.getHeight() / 2;
				break;
			}

			GeneralPath path = new GeneralPath(lastTextLocalShape);
			path.transform(AffineTransform.getTranslateInstance(-bounds.getWidth() / 2 + offsetX, bounds.getHeight()
					/ 2 + offsetY - descent));
			Rectangle2D pathR = path.getBounds2D();
			Point2D lap2d1 = new Point2D.Double(pathR.getMinX() - SPACING, pathR.getCenterY());
			Point2D lap2d2 = new Point2D.Double(pathR.getMaxX() + SPACING, pathR.getCenterY());

			AffineTransform transform = new AffineTransform();
			transform.translate(lap.x, lap.y);
			transform.rotate(Math.PI * angle / 180);
			path.transform(transform);
			lastTextLocalShape = path;

			if (lip != null && r.setLineStyle(t)) {
				transform.transform(lap2d1, lap2d1);
				transform.transform(lap2d2, lap2d2);
				double dist1 = Point2D.distance(lap2d1.getX(), lap2d1.getY(), lip.x, lip.y);
				double dist2 = Point2D.distance(lap2d2.getX(), lap2d2.getY(), lip.x, lip.y);
				gl.glBegin(GL.GL_LINE_STRIP);
				if (dist1 < dist2) {
					gl.glVertex2d(lap2d1.getX() + 0.5d, localBounds.height - lap2d1.getY() + 0.5d);
				}
				else {
					gl.glVertex2d(lap2d2.getX() + 0.5d, localBounds.height - lap2d2.getY() + 0.5d);
				}
				gl.glVertex2i(lip.x, localBounds.height - lip.y);
				gl.glEnd();
				r.resetLineStyle();
			}

			gl.glPushMatrix();
			gl.glTranslated(lap.x, localBounds.height - lap.y, 0);
			gl.glRotated(-angle, 0, 0, 1);
			textUtils.beginRendering();
			textUtils.draw(font, text, (int) (-bounds.getWidth() / 2 + offsetX), (int) (-bounds.getHeight() / 2
					- offsetY + descent));
			textUtils.endRendering(gl, localBounds);
			gl.glPopMatrix();
		}
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
