package org.archstudio.bna.things.utility;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.constants.GridDisplayType;
import org.archstudio.bna.facets.IHasEdgeColor;
import org.archstudio.bna.things.AbstractThingPeer;
import org.archstudio.bna.ui.IUIResources;
import org.archstudio.swtutils.constants.LineStyle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class GridThingPeer<T extends GridThing> extends AbstractThingPeer<T> {

	public GridThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	int worldGridStep;
	GridDisplayType gridDisplayType;

	@Override
	public boolean draw(Rectangle localBounds, IUIResources r) {

		// only draw for the top level things
		if (view.getParentView() != null) {
			return false;
		}

		if ((worldGridStep = t.getGridSpacing()) == 0) {
			return false;
		}

		if ((gridDisplayType = t.getGridDisplayType()) == GridDisplayType.NONE) {
			return false;
		}

		while (worldGridStep * cm.getLocalScale() <= 8) {
			worldGridStep *= 2;
		}

		return true;
	}

	@Override
	public void draw(GL2 gl, Rectangle localBounds, IUIResources r) {

		if (r.setColor(t, IHasEdgeColor.EDGE_COLOR_KEY)) {

			Rectangle lClip = new Rectangle(localBounds.x, localBounds.y, localBounds.width, localBounds.height);
			Rectangle wClip = cm.localToWorld(lClip);
			int wx = wClip.x;
			int wy = wClip.y;
			int wx2 = wClip.x + wClip.width;
			int wy2 = wClip.y + wClip.height;

			int dx = wx % worldGridStep;
			int dy = wy % worldGridStep;

			if (gridDisplayType == GridDisplayType.SOLID_LINES || gridDisplayType == GridDisplayType.DOTTED_LINES) {
				if (gridDisplayType == GridDisplayType.DOTTED_LINES) {
					gl.glLineStipple(1, (short) LineStyle.DOT.toBitPattern());
				}
				gl.glBegin(GL.GL_LINES);
				for (int i = wx - dx; i <= wx2; i += worldGridStep) {
					int gx = cm.worldToLocal(new Point(i, wy)).x;
					gl.glVertex2f(gx + 0.5f, lClip.y + 0.5f);
					gl.glVertex2f(gx + 0.5f, lClip.y + lClip.height + 0.5f);
				}
				for (int i = wy - dy; i <= wy2; i += worldGridStep) {
					int gy = cm.worldToLocal(new Point(wx, i)).y;
					gl.glVertex2f(lClip.x + 0.5f, gy + 0.5f);
					gl.glVertex2f(lClip.x + lClip.width + 0.5f, gy + 0.5f);
				}
				gl.glEnd();
			}
			else if (gridDisplayType == GridDisplayType.DOTS_AT_CORNERS) {
				gl.glBegin(GL.GL_POINTS);
				for (int i = wx - dx; i <= wx2; i += worldGridStep) {
					int gx = cm.worldToLocal(new Point(i, wy)).x;
					for (int j = wy - dy; j <= wy2; j += worldGridStep) {
						int gy = cm.worldToLocal(new Point(wx, j)).y;
						gl.glVertex2f(gx + 0.5f, gy + 0.5f);
					}
				}
				gl.glEnd();
			}
			else if (gridDisplayType == GridDisplayType.CROSSES_AT_CORNERS) {
				gl.glBegin(GL.GL_LINES);
				for (int i = wx - dx; i <= wx2; i += worldGridStep) {
					int gx = cm.worldToLocal(new Point(i, wy)).x;
					for (int j = wy - dy; j <= wy2; j += worldGridStep) {
						int gy = cm.worldToLocal(new Point(wx, j)).y;
						gl.glVertex2f(gx - 3 + 0.5f, gy + 0.5f);
						gl.glVertex2f(gx + 4 + 0.5f, gy + 0.5f);
						gl.glVertex2f(gx + 0.5f, gy - 3 + 0.5f);
						gl.glVertex2f(gx + 0.5f, gy + 4 + 0.5f);
					}
				}
				gl.glEnd();
			}
		}

		r.resetLineStyle();
	}

	@Override
	public void draw(GC gc, Rectangle localBounds, IUIResources r) {

		if (r.setColor(t, IHasEdgeColor.EDGE_COLOR_KEY)) {

			Rectangle lClip = new Rectangle(localBounds.x, localBounds.y, localBounds.width, localBounds.height);
			Rectangle wClip = cm.localToWorld(lClip);
			int wx = wClip.x;
			int wy = wClip.y;
			int wx2 = wClip.x + wClip.width;
			int wy2 = wClip.y + wClip.height;

			int dx = wx % worldGridStep;
			int dy = wy % worldGridStep;

			if (gridDisplayType == GridDisplayType.SOLID_LINES || gridDisplayType == GridDisplayType.DOTTED_LINES) {
				if (gridDisplayType == GridDisplayType.DOTTED_LINES) {
					// SWT.LINE_DOT is too dashed
					// gc.setLineStyle(LineStyle.DOT.toSwtStyle());
					gc.setLineStyle(SWT.LINE_CUSTOM);
					gc.setLineDash(new int[] { 1, 1 });
				}
				for (int i = wx - dx; i <= wx2; i += worldGridStep) {
					int gx = cm.worldToLocal(new Point(i, wy)).x;
					int y1 = lClip.y;
					int y2 = lClip.y + lClip.height;
					gc.drawLine(gx, y1, gx, y2);
				}
				for (int i = wy - dy; i <= wy2; i += worldGridStep) {
					int gy = cm.worldToLocal(new Point(wx, i)).y;
					int x1 = lClip.x;
					int x2 = lClip.x + lClip.width;
					gc.drawLine(x1, gy, x2, gy);
				}
			}
			else if (gridDisplayType == GridDisplayType.DOTS_AT_CORNERS) {
				for (int i = wx - dx; i <= wx2; i += worldGridStep) {
					int gx = cm.worldToLocal(new Point(i, wy)).x;
					for (int j = wy - dy; j <= wy2; j += worldGridStep) {
						int gy = cm.worldToLocal(new Point(wx, j)).y;
						gc.drawPoint(gx, gy);
					}
				}
			}
			else if (gridDisplayType == GridDisplayType.CROSSES_AT_CORNERS) {
				for (int i = wx - dx; i <= wx2; i += worldGridStep) {
					int gx = cm.worldToLocal(new Point(i, wy)).x;
					for (int j = wy - dy; j <= wy2; j += worldGridStep) {
						int gy = cm.worldToLocal(new Point(wx, j)).y;
						gc.drawLine(gx - 3, gy, gx + 3, gy);
						gc.drawLine(gx, gy - 3, gx, gy + 3);
					}
				}
			}
		}

		r.resetLineStyle();
	}
}
