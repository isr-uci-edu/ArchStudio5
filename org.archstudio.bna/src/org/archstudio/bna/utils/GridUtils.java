package org.archstudio.bna.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasMinimumSize;
import org.archstudio.bna.facets.IHasMutableMinimumSize;
import org.archstudio.bna.facets.IRelativeMovable;
import org.archstudio.bna.things.utility.GridThing;
import org.archstudio.bna.utils.SplineUtils.SplineData;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public class GridUtils {

	private GridUtils() {
	}

	public static void rectifyToGrid(IBNAModel m) {
		int gridSpacing = GridUtils.getGridSpacing(m);
		if (gridSpacing == 0) {
			return;
		}

		for (IThing t : m.getAllThings()) {
			if (t instanceof IHasMutableEndpoints) {
				rectifyToGrid(gridSpacing, (IHasMutableEndpoints) t);
			}
			if (t instanceof IHasMutableMidpoints) {
				rectifyToGrid(gridSpacing, (IHasMutableMidpoints) t);
			}
			if (t instanceof IHasMinimumSize) {
				rectifyToGrid(gridSpacing, (IHasMinimumSize) t);
			}
			else if (t instanceof IRelativeMovable) {
				rectifyToGrid(gridSpacing, (IRelativeMovable) t);
			}
		}
	}

	public static void rectifyToGrid(int gridSpacing, IRelativeMovable t) {
		Point p = t.getReferencePoint();
		p = GridUtils.snapToGrid(gridSpacing, p);
		t.setReferencePoint(p);
	}

	public static void rectifyToGrid(int gridSpacing, IHasMinimumSize t) {
		Rectangle r = t.getBoundingBox();

		if (UserEditableUtils.isEditableForAllQualities(t, IRelativeMovable.USER_MAY_MOVE)) {
			r.x = GridUtils.snapToGrid(gridSpacing, r.x);
			r.y = GridUtils.snapToGrid(gridSpacing, r.y);
		}
		if (UserEditableUtils.isEditableForAllQualities(t, IHasMutableMinimumSize.USER_MAY_RESIZE)) {
			int x1 = r.x;
			int y1 = r.y;
			int x2 = r.x + r.width;
			int y2 = r.y + r.height;

			int nx1 = GridUtils.snapToGrid(gridSpacing, x1);
			int ny1 = GridUtils.snapToGrid(gridSpacing, y1);
			int nx2 = GridUtils.snapToGrid(gridSpacing, x2);
			int ny2 = GridUtils.snapToGrid(gridSpacing, y2);

			if (nx1 == nx2) {
				nx2 += gridSpacing;
			}
			if (ny1 == ny2) {
				ny2 += gridSpacing;
			}

			r.width = nx2 - nx1;
			r.height = ny2 - ny1;
		}

		t.setBoundingBox(r);
	}

	public static void rectifyToGrid(int gridSpacing, IHasMutableEndpoints t) {
		Point ep1 = t.getEndpoint1();
		Point nep1 = GridUtils.snapToGrid(gridSpacing, new Point(ep1.x, ep1.y));

		Point ep2 = t.getEndpoint2();
		Point nep2 = GridUtils.snapToGrid(gridSpacing, new Point(ep2.x, ep2.y));

		if (nep1.x == nep2.x && nep1.y == nep2.y) {
			nep2.x += gridSpacing;
			nep2.y += gridSpacing;
		}

		if (t instanceof IHasStickyEndpoints) {
			if (((IHasStickyEndpoints) t).getEndpoint1StuckToThingID() == null) {
				t.setEndpoint1(nep1);
			}
			if (((IHasStickyEndpoints) t).getEndpoint2StuckToThingID() == null) {
				t.setEndpoint2(nep2);
			}
		}
		else {
			t.setEndpoint1(nep1);
			t.setEndpoint2(nep2);
		}
	}

	public static void rectifyToGrid(int gridSpacing, IHasMutableMidpoints t) {
		SplineData sd = SplineUtils.getPoints(t);
		List<Point> newPoints = new ArrayList<Point>();

		Point lastPoint = null;
		for (Point p : sd.allPoints) {
			p.x = GridUtils.snapToGrid(gridSpacing, p.x);
			p.y = GridUtils.snapToGrid(gridSpacing, p.y);
			if (lastPoint == null || !lastPoint.equals(p)) {
				newPoints.add(p);
			}
			lastPoint = p;
		}

		sd.allPoints = newPoints;
		t.setMidpoints(sd.getMidpoints().toArray(new Point[0]));
	}

	public static GridThing getGridThing(IBNAModel m) {
		for (IThing t : m.getThings()) {
			if (t instanceof GridThing) {
				return (GridThing) t;
			}
		}
		GridThing gt = new GridThing(null);
		m.addThing(gt);
		m.sendToBack(Collections.singleton(gt));
		return gt;
	}

	public static int getGridSpacing(IBNAModel m) {
		GridThing gt = getGridThing(m);
		if (gt != null) {
			return gt.getGridSpacing();
		}
		return 0;
	}

	public static Point snapToGrid(int gridSpacing, Point p) {
		if (gridSpacing == 0) {
			return p;
		}
		return new Point(snapToGrid(gridSpacing, p.x), snapToGrid(gridSpacing, p.y));
	}

	public static int snapToGrid(int gridSpacing, int coord) {
		int diff = coord % gridSpacing;

		if (diff < gridSpacing / 2) {
			return coord - diff;
		}
		else {
			return coord + gridSpacing - diff;
		}
	}
}
