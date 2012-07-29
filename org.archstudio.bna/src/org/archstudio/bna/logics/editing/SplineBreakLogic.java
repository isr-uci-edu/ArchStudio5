package org.archstudio.bna.logics.editing;

import static org.archstudio.sysutils.SystemUtils.castOrNull;
import static org.archstudio.sysutils.SystemUtils.firstOrNull;

import java.awt.geom.Line2D;
import java.util.List;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasMutableMidpoints;
import org.archstudio.bna.facets.IHasMutablePoints;
import org.archstudio.bna.facets.IHasPoints;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.utils.IBNAMouseClickListener;
import org.archstudio.bna.utils.UserEditableUtils;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;

public class SplineBreakLogic extends AbstractThingLogic implements IBNAMouseClickListener {

	public SplineBreakLogic() {
	}

	@Override
	public void mouseClick(IBNAView view, MouseEvent evt, List<IThing> things, ICoordinate location) {
	}

	@Override
	public void mouseDoubleClick(IBNAView view, MouseEvent evt, List<IThing> things, final ICoordinate location) {
		final IHasMutablePoints t = castOrNull(firstOrNull(things), IHasMutablePoints.class);
		if (t != null && UserEditableUtils.isEditableForAllQualities(t, IHasMutableMidpoints.USER_MAY_ADD_MIDPOINTS)) {

			// insert the new point
			boolean pointAdded = false;
			Point worldPoint = location.getWorldPoint();
			final List<Point> points = t.getPoints();
			for (int i = 1; i < points.size(); i++) {
				Point p1 = points.get(i - 1);
				Point p2 = points.get(i);
				double dist = Line2D.ptSegDist(p2.x, p2.y, p1.x, p1.y, worldPoint.x, worldPoint.y);
				if (dist <= 5) {
					pointAdded = true;
					points.add(i, new Point(worldPoint.x, worldPoint.y));
					break;
				}
			}

			// if a point wasn't added, do so now
			if (!pointAdded) {
				points.add(new Point(worldPoint.x, worldPoint.y));
			}

			BNAOperations.set("Reshape", getBNAModel(), t, IHasPoints.POINTS_KEY, points);
		}
	}
}
