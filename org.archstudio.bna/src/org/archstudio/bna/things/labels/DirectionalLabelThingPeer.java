package org.archstudio.bna.things.labels;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.things.AbstractRectangleThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.swtutils.constants.Flow;
import org.archstudio.swtutils.constants.Orientation;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;

public class DirectionalLabelThingPeer<T extends DirectionalLabelThing> extends AbstractRectangleThingPeer<T> {

	public DirectionalLabelThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, Graphics g, IResources r) {
		Rectangle lbb = BNAUtils.getLocalBoundingBox(cm, t, new Rectangle());

		Flow f = t.getFlow();
		if (f.equals(Flow.NONE)) {
			return;
		}

		Orientation o = t.getOrientation();
		if (o.equals(Orientation.NONE)) {
			return;
		}

		if (r.setBackgroundColor(g, t, IHasColor.COLOR_KEY)) {
			if (f.equals(Flow.OUT) || f.equals(Flow.IN)) {
				/*
				 * For "out" facing flows, the triangle points in the direction
				 * of the orientation. For "in" facing flows, it points the
				 * opposite direction.
				 */
				if (f.equals(Flow.IN)) {
					o = o.opposite();
				}
				int[] trianglePoints = BNAUtils.createIsocolesTriangle(lbb, o);
				g.fillPolygon(trianglePoints);
			}
			else if (f.equals(Flow.INOUT)) {
				Rectangle sbb = lbb;
				int x1 = sbb.x;
				int y1 = sbb.y;
				int xm = x1 + sbb.width / 2;
				int ym = y1 + sbb.height / 2;
				int xmg = xm + 1;
				int ymg = ym + 1;
				int xq = x1 + sbb.width / 7;
				int yq = y1 + sbb.height / 7;
				int xqg = x1 + (sbb.width * 6 + 6) / 7;
				int yqg = y1 + (sbb.height * 6 + 6) / 7;
				int x2 = x1 + sbb.width;
				int y2 = y1 + sbb.height;
				//We have to render two triangles.
				int[] trianglePoints = new int[6];
				switch (o) {
				case NORTH:
				case SOUTH:
					trianglePoints[0] = x1;
					trianglePoints[1] = ym;

					trianglePoints[2] = xm;
					trianglePoints[3] = y1;

					trianglePoints[4] = x2;
					trianglePoints[5] = ym;

					g.fillPolygon(trianglePoints);

					trianglePoints[0] = x1;
					trianglePoints[1] = ymg;

					trianglePoints[2] = xm;
					trianglePoints[3] = y2;

					trianglePoints[4] = x2;
					trianglePoints[5] = ymg;

					g.fillPolygon(trianglePoints);
					break;
				case EAST:
				case WEST:
					trianglePoints[0] = xm;
					trianglePoints[1] = y1;

					trianglePoints[2] = x1;
					trianglePoints[3] = ym;

					trianglePoints[4] = xm;
					trianglePoints[5] = y2;

					g.fillPolygon(trianglePoints);

					trianglePoints[0] = xmg;
					trianglePoints[1] = y1;

					trianglePoints[2] = x2;
					trianglePoints[3] = ym;

					trianglePoints[4] = xmg;
					trianglePoints[5] = y2;

					g.fillPolygon(trianglePoints);
					break;
				case NORTHEAST:
				case SOUTHWEST:
					trianglePoints[0] = xq + 1;
					trianglePoints[1] = yq;

					trianglePoints[2] = x2;
					trianglePoints[3] = y1;

					trianglePoints[4] = xqg;
					trianglePoints[5] = yqg - 1;

					g.fillPolygon(trianglePoints);

					trianglePoints[0] = xq;
					trianglePoints[1] = yq;

					trianglePoints[2] = x1;
					trianglePoints[3] = y2;

					trianglePoints[4] = xqg;
					trianglePoints[5] = yqg;

					g.fillPolygon(trianglePoints);
					break;
				case NORTHWEST:
				case SOUTHEAST:
					trianglePoints[0] = xq;
					trianglePoints[1] = yqg - 1;

					trianglePoints[2] = x1;
					trianglePoints[3] = y1;

					trianglePoints[4] = xqg - 1;
					trianglePoints[5] = yq;

					g.fillPolygon(trianglePoints);

					trianglePoints[0] = xq;
					trianglePoints[1] = yqg;

					trianglePoints[2] = x2;
					trianglePoints[3] = y2;

					trianglePoints[4] = xqg;
					trianglePoints[5] = yq;

					g.fillPolygon(trianglePoints);
					break;
				}
			}
		}
	}
}
