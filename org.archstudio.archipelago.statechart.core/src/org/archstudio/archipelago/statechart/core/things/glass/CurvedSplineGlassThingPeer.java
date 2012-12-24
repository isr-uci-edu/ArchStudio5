package org.archstudio.archipelago.statechart.core.things.glass;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import org.archstudio.archipelago.statechart.core.things.AbstractCurvedSplineThingPeer;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.facets.IHasSelected;
import org.eclipse.swt.graphics.Rectangle;

public class CurvedSplineGlassThingPeer<T extends CurvedSplineGlassThing> extends AbstractCurvedSplineThingPeer<T> {

	public CurvedSplineGlassThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, GL2 gl, Rectangle clip, IResources r) {
		if (Boolean.TRUE.equals(t.get(IHasSelected.SELECTED_KEY))) {

			Shape s = getShape(view, cm, t);
			double[] coords = new double[6];

			gl.glLineWidth(1f);

			gl.glColor3f(1f, 1f, 1f);
			PathIterator p = s.getPathIterator(new AffineTransform(), 2d);
			gl.glBegin(GL.GL_LINE_STRIP);
			while (!p.isDone()) {
				switch (p.currentSegment(coords)) {
				case PathIterator.SEG_MOVETO:
				case PathIterator.SEG_LINETO:
					gl.glVertex2d(coords[0], coords[1]);
					break;
				case PathIterator.SEG_CLOSE:
					break;
				default:
					throw new IllegalArgumentException();
				}
				p.next();
			}
			gl.glEnd();

			gl.glColor3f(0f, 0f, 0f);
			gl.glLineStipple(1, (short) (0x0f0f0f0f >> t.getRotatingOffset() % 8));
			p = s.getPathIterator(new AffineTransform(), 0.25d);
			gl.glBegin(GL.GL_LINE_STRIP);
			while (!p.isDone()) {
				switch (p.currentSegment(coords)) {
				case PathIterator.SEG_MOVETO:
				case PathIterator.SEG_LINETO:
					gl.glVertex2d(coords[0], coords[1]);
					break;
				case PathIterator.SEG_CLOSE:
					break;
				default:
					throw new IllegalArgumentException();
				}
				p.next();
			}
			gl.glEnd();

		}
	};

}
