package org.archstudio.bna.things.borders;

import java.util.List;

import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.things.AbstractSplineThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

import com.google.common.collect.Lists;

public class SplineGlowThingPeer<T extends SplineGlowThing> extends AbstractSplineThingPeer<T> {

	public SplineGlowThingPeer(T thing) {
		super(thing);
	}

	@Override
	public boolean isInThing(IBNAView view, ICoordinateMapper cm, ICoordinate location) {
		return false;
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, GL2 gl, Rectangle clip, IResources r) {
		List<Point> localPoints = BNAUtils.worldToLocal(cm, t.getPoints());
		drawSide(view, cm, gl, clip, r, localPoints);
		drawSide(view, cm, gl, clip, r, Lists.reverse(localPoints));
	}

	private void drawSide(IBNAView view, ICoordinateMapper cm, GL2 gl, Rectangle clip, IResources r,
			List<Point> localPoints) {
		int width = t.getWidth();
		RGB color = t.getColor();

		if (color != null) {
			gl.glBegin(GL2.GL_TRIANGLE_STRIP);
			for (int i = 1; i < localPoints.size(); i++) {
				Point p1 = localPoints.get(i - 1);
				Point p2 = localPoints.get(i);

				double angle = Math.atan2(p2.y - p1.y, p2.x - p1.x);

				r.setColor(color, 0.5f);
				gl.glVertex2d(p1.x, p1.y);
				r.setColor(color, 0f);
				gl.glVertex2d(p1.x + width * Math.sin(-angle), p1.y + width * Math.cos(-angle));

				r.setColor(color, 0.5f);
				gl.glVertex2d(p2.x, p2.y);
				r.setColor(color, 0f);
				gl.glVertex2d(p2.x + width * Math.sin(-angle), p2.y + width * Math.cos(-angle));

			}
			gl.glEnd();
		}
	}
}
