package org.archstudio.bna.things.shapes;

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
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.facets.IHasEdgeColor;
import org.archstudio.bna.facets.IHasSecondaryColor;
import org.archstudio.bna.facets.peers.IHasShadowPeer;
import org.archstudio.bna.things.AbstractRectangleThingPeer;
import org.archstudio.bna.utils.BNARenderingSettings;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

public class RectangleThingPeer<T extends RectangleThing> extends AbstractRectangleThingPeer<T> implements
		IThingPeer<T>, IHasShadowPeer {

	public RectangleThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, GL2 gl, Rectangle clip, IResources r) {
		Rectangle lbb = BNAUtils.getLocalBoundingBox(cm, t);
		if (!lbb.intersects(clip)) {
			return;
		}

		Point p1 = new Point(lbb.x, lbb.y);
		Point p2 = new Point(lbb.x + lbb.width, lbb.y + lbb.height);
		Dimension corner = t.getCornerSize();

		if (corner.width == 0 || corner.height == 0) {
			if (r.setColor(t, IHasColor.COLOR_KEY)) {
				gl.glBegin(GL2.GL_QUADS);
				gl.glVertex2i(p1.x, p1.y);
				gl.glVertex2i(p2.x, p1.y);
				if (t.isGradientFilled() && BNARenderingSettings.getDecorativeGraphics(view.getComposite())) {
					r.setColor(t, IHasSecondaryColor.SECONDARY_COLOR_KEY);
				}
				gl.glVertex2i(p2.x, p2.y);
				gl.glVertex2i(p1.x, p2.y);
				gl.glEnd();
			}
			if (r.setColor(t, IHasEdgeColor.EDGE_COLOR_KEY) && r.setLineStyle(t)) {
				int count = t.getCount();
				int width = t.getLineWidth();
				float o = width % 2 > 0 ? 0.5f : 0;
				while (count > 0) {
					int inset = (count - 1) * width * 2;
					gl.glBegin(GL.GL_LINE_LOOP);
					gl.glVertex2f(p1.x + inset + o, p1.y + inset + o);
					gl.glVertex2f(p2.x - inset - o, p1.y + inset + o);
					gl.glVertex2f(p2.x - inset - o, p2.y - inset - o);
					gl.glVertex2f(p1.x + inset + o, p2.y - inset - o);
					gl.glEnd();
					count--;
				}
			}
		}
		else {
			RoundRectangle2D s = new RoundRectangle2D.Double(lbb.x + 0.5f, lbb.y + 0.5f, lbb.width, lbb.height,//
					Math.min(lbb.width, corner.width), Math.min(lbb.height, corner.height));
			double[] coords = new double[6];
			boolean isGradientFilled = t.isGradientFilled()
					&& BNARenderingSettings.getDecorativeGraphics(view.getComposite());
			RGB color1 = t.getColor();
			RGB color2 = t.getSecondaryColor();
			if (r.setColor(t, IHasColor.COLOR_KEY)) {
				PathIterator p = s.getPathIterator(new AffineTransform(), 0.25d);
				gl.glBegin(GL.GL_TRIANGLE_FAN);
				while (!p.isDone()) {
					switch (p.currentSegment(coords)) {
					case PathIterator.SEG_MOVETO:
					case PathIterator.SEG_LINETO:
						if (isGradientFilled) {
							setColor(gl, color1, color2, s.getMinY(), s.getMaxY(), coords[1]);
						}
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
			if (r.setColor(t, IHasEdgeColor.EDGE_COLOR_KEY) && r.setLineStyle(t)) {
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
			}
		}
	}

	private void setColor(GL2 gl, RGB color1, RGB color2, double minY, double maxY, double d) {
		double f = SystemUtils.bound(0d, (d - minY) / (maxY - minY), 1d);
		gl.glColor3d(//
				(color1.red + (color2.red - color1.red) * f) / 255d,//
				(color1.green + (color2.green - color1.green) * f) / 255d,//
				(color1.blue + (color2.blue - color1.blue) * f) / 255d);
	}

	@Override
	public void drawShadow(IBNAView view, ICoordinateMapper cm, GL2 gl, Rectangle clip, IResources r) {
		Rectangle lbb = BNAUtils.getLocalBoundingBox(cm, t);
		if (!clip.intersects(lbb)) {
			return;
		}

		Dimension corner = t.getCornerSize();

		RoundRectangle2D s = new RoundRectangle2D.Double(lbb.x + 0.5f, lbb.y + 0.5f, lbb.width, lbb.height,//
				Math.min(lbb.width, corner.width), Math.min(lbb.height, corner.height));
		double[] coords = new double[6];
		r.setColor(new RGB(0, 0, 0), 1f);

		PathIterator p = s.getPathIterator(new AffineTransform(), 0.25d);
		gl.glBegin(GL.GL_TRIANGLE_FAN);
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
}
