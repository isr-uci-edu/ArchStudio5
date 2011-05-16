package org.archstudio.bna.logics.editing;

import java.awt.geom.Line2D;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasMutableMidpoints;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.IBNAMouseListener;
import org.archstudio.bna.utils.SplineUtils;
import org.archstudio.bna.utils.SplineUtils.SplineData;
import org.archstudio.bna.utils.UserEditableUtils;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.events.MouseEvent;

public class SplineBreakLogic extends AbstractThingLogic implements IBNAMouseListener {

	public SplineBreakLogic() {
	}

	public void mouseUp(IBNAView view, MouseEvent evt, Iterable<IThing> t, Point localPoint, Point worldPoint) {
	}

	public void mouseDown(IBNAView view, MouseEvent evt, Iterable<IThing> t, Point localPoint, Point worldPoint) {
	}

	public void mouseClick(IBNAView view, MouseEvent evt, Iterable<IThing> t, Point localPoint, Point worldPoint) {
	}

	public void mouseDoubleClick(IBNAView view, MouseEvent evt, Iterable<IThing> t, Point localPoint, Point worldPoint) {
		if (t instanceof IHasMutableMidpoints
				&& UserEditableUtils.isEditableForAllQualities(t, IHasMutableMidpoints.USER_MAY_ADD_MIDPOINTS)) {

			SplineData sd = SplineUtils.getPoints(t);

			// insert the new point
			Point lastPoint = null;
			boolean pointAdded = false;
			for (int i = 0; i < sd.allPoints.size(); i++) {
				Point p = sd.allPoints.get(i);
				if (lastPoint != null) {
					int dist = BNAUtils.round(Line2D.ptSegDist(p.x, p.y, lastPoint.x, lastPoint.y, localPoint,
							worldPoint));
					if (dist <= 5) {
						pointAdded = true;
						sd.allPoints.add(i, new Point(localPoint, worldPoint));
						break;
					}
				}
				lastPoint = p;
			}

			// if a point wasn't added, do so now
			if (!pointAdded) {
				sd.getMidpoints().add(new Point(localPoint, worldPoint));
			}

			SplineUtils.setPoints(t, sd);
		}
	}
}
