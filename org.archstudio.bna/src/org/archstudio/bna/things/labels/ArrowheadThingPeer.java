package org.archstudio.bna.things.labels;

import java.util.Arrays;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.constants.ArrowheadShape;
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.facets.IHasEdgeColor;
import org.archstudio.bna.things.AbstractThingPeer;
import org.archstudio.bna.utils.ArrowheadUtils;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public class ArrowheadThingPeer<T extends ArrowheadThing> extends AbstractThingPeer<T> {

	public ArrowheadThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, Graphics g, IResources r) {

		Point lp1 = cm.worldToLocal(t.getAnchorPoint());
		Point lp2 = cm.worldToLocal(t.getReferencePoint());

		ArrowheadShape arrowheadShape = t.getArrowheadShape();
		if (arrowheadShape == null || arrowheadShape == ArrowheadShape.NONE) {
			return;
		}

		int arrowheadSize = t.getArrowheadSize();
		int[] points = ArrowheadUtils.calculateTriangularArrowhead(lp2.x, lp2.y, lp1.x, lp1.y, arrowheadSize);
		if (points == null) {
			return;
		}
		Point[] pointArray = BNAUtils.toPointArray(points);
		BNAUtils.worldToLocal(cm, Arrays.asList(pointArray));
		points = BNAUtils.toXYArray(Arrays.asList(pointArray));

		if (r.setBackgroundColor(g, t, IHasColor.COLOR_KEY)) {
			g.fillPolygon(points);
		}

		if (r.setForegroundColor(g, t, IHasEdgeColor.EDGE_COLOR_KEY)) {
			if (arrowheadShape == ArrowheadShape.WEDGE) {
				g.drawPolyline(points);
			}
			else if (arrowheadShape == ArrowheadShape.TRIANGLE) {
				g.drawPolygon(points);
			}
		}
	}

	@Override
	public boolean isInThing(IBNAView view, ICoordinateMapper cm, ICoordinate location) {
		return false;
	}
	
	@Override
	public void getLocalBounds(IBNAView view, ICoordinateMapper cm, IResources r, Rectangle boundsResult) {
		boundsResult.setBounds(t.getAnchorPoint(), new Dimension(1,1));
		int arrowheadSize = t.getArrowheadSize();
		boundsResult.expand(arrowheadSize, arrowheadSize);
	}
}
