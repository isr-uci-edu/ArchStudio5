package org.archstudio.bna.things.utility;

import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import javax.media.opengl.GL;
import javax.media.opengl.GL2ES2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.things.AbstractThingPeer;
import org.archstudio.bna.ui.jogl.IJOGLResources;
import org.archstudio.bna.ui.swt.ISWTResources;
import org.archstudio.swtutils.constants.LineStyle;
import org.archstudio.sysutils.ExpandableFloatBuffer;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

public class RotaterThingPeer<T extends RotaterThing> extends AbstractThingPeer<T> {

	protected static final int UNDERHANG = 4;
	protected static final int WEDGE_SIZE = 15;

	public RotaterThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	@Override
	public void draw(GL2ES2 gl, Rectangle localBounds, IJOGLResources r) {
		Point2D lap = cm.worldToLocal(t.getRawAnchorPoint());
		int Radius = t.getRawRadius();
		int radius = Radius / 4;
		int angle = t.getRawAngle();
		float angleRadians = (float) Math.PI * angle / 180;
		float error = 0.25f;
		float radianDelta = (float) Math.acos(2 * (1 - error / Radius) * (1 - error / Radius) - 1);

		r.pushMatrix(lap.getX(), lap.getY(), 0);

		ExpandableFloatBuffer xyzVertices = new ExpandableFloatBuffer();
		ExpandableFloatBuffer rgbaColors = new ExpandableFloatBuffer();

		{ // background circle
			xyzVertices.rewind();
			rgbaColors.rewind();

			float[] vertex = new float[] { 0, 0, 0 };
			float[] colors = new float[] { 0, 0, 0, 0, 0, 0, 0, 0.25f };

			for (float radians = 0; radians < 2f * Math.PI; radians += radianDelta) {
				float x = (float) Math.cos(radians);
				float y = (float) Math.sin(radians);

				vertex[0] = x * radius;
				vertex[1] = y * radius;
				xyzVertices.put(vertex);

				vertex[0] = x * (Radius - UNDERHANG);
				vertex[1] = y * (Radius - UNDERHANG);
				xyzVertices.put(vertex);

				rgbaColors.put(colors);
			}
			float x = 1;
			float y = 0;

			vertex[0] = x * radius;
			vertex[1] = y * radius;
			xyzVertices.put(vertex);

			vertex[0] = x * (Radius - UNDERHANG);
			vertex[1] = y * (Radius - UNDERHANG);
			xyzVertices.put(vertex);

			rgbaColors.put(colors);

			int points = xyzVertices.position() / 3;
			xyzVertices.rewind();
			rgbaColors.rewind();
			r.fillShape(GL.GL_TRIANGLE_STRIP, xyzVertices.getBackingBuffer(), rgbaColors.getBackingBuffer(), points);
		}

		{ // outer circle edge
			r.drawShape(new Ellipse2D.Double(-Radius + UNDERHANG, -Radius + UNDERHANG, 2 * (Radius - UNDERHANG) - 1,
					2 * (Radius - UNDERHANG) - 1), new RGB(0, 0, 0), 1, LineStyle.SOLID, 1);
		}

		{ // red wedge
			xyzVertices.rewind();
			rgbaColors.rewind();

			float[] vertex = new float[] { 0, 0, 0 };
			float[] color = new float[] { 1, 0, 0, 0.5f };

			xyzVertices.put(vertex);
			rgbaColors.put(color);

			color[3] = 1;
			for (float radians = -WEDGE_SIZE * (float) Math.PI / 180; radians < WEDGE_SIZE * (float) Math.PI / 180; radians += radianDelta) {
				float x = (float) Math.cos(radians + angleRadians) * Radius;
				float y = (float) Math.sin(radians + angleRadians) * Radius;

				vertex[0] = x;
				vertex[1] = y;
				xyzVertices.put(vertex);
				rgbaColors.put(color);
			}

			float radians = WEDGE_SIZE * (float) Math.PI / 180;
			float x = (float) Math.cos(radians + angleRadians) * Radius;
			float y = (float) Math.sin(radians + angleRadians) * Radius;

			vertex[0] = x;
			vertex[1] = y;
			xyzVertices.put(vertex);
			rgbaColors.put(color);

			int points = xyzVertices.position() / 3;
			xyzVertices.rewind();
			rgbaColors.rewind();
			r.fillShape(GL.GL_TRIANGLE_FAN, xyzVertices.getBackingBuffer(), rgbaColors.getBackingBuffer(), points);
		}

		r.popMatrix();
	}

	@Override
	public void draw(GC gc, Rectangle localBounds, ISWTResources r) {
		Point2D lap = cm.worldToLocal(t.getRawAnchorPoint());
		int radius = t.getRawRadius();
		int angle = t.getRawAngle();
		Rectangle o;

		o = new Rectangle(SystemUtils.round(lap.getX()) - (radius - UNDERHANG), SystemUtils.round(lap.getY())
				- (radius - UNDERHANG), 2 * (radius - UNDERHANG), 2 * (radius - UNDERHANG));

		gc.setAlpha(64);
		gc.setBackground(gc.getDevice().getSystemColor(SWT.COLOR_GRAY));
		gc.fillOval(o.x, o.y, o.width, o.height);

		gc.setAlpha(255);
		gc.setForeground(gc.getDevice().getSystemColor(SWT.COLOR_BLACK));
		gc.drawOval(o.x, o.y, o.width, o.height);

		o = new Rectangle(SystemUtils.round(lap.getX()) - radius, SystemUtils.round(lap.getY()) - radius, 2 * radius,
				2 * radius);

		r.fillShape(new Arc2D.Double(o.x, o.y, o.width, o.height, -angle - WEDGE_SIZE, WEDGE_SIZE * 2, Arc2D.PIE),
				new RGB(255, 0, 0), new RGB(255, 0, 0), 0.5);
	}

	@Override
	public boolean isInThing(ICoordinate location) {
		Point2D lap = cm.worldToLocal(t.getRawAnchorPoint());
		int radius = t.getRawRadius();
		Point lLocation = location.getLocalPoint();
		float distance = (float) Point2D.distance(lap.getX(), lap.getY(), lLocation.x, lLocation.y);
		return distance <= radius;
	}

}
