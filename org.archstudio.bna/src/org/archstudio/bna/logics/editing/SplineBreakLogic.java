package org.archstudio.bna.logics.editing;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;

import org.archstudio.bna.AbstractThingLogic;
import org.archstudio.bna.BNAUtils;
import org.archstudio.bna.IBNAMouseListener;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.ISplineReshapable;

public class SplineBreakLogic extends AbstractThingLogic implements IBNAMouseListener {

	public SplineBreakLogic() {
	}

	public void mouseUp(IBNAView view, MouseEvent evt, IThing t, int worldX, int worldY) {
	}

	public void mouseDown(IBNAView view, MouseEvent evt, IThing t, int worldX, int worldY) {
	}

	public void mouseClick(IBNAView view, MouseEvent evt, IThing t, int worldX, int worldY) {
	}

	public void mouseDoubleClick(IBNAView view, MouseEvent evt, IThing t, int worldX, int worldY) {
		if (t instanceof ISplineReshapable) {
			ISplineReshapable rt = (ISplineReshapable) t;
			if (rt.isUserEditable()) {
				int lastX = rt.getEndpoint1().x;
				int lastY = rt.getEndpoint1().y;

				Point[] midpoints = rt.getMidpoints();
				if (midpoints == null)
					midpoints = new Point[0];

				List<Point> midpointList = new ArrayList<Point>(midpoints.length);
				for (int i = 0; i < midpoints.length; i++) {
					midpointList.add(midpoints[i]);
				}
				for (int i = 0; i < midpoints.length; i++) {
					int x = midpoints[i].x;
					int y = midpoints[i].y;

					int dist = BNAUtils.round(Line2D.ptSegDist(x, y, lastX, lastY, worldX, worldY));
					if (dist <= 5) {
						midpointList.add(i, new Point(worldX, worldY));
						Point[] newmidpoints = midpointList.toArray(new Point[midpointList.size()]);
						rt.setMidpoints(newmidpoints);
						return;
					}
					lastX = x;
					lastY = y;
				}
				midpointList.add(new Point(worldX, worldY));
				Point[] newmidpoints = midpointList.toArray(new Point[midpointList.size()]);
				rt.setMidpoints(newmidpoints);
			}
		}
	}
}
