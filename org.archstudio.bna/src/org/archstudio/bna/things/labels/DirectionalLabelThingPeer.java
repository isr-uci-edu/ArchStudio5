package org.archstudio.bna.things.labels;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.Resources;
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.things.AbstractRectangleThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.swtutils.constants.Flow;
import org.archstudio.swtutils.constants.Orientation;
import org.eclipse.swt.graphics.Rectangle;

public class DirectionalLabelThingPeer<T extends DirectionalLabelThing> extends AbstractRectangleThingPeer<T> {

	public DirectionalLabelThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	@Override
	public void draw(GL2 gl, Rectangle localBounds, Resources r) {
		Rectangle lbb = BNAUtils.getLocalBoundingBox(cm, t);
		if (!localBounds.intersects(lbb)) {
			return;
		}

		Flow f = t.getFlow();
		if (f.equals(Flow.NONE)) {
			return;
		}

		Orientation o = t.getOrientation();
		if (o.equals(Orientation.NONE)) {
			return;
		}

		if (r.setColor(t, IHasColor.COLOR_KEY)) {
			if (f.equals(Flow.OUT) || f.equals(Flow.IN)) {
				/*
				 * For "out" facing flows, the triangle points in the direction of the orientation. For "in" facing
				 * flows, it points the opposite direction.
				 */
				if (f.equals(Flow.IN)) {
					o = o.opposite();
				}
				int[] trianglePoints = BNAUtils.createIsocolesTriangle(lbb, o);

				gl.glBegin(GL.GL_TRIANGLES);
				for (int i = 0; i < trianglePoints.length; i += 2) {
					gl.glVertex2f(trianglePoints[i] + 0.5f, localBounds.height - trianglePoints[i + 1] + 0.5f);
				}
				gl.glEnd();
			}
			else if (f.equals(Flow.INOUT)) {
				Rectangle sbb = lbb;
				float x1 = sbb.x + 0.5f;
				float y1 = sbb.y + 0.5f;
				float xm = x1 + sbb.width / 2;
				float ym = y1 + sbb.height / 2;
				float xmg = xm + 1;
				float ymg = ym + 1;
				float xq = x1 + sbb.width / 7;
				float yq = y1 + sbb.height / 7;
				float xqg = x1 + (sbb.width * 6 + 6) / 7;
				float yqg = y1 + (sbb.height * 6 + 6) / 7;
				float x2 = x1 + sbb.width - 0.5f;
				float y2 = y1 + sbb.height - 0.5f;
				//We have to render two triangles.
				switch (o) {

				case NORTH:
				case SOUTH:

					gl.glBegin(GL.GL_TRIANGLES);
					gl.glVertex2f(x1, localBounds.height - ym);
					gl.glVertex2f(xm, localBounds.height - y1);
					gl.glVertex2f(x2, localBounds.height - ym);
					gl.glEnd();

					gl.glBegin(GL.GL_TRIANGLES);
					gl.glVertex2f(x1, localBounds.height - ymg);
					gl.glVertex2f(xm, localBounds.height - y2);
					gl.glVertex2f(x2, localBounds.height - ymg);
					gl.glEnd();

					break;

				case EAST:
				case WEST:

					gl.glBegin(GL.GL_TRIANGLES);
					gl.glVertex2f(xm, localBounds.height - y1);
					gl.glVertex2f(x1, localBounds.height - ym);
					gl.glVertex2f(xm, localBounds.height - y2);
					gl.glEnd();

					gl.glBegin(GL.GL_TRIANGLES);
					gl.glVertex2f(xmg, localBounds.height - y1);
					gl.glVertex2f(x2, localBounds.height - ym);
					gl.glVertex2f(xmg, localBounds.height - y2);
					gl.glEnd();

					break;

				case NORTHEAST:
				case SOUTHWEST:

					gl.glBegin(GL.GL_TRIANGLES);
					gl.glVertex2f(xq + 1, localBounds.height - yq);
					gl.glVertex2f(x2, localBounds.height - y1);
					gl.glVertex2f(xqg, localBounds.height - (yqg - 1));
					gl.glEnd();

					gl.glBegin(GL.GL_TRIANGLES);
					gl.glVertex2f(xq, localBounds.height - yq);
					gl.glVertex2f(x1, localBounds.height - y2);
					gl.glVertex2f(xqg, localBounds.height - yqg);
					gl.glEnd();

					break;

				case NORTHWEST:
				case SOUTHEAST:

					gl.glBegin(GL.GL_TRIANGLES);
					gl.glVertex2f(xq, localBounds.height - (yqg - 1));
					gl.glVertex2f(x1, localBounds.height - y1);
					gl.glVertex2f(xqg - 1, localBounds.height - yq);
					gl.glEnd();

					gl.glBegin(GL.GL_TRIANGLES);
					gl.glVertex2f(xq, localBounds.height - yqg);
					gl.glVertex2f(x2, localBounds.height - y2);
					gl.glVertex2f(xqg, localBounds.height - yq);
					gl.glEnd();

					break;

				default:
					// draw nothing
				}
			}
		}
	}
}
