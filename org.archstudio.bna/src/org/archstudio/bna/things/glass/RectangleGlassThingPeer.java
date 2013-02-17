package org.archstudio.bna.things.glass;

import java.awt.Dimension;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.RoundRectangle2D;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.facets.IHasSelected;
import org.archstudio.bna.things.AbstractRectangleThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class RectangleGlassThingPeer<T extends RectangleGlassThing> extends AbstractRectangleThingPeer<T> implements
		IThingPeer<T> {

	public RectangleGlassThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, GL2 gl, Rectangle clip, IResources r) {
		if (Boolean.TRUE.equals(t.get(IHasSelected.SELECTED_KEY))) {
			Rectangle lbb = BNAUtils.getLocalBoundingBox(cm, t);
			if (!clip.intersects(lbb)) {
				return;
			}

			Point p1 = new Point(lbb.x, lbb.y);
			Point p2 = new Point(lbb.x + lbb.width, lbb.y + lbb.height);
			Dimension corner = t.getCornerSize();

			if (corner.width == 0 || corner.height == 0) {
				gl.glLineWidth(1f);

				gl.glColor3f(1f, 1f, 1f);
				gl.glBegin(GL.GL_LINE_LOOP);
				gl.glVertex2f(p1.x + 0.5f, p1.y + 0.5f);
				gl.glVertex2f(p2.x - 0.5f, p1.y + 0.5f);
				gl.glVertex2f(p2.x - 0.5f, p2.y - 0.5f);
				gl.glVertex2f(p1.x + 0.5f, p2.y - 0.5f);
				gl.glEnd();

				gl.glColor3f(0f, 0f, 0f);
				gl.glLineStipple(1, (short) (0x0f0f0f0f >> t.getRotatingOffset() % 8));
				gl.glBegin(GL.GL_LINE_LOOP);
				gl.glVertex2f(p1.x + 0.5f, p1.y + 0.5f);
				gl.glVertex2f(p2.x - 0.5f, p1.y + 0.5f);
				gl.glVertex2f(p2.x - 0.5f, p2.y - 0.5f);
				gl.glVertex2f(p1.x + 0.5f, p2.y - 0.5f);
				gl.glEnd();

				gl.glLineStipple(1, (short) 0xffff);
			}
			else {
				RoundRectangle2D s = new RoundRectangle2D.Double(lbb.x + 0.5f, lbb.y + 0.5f, lbb.width, lbb.height,//
						Math.min(lbb.width, corner.width), Math.min(lbb.height, corner.height));
				double[] coords = new double[6];

				gl.glLineWidth(1f);

				gl.glColor3f(1f, 1f, 1f);
				PathIterator p = s.getPathIterator(new AffineTransform(), 0.25d);
				gl.glBegin(GL.GL_LINE_LOOP);
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
				gl.glBegin(GL.GL_LINE_LOOP);
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

				gl.glLineStipple(1, (short) 0xffff);
			}
		}
	}
}
