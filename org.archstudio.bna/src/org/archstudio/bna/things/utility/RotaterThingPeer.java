package org.archstudio.bna.things.utility;

import java.awt.geom.Arc2D;
import java.awt.geom.Point2D;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.things.AbstractAnchorPointThingPeer;
import org.archstudio.bna.ui.IUIResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

public class RotaterThingPeer<T extends RotaterThing> extends AbstractAnchorPointThingPeer<T> {

	protected static final int UNDERHANG = 4;
	protected static final int WEDGE_SIZE = 10;

	public RotaterThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	@Override
	public void draw(GL2 gl, Rectangle localBounds, IUIResources r) {
		Point lap = cm.worldToLocal(t.getAnchorPoint());
		int Radius = t.getRadius();
		int radius = Radius / 4;
		int angle = t.getAngle();
		float angleRadians = (float) Math.PI * angle / 180;
		float error = 0.25f;
		float radianDelta = (float) Math.acos(2 * (1 - error / Radius) * (1 - error / Radius) - 1);

		r.pushMatrix(lap.x, lap.y, 0);

		{ // background circle
			gl.glBegin(GL.GL_TRIANGLE_STRIP);
			for (float radians = 0; radians < 2f * Math.PI; radians += radianDelta) {
				float x = (float) Math.cos(radians);
				float y = (float) Math.sin(radians);
				gl.glColor4f(0f, 0f, 0f, 0f);
				gl.glVertex2f(x * radius, y * radius);
				gl.glColor4f(0f, 0f, 0f, 0.25f);
				gl.glVertex2f(x * (Radius - UNDERHANG), y * (Radius - UNDERHANG));
			}
			float x = 1;
			float y = 0;
			gl.glColor4f(0f, 0f, 0f, 0f);
			gl.glVertex2f(x * radius, y * radius);
			gl.glColor4f(0f, 0f, 0f, 0.25f);
			gl.glVertex2f(x * (Radius - UNDERHANG), y * (Radius - UNDERHANG));
			gl.glEnd();
		}

		{ // outer circle edge
			gl.glBegin(GL.GL_LINE_LOOP);
			gl.glColor4f(0f, 0f, 0f, 1f);
			for (float radians = 0; radians < 2f * Math.PI; radians += radianDelta) {
				float x = (float) Math.cos(radians) * (Radius - UNDERHANG);
				float y = (float) Math.sin(radians) * (Radius - UNDERHANG);
				gl.glVertex2f(x, y);
			}
			gl.glVertex2f(Radius - UNDERHANG, 0);
			gl.glEnd();
		}

		{ // red wedge
			gl.glBegin(GL.GL_TRIANGLE_FAN);
			gl.glColor4f(1f, 0f, 0f, 0.5f);
			gl.glVertex2i(0, 0);
			gl.glColor4f(1f, 0f, 0f, 0.5f);
			for (float radians = -WEDGE_SIZE * (float) Math.PI / 180; radians < WEDGE_SIZE * (float) Math.PI / 180; radians += radianDelta) {
				float x = (float) Math.cos(radians + angleRadians) * Radius;
				float y = (float) Math.sin(radians + angleRadians) * Radius;
				gl.glVertex2f(x, y);
			}
			float radians = 10 * (float) Math.PI / 180;
			float x = (float) Math.cos(radians + angleRadians) * Radius;
			float y = (float) Math.sin(radians + angleRadians) * Radius;
			gl.glVertex2f(x, y);
		}
		gl.glEnd();

		r.popMatrix();
	}

	@Override
	public void draw(GC gc, Rectangle localBounds, IUIResources r) {
		Point lap = cm.worldToLocal(t.getAnchorPoint());
		int radius = t.getRadius();
		int angle = t.getAngle();
		Rectangle o;

		o = new Rectangle(lap.x - (radius - UNDERHANG), lap.y - (radius - UNDERHANG), 2 * (radius - UNDERHANG),
				2 * (radius - UNDERHANG));

		gc.setAlpha(64);
		gc.setBackground(gc.getDevice().getSystemColor(SWT.COLOR_GRAY));
		gc.fillOval(o.x, o.y, o.width, o.height);

		gc.setAlpha(255);
		gc.setForeground(gc.getDevice().getSystemColor(SWT.COLOR_BLACK));
		gc.drawOval(o.x, o.y, o.width, o.height);

		o = new Rectangle(lap.x - radius, lap.y - radius, 2 * radius, 2 * radius);

		r.setColor(new RGB(255, 0, 0), 0.25);
		r.fillShape(new Arc2D.Double(o.x, o.y, o.width, o.height, -angle - WEDGE_SIZE, WEDGE_SIZE * 2, Arc2D.PIE));
	}

	@Override
	public boolean isInThing(ICoordinate location) {
		Point lap = cm.worldToLocal(t.getAnchorPoint());
		int radius = t.getRadius();
		Point lLocation = location.getLocalPoint();
		float distance = (float) Point2D.distance(lap.x, lap.y, lLocation.x, lLocation.y);
		return distance <= radius;
	}

}
