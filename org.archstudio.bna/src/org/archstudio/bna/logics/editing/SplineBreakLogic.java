package org.archstudio.bna.logics.editing;

import static org.archstudio.sysutils.SystemUtils.castOrNull;
import static org.archstudio.sysutils.SystemUtils.firstOrNull;

import java.awt.geom.Line2D;
import java.util.List;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.constants.MouseType;
import org.archstudio.bna.facets.IHasMutableMidpoints;
import org.archstudio.bna.facets.IHasMutablePoints;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.ui.IBNAMouseClickListener;
import org.archstudio.bna.utils.UserEditableUtils;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;

public class SplineBreakLogic extends AbstractThingLogic implements IBNAMouseClickListener {

	public SplineBreakLogic(IBNAWorld world) {
		super(world);
	}

	@Override
	synchronized public void mouseClick(IBNAView view, MouseType type, MouseEvent evt, List<IThing> things,
			ICoordinate location) {
		if (evt.count == 1) {
			final IHasMutablePoints t = castOrNull(firstOrNull(things), IHasMutablePoints.class);
			if (t != null
					&& UserEditableUtils.isEditableForAllQualities(t, IHasMutableMidpoints.USER_MAY_ADD_MIDPOINTS)) {

				// insert the new point
				boolean pointAdded = false;
				Point worldPoint = location.getWorldPoint();
				final IHasMutablePoints finalT = t;
				final List<Point> oldPoints = t.getPoints();
				final List<Point> newPoints = t.getPoints();
				for (int i = 1; i < newPoints.size(); i++) {
					Point p1 = newPoints.get(i - 1);
					Point p2 = newPoints.get(i);
					double dist = Line2D.ptSegDist(p2.x, p2.y, p1.x, p1.y, worldPoint.x, worldPoint.y);
					if (dist <= 5) {
						pointAdded = true;
						newPoints.add(i, new Point(worldPoint.x, worldPoint.y));
						break;
					}
				}

				// if a point wasn't added, do so now
				if (!pointAdded) {
					newPoints.add(new Point(worldPoint.x, worldPoint.y));
				}

				BNAOperations.runnable("Reshape", new Runnable() {

					@Override
					public void run() {
						finalT.setPoints(oldPoints);
					}
				}, new Runnable() {

					@Override
					public void run() {
						finalT.setPoints(newPoints);
					}
				}, true);
			}
		}
	}
}
