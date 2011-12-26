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
import org.archstudio.bna.IResources;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.things.AbstractThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

import com.jogamp.opengl.util.awt.TextRenderer;

public class TagThingPeer<T extends TagThing> extends AbstractThingPeer<T> implements IThingPeer<T> {

	private static final int SPACING = 4;

	public TagThingPeer(T thing) {
		super(thing);
	}

	Shape lastTextLocalShape = null;

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
	public void draw(IBNAView view, ICoordinateMapper cm, GL2 gl, Rectangle clip, IResources r) {
		lastTextLocalShape = null;
		if (r.setColor(t, IHasColor.COLOR_KEY)) {
			String text = t.getText();
			Point lap = cm.worldToLocal(t.getAnchorPoint());
			Point ip = t.getIndicatorPoint();
			Point lip = ip != null ? cm.worldToLocal(ip) : null;
			int fontSize = calculateFontSize(view.getCoordinateMapper(), t.getFontSize(), t.getDontIncreaseFontSize());
			Font font = r.getFont(t, fontSize);
			int angle = t.getAngle();

			TextRenderer tr = r.getTextRenderer(font);
			Point canvasSize = view.getComposite().getSize();
			int textX = lap.x;
			int textY = lap.y;
			Rectangle textBounds = BNAUtils.toRectangle(tr.getBounds(text));
			lastTextLocalShape = new Rectangle2D.Float(textBounds.x, textBounds.y, textBounds.width, textBounds.height);

			AffineTransform transform = new AffineTransform();
			transform.translate(textX, textY);
			transform.rotate(Math.PI * angle / 180);
			GeneralPath path = new GeneralPath(lastTextLocalShape);
			path.transform(transform);
			lastTextLocalShape = path;

			if (lip != null) {
				Point2D lap2d = new Point2D.Float(0, 0);
				Point2D lap2d2 = new Point2D.Float(textBounds.width + SPACING, 0);
				transform.transform(lap2d, lap2d);
				transform.transform(lap2d2, lap2d2);
				double dist1 = Point2D.distance(lap2d.getX(), lap2d.getY(), lip.x, lip.y);
				double dist2 = Point2D.distance(lap2d2.getX(), lap2d2.getY(), lip.x, lip.y);
				gl.glBegin(GL.GL_LINE_STRIP);
				if (dist1 < dist2) {
					gl.glVertex2d(lap2d.getX() + 0.5d, lap2d.getY() + 0.5d);
				}
				else {
					gl.glVertex2d(lap2d2.getX() + 0.5d, lap2d2.getY() + 0.5d);
				}
				gl.glVertex2i(lip.x, lip.y);
				gl.glEnd();
			}

			tr.beginRendering(canvasSize.x, canvasSize.y);
			gl.glMatrixMode(GL2.GL_MODELVIEW);
			gl.glTranslated(textX, canvasSize.y - textY, 0);
			gl.glRotated(-angle, 0, 0, 1);
			r.setColor(t, IHasColor.COLOR_KEY);
			tr.draw3D(text, 4, 0, 0, 1);
			tr.endRendering();
		}
	}

	@Override
	public boolean isInThing(IBNAView view, ICoordinateMapper cm, ICoordinate location) {
		if (lastTextLocalShape != null) {
			Point lPoint = location.getLocalPoint();
			return lastTextLocalShape.contains(lPoint.x, lPoint.y);
		}
		return false;
	}

	@Override
	public Rectangle getLocalBounds(IBNAView view, ICoordinateMapper cm) {
		Rectangle bounds;
		if (lastTextLocalShape == null) {
			bounds = new Rectangle(0, 0, 0, 0);
			bounds.x = bounds.y = Integer.MIN_VALUE / 2;
			bounds.width = bounds.height = Integer.MAX_VALUE;
		}
		else {
			bounds = BNAUtils.toRectangle(lastTextLocalShape.getBounds());
		}

		Point lap = cm.worldToLocal(t.getAnchorPoint());
		bounds.add(new Rectangle(lap.x, lap.y, 1, 1));

		Point ip = t.getIndicatorPoint();
		Point lip = ip != null ? cm.worldToLocal(ip) : null;
		if (lip != null) {
			bounds.add(new Rectangle(lip.x, lip.y, 1, 1));
		}
		return bounds;
	}
}
