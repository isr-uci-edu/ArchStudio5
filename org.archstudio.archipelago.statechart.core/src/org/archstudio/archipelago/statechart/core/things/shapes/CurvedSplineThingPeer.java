package org.archstudio.archipelago.statechart.core.things.shapes;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import org.archstudio.archipelago.statechart.core.things.AbstractCurvedSplineThingPeer;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.facets.IHasEdgeColor;
import org.eclipse.swt.graphics.Rectangle;

public class CurvedSplineThingPeer<T extends CurvedSplineThing> extends AbstractCurvedSplineThingPeer<T> {

	public CurvedSplineThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, GL2 gl, Rectangle clip, IResources r) {
		if (r.setColor(t, IHasEdgeColor.EDGE_COLOR_KEY) && r.setLineStyle(t)) {
			Shape s = getShape(view, cm, t);
			double[] coords = new double[6];
			PathIterator p = s.getPathIterator(new AffineTransform(), 0.25d);
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
