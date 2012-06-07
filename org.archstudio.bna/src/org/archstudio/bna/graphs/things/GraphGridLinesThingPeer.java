package org.archstudio.bna.graphs.things;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.facets.IHasEdgeColor;
import org.archstudio.bna.graphs.GraphCoordinateMapper;
import org.archstudio.bna.things.AbstractRectangleThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public class GraphGridLinesThingPeer<T extends GraphGridLinesThing> extends AbstractRectangleThingPeer<T> {

	public GraphGridLinesThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, IResources r, Graphics g) {
		if (BNAUtils.setForegroundColor(r, g, t, IHasEdgeColor.EDGE_COLOR_KEY)) {
			BNAUtils.setLineStyle(r, g, t);

			Rectangle wbb = t.getBoundingBox();
			Rectangle lbb = cm.worldToLocal(wbb);
			int wUnit = t.getUnit();

			// draw units
			switch (t.getOrientation()) {
			case VERTICAL_LINES: {
				switch (cm instanceof GraphCoordinateMapper ? ((GraphCoordinateMapper) cm).getXAxisType()
						: GraphCoordinateMapper.Type.LINEAR) {
				case LINEAR: {
					int wMin = wbb.x / wUnit * wUnit;
					int wMax = ((wbb.x + wbb.width) / wUnit + 1) * wUnit;
					for (int wX = wMin; wX <= wMax; wX += wUnit) {
						Point lPoint = cm.worldToLocal(new Point(wX, wbb.y));
						g.drawLine(lPoint.x, lbb.y, lPoint.x, lbb.y + lbb.height);
					}
					break;
				}
				case LOGARITHMIC: {
					int wMin = 1;
					int wMax = wbb.x + wbb.width;
					for (int wX = wMin; wX <= wMax; wX *= wUnit) {
						Point lPoint = cm.worldToLocal(new Point(wX, wbb.y));
						g.drawLine(lPoint.x, lbb.y, lPoint.x, lbb.y + lbb.height);
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
					int wMax = ((wbb.y + wbb.height) / wUnit + 1) * wUnit;
					for (int wY = wMin; wY <= wMax; wY += wUnit) {
						Point lPoint = cm.worldToLocal(new Point(wbb.x, wY));
						g.drawLine(lbb.x, lPoint.y, lbb.x + lbb.width, lPoint.y);
					}
					break;
				}
				case LOGARITHMIC: {
					int wMin = -1;
					int wMax = wbb.y;
					for (int wY = wMin; wY >= wMax; wY *= wUnit) {
						Point lPoint = cm.worldToLocal(new Point(wbb.x, wY));
						g.drawLine(lbb.x, lPoint.y, lbb.x + lbb.width, lPoint.y);
					}
					break;
				}
				}
			}
			}
		}
	}

	@Override
	public Rectangle getLocalBounds(IBNAView view, ICoordinateMapper cm, IResources r) {
		return super.getLocalBounds(view, cm, r).expand(1, 1);
	}
}
