package org.archstudio.bna.things.glass;

import java.awt.Dimension;

import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.things.AbstractAnchorPointThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class AnchoredShapeGlassThingPeer<T extends AnchoredShapeGlassThing> extends AbstractAnchorPointThingPeer<T> {

	public AnchoredShapeGlassThingPeer(T thing) {
		super(thing);
	}

	public Rectangle getLocalBounds(IBNAView view, ICoordinateMapper cm) {
		Point lp = cm.worldToLocal(t.getAnchorPoint());
		Dimension size = t.getSize();
		return new Rectangle(lp.x - size.width / 2, lp.y - size.height / 2, size.width, size.height);
	}

	@Override
	public boolean isInThing(IBNAView view, ICoordinateMapper cm, ICoordinate location) {
		return getLocalBounds(view, cm).contains(location.getLocalPoint());
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, GL2 gl, Rectangle clip, IResources r) {
		if (t.isSelected()) {
			Rectangle lbb = getLocalBounds(view, cm);

			switch (t.getShape()) {
			case CIRCLE: {
				lbb.width -= 1;
				lbb.height -= 1;
				float[] points = BNAUtils.getEllipsePoints(lbb);

				gl.glColor3f(1f, 1f, 1f);
				gl.glBegin(GL2.GL_LINE_LOOP);
				for (int i = 0; i < points.length; i += 2)
					gl.glVertex2f(points[i] + 0.5f, points[i + 1] + 0.5f);
				gl.glEnd();

				gl.glColor3f(0f, 0f, 0f);
				gl.glLineStipple(1, (short) (0x0f0f0f0f >> t.getRotatingOffset() % 8));
				gl.glBegin(GL2.GL_LINE_LOOP);
				for (int i = 0; i < points.length; i += 2)
					gl.glVertex2f(points[i] + 0.5f, points[i + 1] + 0.5f);
				gl.glEnd();
			}
				break;
			case SQUARE: {
				float[] points = new float[] { lbb.x, lbb.y,//
						lbb.x + lbb.width - 1, lbb.y,//
						lbb.x + lbb.width - 1, lbb.y + lbb.height - 1,//
						lbb.x, lbb.y + lbb.height - 1 };

				gl.glColor3f(1f, 1f, 1f);
				gl.glBegin(GL2.GL_LINE_LOOP);
				for (int i = 0; i < points.length; i += 2)
					gl.glVertex2f(points[i] + 0.5f, points[i + 1] + 0.5f);
				gl.glEnd();

				gl.glColor3f(0f, 0f, 0f);
				gl.glLineStipple(1, (short) (0x0f0f0f0f >> t.getRotatingOffset() % 8));
				gl.glBegin(GL2.GL_LINE_LOOP);
				for (int i = 0; i < points.length; i += 2)
					gl.glVertex2f(points[i] + 0.5f, points[i + 1] + 0.5f);
				gl.glEnd();
			}
				break;
			}
		}

		//			switch (t.getShape()) {
		//			case CIRCLE:
		//				g.setForegroundColor(r.getColor(new RGB(255, 255, 255)));
		//				g.drawOval(lbb.x, lbb.y, lbb.width - 1, lbb.height - 1);
		//				g.setForegroundColor(r.getColor(new RGB(0, 0, 0)));
		//				g.setLineDash(new int[]{4,4});
		//				g.setLineWidth(1);
		//				if (g instanceof SWTGraphics) {
		//					((SWTGraphics) g).setLineDashOffset(t.getRotatingOffset());
		//				}
		//				g.drawOval(lbb.x, lbb.y, lbb.width - 1, lbb.height - 1);
		//				break;
		//			case SQUARE:
		//				g.setForegroundColor(r.getColor(new RGB(255, 255, 255)));
		//				g.drawRectangle(lbb.x, lbb.y, lbb.width - 1, lbb.height - 1);
		//				g.setForegroundColor(r.getColor(new RGB(0, 0, 0)));
		//				g.setLineDash(new int[]{4,4});
		//				g.setLineWidth(1);
		//				if (g instanceof SWTGraphics) {
		//					((SWTGraphics) g).setLineDashOffset(t.getRotatingOffset());
		//				}
		//				g.drawRectangle(lbb.x, lbb.y, lbb.width - 1, lbb.height - 1);
		//				break;
		//			}
	}
}
