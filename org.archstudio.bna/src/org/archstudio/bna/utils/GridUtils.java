package org.archstudio.bna.utils;

import java.awt.geom.Point2D;
import java.util.List;

import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.constants.GridDisplayType;
import org.archstudio.bna.facets.IHasMutableBoundingBox;
import org.archstudio.bna.facets.IHasMutableEndpoints;
import org.archstudio.bna.facets.IHasMutableMidpoints;
import org.archstudio.bna.facets.IHasMutableReferencePoint;
import org.archstudio.bna.facets.IHasMutableSize;
import org.archstudio.bna.facets.IHasReferencePoint;
import org.archstudio.bna.things.utility.GridThing;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class GridUtils {

	private GridUtils() {
	}

	public static void rectifyToGrid(IBNAWorld world) {
		int gridSpacing = GridUtils.getGridSpacing(world);
		if (gridSpacing == 0) {
			return;
		}

		for (IThing t : world.getBNAModel().getAllThings()) {
			if (t instanceof IHasMutableEndpoints) {
				rectifyToGrid(gridSpacing, (IHasMutableEndpoints) t);
			}
			if (t instanceof IHasMutableMidpoints
					&& UserEditableUtils.isEditableForAllQualities(t, IHasMutableMidpoints.USER_MAY_MOVE_MIDPOINTS)) {
				rectifyToGrid(gridSpacing, (IHasMutableMidpoints) t);
			}
			if (t instanceof IHasMutableBoundingBox
					&& UserEditableUtils.isEditableForAllQualities(t, IHasMutableSize.USER_MAY_RESIZE)) {
				rectifyToGrid(gridSpacing, (IHasMutableBoundingBox) t);
			}
			else if (t instanceof IHasMutableReferencePoint
					&& UserEditableUtils.isEditableForAllQualities(t, IHasMutableReferencePoint.USER_MAY_MOVE)) {
				rectifyToGrid(gridSpacing, (IHasMutableReferencePoint) t);
			}
		}
	}

	private static void rectifyToGrid(int gridSpacing, IHasReferencePoint t) {
		Point p = t.getReferencePoint();
		p = GridUtils.snapToGrid(gridSpacing, p);
		t.setReferencePoint(p);
	}

	private static void rectifyToGrid(int gridSpacing, IHasMutableBoundingBox t) {
		Rectangle r = t.getBoundingBox();

		if (UserEditableUtils.isEditableForAllQualities(t, IHasMutableReferencePoint.USER_MAY_MOVE)) {
			r.x = GridUtils.snapToGrid(gridSpacing, r.x);
			r.y = GridUtils.snapToGrid(gridSpacing, r.y);
		}
		if (UserEditableUtils.isEditableForAllQualities(t, IHasMutableSize.USER_MAY_RESIZE)) {
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

	private static void rectifyToGrid(int gridSpacing, IHasMutableEndpoints t) {
		Point2D ep1 = t.getEndpoint1();
		if (UserEditableUtils.isEditableForAllQualities(t, IHasMutableEndpoints.USER_MAY_MOVE_ENDPOINT_1)) {
			ep1 = GridUtils.snapToGrid(gridSpacing, ep1);
		}
		Point2D ep2 = t.getEndpoint2();
		if (UserEditableUtils.isEditableForAllQualities(t, IHasMutableEndpoints.USER_MAY_MOVE_ENDPOINT_2)) {
			ep2 = GridUtils.snapToGrid(gridSpacing, t.getEndpoint2());
		}
		if (Math.abs(ep2.getX() - ep1.getX()) / gridSpacing < 0.5
				&& Math.abs(ep1.getY() - ep1.getY()) / gridSpacing < 0.5) {
			if (UserEditableUtils.isEditableForAllQualities(t, IHasMutableEndpoints.USER_MAY_MOVE_ENDPOINT_1)) {
				ep1.setLocation(ep1.getX() - gridSpacing, ep1.getY() - gridSpacing);
			}
			if (UserEditableUtils.isEditableForAllQualities(t, IHasMutableEndpoints.USER_MAY_MOVE_ENDPOINT_2)) {
				ep2.setLocation(ep2.getX() + gridSpacing, ep2.getY() + gridSpacing);
			}
		}
		t.setEndpoint1(ep1);
		t.setEndpoint2(ep2);
	}

	private static void rectifyToGrid(int gridSpacing, IHasMutableMidpoints t) {
		List<Point2D> midPoints = t.getMidpoints();
		for (int i = 0; i < midPoints.size(); i++) {
			midPoints.set(i, GridUtils.snapToGrid(gridSpacing, midPoints.get(i)));
		}
		t.setMidpoints(midPoints);
	}

	public static int getGridSpacing(IBNAWorld world) {
		GridThing gridThing = GridThing.getIn(world);
		if (gridThing != null) {
			return gridThing.getGridSpacing();
		}
		return 0;
	}

	public static void setGridSpacing(IBNAWorld world, int gridSpacing) {
		GridThing gridThing = GridThing.createIn(world);
		if (gridThing != null) {
			gridThing.setGridSpacing(gridSpacing);
		}
	}

	public static GridDisplayType getGridDisplayType(IBNAWorld world) {
		GridThing gridThing = GridThing.getIn(world);
		if (gridThing != null) {
			return gridThing.getGridDisplayType();
		}
		return GridDisplayType.NONE;
	}

	public static void setGridDisplayType(IBNAWorld world, GridDisplayType gdt) {
		GridThing gridThing = GridThing.createIn(world);
		if (gridThing != null) {
			gridThing.setGridDisplayType(gdt);
		}
	}

	public static Point snapToGrid(int gridSpacing, Point p) {
		if (gridSpacing == 0) {
			return p;
		}
		return new Point(snapToGrid(gridSpacing, p.x), snapToGrid(gridSpacing, p.y));
	}

	private static int snapToGrid(int gridSpacing, int coord) {
		return (coord + gridSpacing / 2) / gridSpacing * gridSpacing;
	}

	public static Point2D snapToGrid(int gridSpacing, Point2D p) {
		if (gridSpacing == 0) {
			return p;
		}
		return new Point2D.Double(snapToGrid(gridSpacing, p.getX()), snapToGrid(gridSpacing, p.getY()));
	}

	private static double snapToGrid(int gridSpacing, double coord) {
		return Math.round((coord + gridSpacing / 2) / gridSpacing) * gridSpacing;
	}
}
