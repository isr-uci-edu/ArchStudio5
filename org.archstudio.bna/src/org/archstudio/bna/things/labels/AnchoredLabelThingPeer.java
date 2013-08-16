package org.archstudio.bna.things.labels;

import java.awt.Font;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.fixedfunc.GLMatrixFunc;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.things.AbstractAnchorPointThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

import com.jogamp.opengl.util.awt.TextRenderer;

public class AnchoredLabelThingPeer<T extends AnchoredLabelThing> extends AbstractAnchorPointThingPeer<T> implements
		IThingPeer<T> {

	Shape lastTextLocalShape = null;
	int SPACING = 2;

	public AnchoredLabelThingPeer(T thing) {
		super(thing);
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
	public void draw(IBNAView view, ICoordinateMapper cm, GL2 gl, Rectangle clip, IResources r) {
		if ("".equals(t.getText().trim()))
			return;

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
			Point canvasSize = new Point(view.getComposite().getClientArea().width,
					view.getComposite().getClientArea().height);
			Rectangle2D size = tr.getBounds(t.getText());

			int textX = lap.x;
			switch (t.getHorizontalAlignment()) {
			case LEFT:
				//textX += offset * cm.getLocalScale();
				break;
			case CENTER:
				textX -= size.getWidth() / 2;
				break;
			case RIGHT:
				textX -= size.getWidth();
				//textX -= offset * cm.getLocalScale();
				break;
			}
			int textY = lap.y + fontSize;
			switch (t.getVerticalAlignment()) {
			case TOP:
				break;
			case MIDDLE:
				textY -= size.getHeight() / 2;
				break;
			case BOTTOM:
				textY -= size.getHeight();
				break;
			}
			lastTextLocalShape = tr.getBounds(text);
			Rectangle textBounds = BNAUtils.toRectangle(tr.getBounds(text));

			AffineTransform transform = new AffineTransform();
			transform.translate(textX, textY);
			transform.rotate(Math.PI * angle / 180);
			GeneralPath path = new GeneralPath(lastTextLocalShape);
			path.transform(transform);
			lastTextLocalShape = path;

			if (lip != null && r.setLineStyle(t)) {
				Point2D lap2d = new Point2D.Float(-SPACING, 0);
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

			gl.glPushMatrix();
			tr.beginRendering(canvasSize.x, canvasSize.y);
			gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
			gl.glTranslated(textX, canvasSize.y - textY, 0);
			gl.glRotated(-angle, 0, 0, 1);
			r.setColor(t, IHasColor.COLOR_KEY, tr);
			tr.draw(text, 0, 0);
			tr.endRendering();
			gl.glPopMatrix();
		}

		//		Point canvasSize = view.getComposite().getSize();
		//		String text = t.getText();
		//		TextRenderer tr = r.getTextRenderer(r.getFont(t));
		//		Rectangle2D rectangleSize = tr.getBounds(text);
		//		Dimension size = new Dimension(BNAUtils.round(rectangleSize.getWidth()), BNAUtils.round(rectangleSize
		//				.getHeight()));
		//		Point localAnchor = cm.worldToLocal(t.getAnchorPoint());
		//		Point ip = t.getIndicatorPoint();
		//		Point lip = ip != null ? cm.worldToLocal(ip) : null;
		//		Point localOffset = new Point(0, 0);
		//		int offset = 0;
		//
		//		HorizontalAlignment horizontalAlignment = t.getHorizontalAlignment();
		//		switch (horizontalAlignment) {
		//		case LEFT:
		//			localOffset.x += offset * cm.getLocalScale();
		//			break;
		//		case CENTER:
		//			localOffset.x -= size.width / 2;
		//			break;
		//		case RIGHT:
		//			localOffset.x -= size.width;
		//			localOffset.x -= offset * cm.getLocalScale();
		//			break;
		//		}
		//		switch (t.getVerticalAlignment()) {
		//		case TOP:
		//			break;
		//		case MIDDLE:
		//			localOffset.y -= size.height / 2;
		//			break;
		//		case BOTTOM:
		//			localOffset.y -= size.height;
		//			break;
		//		}
		//
		//		tr.beginRendering(canvasSize.x, canvasSize.y);
		//		if (r.setColor(t, IHasColor.COLOR_KEY)) {
		//			gl.glMatrixMode(GL2.GL_MODELVIEW);
		//			gl.glTranslatef(localAnchor.x, canvasSize.y - localAnchor.y, 0);
		//			gl.glRotatef(t.getAngle(), 0, 0, 1);
		//			tr.draw(text, 0, 0);
		//		}
		//		tr.endRendering();
	}

	@Override
	public boolean isInThing(IBNAView view, ICoordinateMapper cm, ICoordinate location) {
		if (lastTextLocalShape != null) {
			Point lPoint = location.getLocalPoint();
			return lastTextLocalShape.contains(lPoint.x, lPoint.y);
		}
		return false;
	}
}
