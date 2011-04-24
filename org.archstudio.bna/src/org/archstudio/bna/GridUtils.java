package org.archstudio.bna;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

import org.archstudio.bna.facets.IBoxReshapable;
import org.archstudio.bna.facets.IRelativeMovable;
import org.archstudio.bna.facets.ISplineReshapable;
import org.archstudio.bna.things.utility.GridThing;

public class GridUtils {

	private GridUtils() {
	}

	public static void rectifyToGrid(IBNAModel m) {
		int gridSpacing = GridUtils.getGridSpacing(m);
		if (gridSpacing == 0)
			return;

		for (IThing t : m.getAllThings()) {
			if (t instanceof IRelativeMovable) {
				if (t instanceof ISplineReshapable) {
					rectifyToGrid(gridSpacing, (ISplineReshapable) t);
				}
				else if (t instanceof IBoxReshapable) {
					rectifyToGrid(gridSpacing, (IBoxReshapable) t);
				}
				else {
					rectifyToGrid(gridSpacing, (IRelativeMovable) t);
				}
			}
			/*
			 * else if(t instanceof IHasMutableAnchorPoint){
			 * rectifyToGrid(gridSpacing, (IHasMutableAnchorPoint)t); } else
			 * if(t instanceof IHasMutableBoundingBox){
			 * rectifyToGrid(gridSpacing, (IHasMutableBoundingBox)t); }
			 */
		}
	}

	public static void rectifyToGrid(int gridSpacing, IRelativeMovable t) {
		Point p = t.getReferencePoint();
		p = GridUtils.snapToGrid(gridSpacing, p);
		t.setReferencePoint(p);
	}

	public static void rectifyToGrid(int gridSpacing, IBoxReshapable t) {
		rectifyToGrid(gridSpacing, t, true);
	}

	public static void rectifyToGrid(int gridSpacing, IBoxReshapable t, boolean bothCorners) {
		Rectangle r = t.getBoundingBox();

		if (!bothCorners) {
			int x1 = r.x;
			int y1 = r.y;

			int nx1 = GridUtils.snapToGrid(gridSpacing, x1);
			int ny1 = GridUtils.snapToGrid(gridSpacing, y1);

			t.setBoundingBox(new Rectangle(nx1, ny1, r.width, r.height));
		}
		else {
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

			t.setBoundingBox(new Rectangle(nx1, ny1, nx2 - nx1, ny2 - ny1));
		}
	}

	public static void rectifyToGrid(int gridSpacing, ISplineReshapable t) {
		Point ep1 = t.getEndpoint1();
		Point nep1 = GridUtils.snapToGrid(gridSpacing, new Point(ep1.x, ep1.y));

		Point ep2 = t.getEndpoint2();
		Point nep2 = GridUtils.snapToGrid(gridSpacing, new Point(ep2.x, ep2.y));

		if ((nep1.x == nep2.x) && (nep1.y == nep2.y)) {
			nep2.x += gridSpacing;
			nep2.y += gridSpacing;
		}

		t.setEndpoint1(nep1);
		t.setEndpoint2(nep2);

		Point[] midpoints = t.getMidpoints();
		if (midpoints != null) {
			List<Point> newMidpointList = new ArrayList<Point>();
			for (int i = 0; i < midpoints.length; i++) {
				Point nmp = GridUtils.snapToGrid(gridSpacing, midpoints[i]);
				if (i == 0) {
					if ((nmp.x != nep1.x) || (nmp.y != nep1.y)) {
						newMidpointList.add(nmp);
					}
				}
				else if (i == (midpoints.length - 1)) {
					if ((nmp.x != nep2.x) || (nmp.y != nep2.y)) {
						newMidpointList.add(nmp);
					}
				}
				else {
					if (newMidpointList.size() > 0) {
						Point lastnmp = newMidpointList.get(newMidpointList.size() - 1);
						if ((nmp.x != lastnmp.x) || (nmp.y != lastnmp.y)) {
							newMidpointList.add(nmp);
						}
					}
				}
			}
			Point[] newMidpoints = newMidpointList.toArray(new Point[newMidpointList.size()]);
			t.setMidpoints(newMidpoints);
		}
	}

	public static GridThing getGridThing(IBNAModel m) {
		for (IThing t : m.getAllThings()) {
			if (t instanceof GridThing) {
				return (GridThing) t;
			}
		}
		GridThing gt = new GridThing();
		m.addThing(gt);
		m.sendToBack(gt);
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
		if (gridSpacing == 0)
			return p;
		return new Point(snapToGrid(gridSpacing, p.x), snapToGrid(gridSpacing, p.y));
	}

	public static int snapToGrid(int gridSpacing, int coord) {
		int diff = coord % gridSpacing;

		if (diff < (gridSpacing / 2)) {
			return coord - diff;
		}
		else {
			return coord + (gridSpacing - diff);
		}
	}

}
