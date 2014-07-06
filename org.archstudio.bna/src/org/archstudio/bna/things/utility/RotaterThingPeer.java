package org.archstudio.bna.things.utility;

import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.List;

import javax.media.opengl.GL;
import javax.media.opengl.GL2ES2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.things.AbstractThingPeer;
import org.archstudio.bna.ui.jogl.IJOGLResources;
import org.archstudio.bna.ui.swt.ISWTResources;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

import com.google.common.collect.Lists;
import com.google.common.primitives.Floats;
import com.jogamp.common.nio.Buffers;

public class RotaterThingPeer<T extends RotaterThing> extends AbstractThingPeer<T> {

	protected static final int UNDERHANG = 4;
	protected static final int WEDGE_SIZE = 10;

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

		{ // background circle
			List<Float> vertices = Lists.newArrayList();
			List<Float> colors = Lists.newArrayList();

			for (float radians = 0; radians < 2f * Math.PI; radians += radianDelta) {
				float x = (float) Math.cos(radians);
				float y = (float) Math.sin(radians);

				colors.add(0f);
				colors.add(0f);
				colors.add(0f);
				colors.add(0f);
				vertices.add(x * radius);
				vertices.add(y * radius);
				vertices.add(0f);

				colors.add(0f);
				colors.add(0f);
				colors.add(0f);
				colors.add(0.25f);
				vertices.add(x * (Radius - UNDERHANG));
				vertices.add(y * (Radius - UNDERHANG));
				vertices.add(0f);
			}
			float x = 1;
			float y = 0;

			colors.add(0f);
			colors.add(0f);
			colors.add(0f);
			colors.add(0f);
			vertices.add(x * radius);
			vertices.add(y * radius);
			vertices.add(0f);

			colors.add(0f);
			colors.add(0f);
			colors.add(0f);
			colors.add(0.25f);
			vertices.add(x * (Radius - UNDERHANG));
			vertices.add(y * (Radius - UNDERHANG));
			vertices.add(0f);

			r.fillShape(GL.GL_TRIANGLE_STRIP, //
					Buffers.newDirectFloatBuffer(Floats.toArray(vertices)), //
					Buffers.newDirectFloatBuffer(Floats.toArray(colors)), vertices.size() / 3);
		}

		{ // outer circle edge
			r.setColor(new RGB(0, 0, 0), 1);
			r.drawShape(new Ellipse2D.Double(-Radius + UNDERHANG, -Radius + UNDERHANG, 2 * (Radius - UNDERHANG) - 1,
					2 * (Radius - UNDERHANG) - 1));
		}

		{ // red wedge
			List<Float> vertices = Lists.newArrayList();
			List<Float> colors = Lists.newArrayList();

			colors.add(1f);
			colors.add(0f);
			colors.add(0f);
			colors.add(0.5f);
			vertices.add(0f);
			vertices.add(0f);
			vertices.add(0f);

			for (float radians = -WEDGE_SIZE * (float) Math.PI / 180; radians < WEDGE_SIZE * (float) Math.PI / 180; radians += radianDelta) {
				float x = (float) Math.cos(radians + angleRadians) * Radius;
				float y = (float) Math.sin(radians + angleRadians) * Radius;

				colors.add(1f);
				colors.add(0f);
				colors.add(0f);
				colors.add(1f);
				vertices.add(x);
				vertices.add(y);
				vertices.add(0f);
			}

			float radians = 10 * (float) Math.PI / 180;
			float x = (float) Math.cos(radians + angleRadians) * Radius;
			float y = (float) Math.sin(radians + angleRadians) * Radius;

			colors.add(1f);
			colors.add(0f);
			colors.add(0f);
			colors.add(1f);
			vertices.add(x);
			vertices.add(y);
			vertices.add(0f);

			r.fillShape(GL.GL_TRIANGLE_FAN, //
					Buffers.newDirectFloatBuffer(Floats.toArray(vertices)), //
					Buffers.newDirectFloatBuffer(Floats.toArray(colors)), vertices.size() / 3);
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

		r.setColor(new RGB(255, 0, 0), 0.25);
		r.fillShape(new Arc2D.Double(o.x, o.y, o.width, o.height, -angle - WEDGE_SIZE, WEDGE_SIZE * 2, Arc2D.PIE));
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
