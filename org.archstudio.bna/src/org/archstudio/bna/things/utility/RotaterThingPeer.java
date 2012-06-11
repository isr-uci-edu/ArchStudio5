package org.archstudio.bna.things.utility;

import java.awt.geom.Point2D;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.things.AbstractAnchorPointThingPeer;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class RotaterThingPeer<T extends RotaterThing> extends AbstractAnchorPointThingPeer<T> implements IThingPeer<T> {

	public RotaterThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, GL2 gl, Rectangle clip, IResources r) {
		Point lap = cm.worldToLocal(t.getAnchorPoint());
		int Radius = t.getRadius();
		int radius = Radius / 4;
		int angle = t.getAngle();
		float angleRadians = (float) Math.PI * angle / 180;
		float error = 0.25f;
		float radianDelta = (float) Math.acos(2 * (1 - error / Radius) * (1 - error / Radius) - 1);

		int UNDERHANG = 4;
		gl.glPushMatrix();
		gl.glTranslatef(lap.x, lap.y, 0);

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

		//{ // inner circle edge
		//	gl.glBegin(GL.GL_LINE_LOOP);
		//	gl.glColor4f(0f, 0f, 0f, 1f);
		//	for (float radians = 0; radians < 2f * Math.PI; radians += radianDelta) {
		//		float x = (float) Math.cos(radians) * radius;
		//		float y = (float) Math.sin(radians) * radius;
		//		gl.glVertex2f(x, y);
		//	}
		//	gl.glVertex2f(radius, 0);
		//	gl.glEnd();
		//}

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
			for (float radians = -10 * (float) Math.PI / 180; radians < 10 * (float) Math.PI / 180; radians += radianDelta) {
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
		gl.glPopMatrix();
	}

	@Override
	public boolean isInThing(IBNAView view, ICoordinateMapper cm, ICoordinate location) {
		Point lap = cm.worldToLocal(t.getAnchorPoint());
		int radius = t.getRadius();
		Point lLocation = location.getLocalPoint();
		float distance = (float) Point2D.distance(lap.x, lap.y, lLocation.x, lLocation.y);
		return distance <= radius;
	}

}
