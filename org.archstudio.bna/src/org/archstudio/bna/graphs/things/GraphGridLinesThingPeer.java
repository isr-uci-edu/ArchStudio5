package org.archstudio.bna.graphs.things;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.Resources;
import org.archstudio.bna.facets.IHasEdgeColor;
import org.archstudio.bna.graphs.GraphCoordinateMapper;
import org.archstudio.bna.things.AbstractRectangleThingPeer;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class GraphGridLinesThingPeer<T extends GraphGridLinesThing> extends AbstractRectangleThingPeer<T> {

	public GraphGridLinesThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	@Override
	public void draw(GL2 gl, Rectangle localBounds, Resources r) {
		if (r.setLineStyle(t) && r.setColor(t, IHasEdgeColor.EDGE_COLOR_KEY)) {

			Rectangle wbb = t.getBoundingBox();
			Rectangle lbb = cm.worldToLocal(wbb);
			int wUnit = t.getUnit();

			// draw units
			gl.glBegin(GL.GL_LINES);
			switch (t.getOrientation()) {
			case VERTICAL_LINES: {
				switch (cm instanceof GraphCoordinateMapper ? ((GraphCoordinateMapper) cm).getXAxisType()
						: GraphCoordinateMapper.Type.LINEAR) {
				case LINEAR: {
					int wMin = wbb.x / wUnit * wUnit;
					int wMax = wbb.x + wbb.width;
					for (int wX = wMin; wX <= wMax; wX += wUnit) {
						if (wX < wbb.x) {
							continue;
						}
						Point lPoint = cm.worldToLocal(new Point(wX, wbb.y));
						gl.glVertex2f(lPoint.x + 0.5f, localBounds.height - (lbb.y + 0.5f));
						gl.glVertex2f(lPoint.x + 0.5f, localBounds.height - (lbb.y + lbb.height + 0.5f));
					}
					break;
				}
				case LOGARITHMIC: {
					int wMin = 1;
					int wMax = wbb.x + wbb.width;
					for (int wX = wMin; wX <= wMax; wX *= wUnit) {
						if (wX < wbb.x) {
							continue;
						}
						Point lPoint = cm.worldToLocal(new Point(wX, wbb.y));
						gl.glVertex2f(lPoint.x + 0.5f, localBounds.height - (lbb.y + 0.5f));
						gl.glVertex2f(lPoint.x + 0.5f, localBounds.height - (lbb.y + lbb.height + 0.5f));
					}
					break;
				}
				}
			}
			case HORIZONTAL_LINES: {
				switch (cm instanceof GraphCoordinateMapper ? ((GraphCoordinateMapper) cm).getYAxisType()
						: GraphCoordinateMapper.Type.LINEAR) {
				case LINEAR: {
					int wMin = wbb.y / wUnit * wUnit;
					int wMax = wbb.y + wbb.height;
					for (int wY = wMin; wY <= wMax; wY += wUnit) {
						if (wY < wbb.y) {
							continue;
						}
						Point lPoint = cm.worldToLocal(new Point(wbb.x, wY));
						gl.glVertex2f(lbb.x + 0.5f, localBounds.height - (lPoint.y + 0.5f));
						gl.glVertex2f(lbb.x + lbb.width + 0.5f, localBounds.height - (lPoint.y + 0.5f));
					}
					break;
				}
				case LOGARITHMIC: {
					int wMin = -1;
					int wMax = wbb.y;
					for (int wY = wMin; wY >= wMax; wY *= wUnit) {
						if (wY > wbb.y + wbb.height) {
							continue;
						}
						Point lPoint = cm.worldToLocal(new Point(wbb.x, wY));
						gl.glVertex2f(lbb.x + 0.5f, localBounds.height - (lPoint.y + 0.5f));
						gl.glVertex2f(lbb.x + lbb.width + 0.5f, localBounds.height - (lPoint.y + 0.5f));
					}
					break;
				}
				}
				gl.glEnd();
			}
			}
		}
	}
}
