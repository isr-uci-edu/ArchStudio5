package org.archstudio.bna.utils;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Nullable;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IMutableCoordinateMapper;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.constants.GridDisplayType;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasLocalInsets;
import org.archstudio.bna.facets.IHasPoints;
import org.archstudio.bna.facets.IHasSelected;
import org.archstudio.bna.facets.IHasWorld;
import org.archstudio.bna.facets.peers.IHasInnerViewPeer;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.things.utility.EnvironmentPropertiesThing;
import org.archstudio.bna.things.utility.GridThing;
import org.archstudio.swtutils.SWTWidgetUtils;
import org.archstudio.swtutils.constants.Orientation;
import org.archstudio.sysutils.SystemUtils;
import org.archstudio.sysutils.UIDGenerator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Widget;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.primitives.Floats;

public class BNAUtils {

	@SuppressWarnings("unchecked")
	public static <T> T castOrNull(IThing thing, Class<T> thingClass) {
		if (thingClass.isInstance(thing)) {
			return (T) thing;
		}
		return null;
	}

	public static final Rectangle NONEXISTENT_RECTANGLE = new Rectangle(Integer.MIN_VALUE, Integer.MIN_VALUE, 0, 0);

	public static final IThingKey<Double> SCALE_KEY = ThingKey.create("scale");
	public static final IThingKey<Point> LOCAL_KEY = ThingKey.create("local");
	public static final IThingKey<Point> WORLD_KEY = ThingKey.create("world");

	public static final int round(double d) {
		return (int) Math.round(d);
	}

	public static final int round(float f) {
		return Math.round(f);
	}

	public static Rectangle normalizeRectangle(Rectangle rectangleResult) {
		if (rectangleResult.width < 0) {
			rectangleResult.x -= rectangleResult.width;
			rectangleResult.width = -rectangleResult.width;
		}
		if (rectangleResult.height < 0) {
			rectangleResult.y -= rectangleResult.height;
			rectangleResult.height = -rectangleResult.height;
		}
		return rectangleResult;
	}

	public static String generateUID(String prefix) {
		return UIDGenerator.generateUID(prefix);
	}

	public static boolean isWithin(Rectangle outsideRect, int x, int y) {
		Rectangle out = normalizeRectangle(outsideRect);
		int x1 = out.x;
		int x2 = out.x + out.width;
		int y1 = out.y;
		int y2 = out.y + out.height;

		return x >= x1 && x <= x2 && y >= y1 && y <= y2;
	}

	public static boolean isWithin(Rectangle outsideRect, Rectangle insideRect) {
		Rectangle in = normalizeRectangle(insideRect);

		return isWithin(outsideRect, in.x, in.y) && isWithin(outsideRect, in.x + in.width, in.y)
				&& isWithin(outsideRect, in.x, in.y + in.height)
				&& isWithin(outsideRect, in.x + in.width, in.y + in.height);
	}

	/**
	 * @deprecated Use {@link SystemUtils#nullEquals(Object, Object)} instead.
	 */
	@Deprecated
	public static final boolean nulleq(Object o1, Object o2) {
		return SystemUtils.nullEquals(o1, o2);
	}

	public static final Point clone(Point p) {
		return new Point(p.x, p.y);
	}

	public static final Rectangle clone(Rectangle r) {
		return new Rectangle(r.x, r.y, r.width, r.height);
	}

	public static final List<Point> worldToLocal(final ICoordinateMapper cm, List<Point> worldPoints) {
		return Lists.transform(worldPoints, new Function<Point, Point>() {

			public Point apply(Point input) {
				return cm.worldToLocal(new Point(input.x, input.y));
			}
		});
	}

	public static Dimension worldToLocalCornerSize(ICoordinateMapper cm, Rectangle worldRectangle,
			int worldCornerWidth, int worldCornerHeight) {
		Rectangle cornerWorldRect = new Rectangle(worldRectangle.x, worldRectangle.y, Math.min(worldCornerWidth,
				worldRectangle.width), Math.min(worldCornerHeight, worldRectangle.height));
		Rectangle localCornerWorldRect = cm.worldToLocal(cornerWorldRect);
		return new Dimension(localCornerWorldRect.width, localCornerWorldRect.height);
	}

	public static final List<Point> localToWorld(ICoordinateMapper cm, List<Point> localPointsResult) {
		for (Point p : localPointsResult) {
			cm.localToWorld(p);
		}
		return localPointsResult;
	}

	public static boolean wasControlPressed(MouseEvent evt) {
		return (evt.stateMask & SWT.CONTROL) != 0;
	}

	public static boolean wasShiftPressed(MouseEvent evt) {
		return (evt.stateMask & SWT.SHIFT) != 0;
	}

	public static boolean wasClick(MouseEvent downEvt, MouseEvent upEvt) {
		if (downEvt.button == upEvt.button) {
			int dx = upEvt.x - downEvt.x;
			int dy = upEvt.y - downEvt.y;
			if (dx == 0 && dy == 0) {
				return true;
			}
		}
		return false;
	}

	public static void setRectangle(Rectangle r, int x, int y, int width, int height) {
		r.x = x;
		r.y = y;
		r.width = width;
		r.height = height;
	}

	public static Rectangle createAlignedRectangle(Point p, int width, int height, Orientation o) {
		Rectangle r = new Rectangle(0, 0, 0, 0);
		alignRectangle(r, p, width, height, o);
		return r;
	}

	public static void alignRectangle(Rectangle r, Point p, int width, int height, Orientation o) {
		switch (o) {
		case NONE:
			setRectangle(r, p.x - width / 2, p.y - height / 2, width, height);
			break;
		case NORTHWEST:
			setRectangle(r, p.x - width, p.y - height, width, height);
			break;
		case NORTH:
			setRectangle(r, p.x - width / 2, p.y - height, width, height);
			break;
		case NORTHEAST:
			setRectangle(r, p.x, p.y - height, width, height);
			break;
		case EAST:
			setRectangle(r, p.x, p.y - height / 2, width, height);
			break;
		case SOUTHEAST:
			setRectangle(r, p.x, p.y, width, height);
			break;
		case SOUTH:
			setRectangle(r, p.x - width / 2, p.y, width, height);
			break;
		case SOUTHWEST:
			setRectangle(r, p.x - width, p.y, width, height);
			break;
		case WEST:
			setRectangle(r, p.x - width, p.y - height / 2, width, height);
			break;
		}
	}

	private static float[] deg2rad = null;

	public static float degreesToRadians(int degrees) {
		while (degrees < 0) {
			degrees += 360;
		}
		degrees = degrees % 360;
		if (deg2rad == null) {
			deg2rad = new float[360];
			for (int i = 0; i < 360; i++) {
				deg2rad[i] = i * (float) Math.PI / 180f;
			}
		}
		return deg2rad[degrees];
	}

	public static Point[] toPointArray(int[] points) {
		Point[] pa = new Point[points.length / 2];
		for (int i = 0; i < points.length; i += 2) {
			pa[i / 2] = new Point(points[i], points[i + 1]);
		}
		return pa;
	}

	public static int[] createIsocolesTriangle(Rectangle sbb, Orientation facing) {
		int ft = 6;
		int fb = 16;
		int x1 = sbb.x;
		int y1 = sbb.y;
		int xm = sbb.x + sbb.width / 2;
		int ym = sbb.y + sbb.height / 2;
		int xq = x1 + sbb.width * ft / fb;
		int yq = y1 + sbb.height * ft / fb;
		int xqg = x1 + sbb.width - sbb.width * ft / fb;
		int yqg = y1 + sbb.height - sbb.height * ft / fb;
		int x2 = x1 + sbb.width;
		int y2 = y1 + sbb.height;

		int px1, px2, px3;
		int py1, py2, py3;

		switch (facing) {
		case NORTHWEST:
			px1 = xq;
			py1 = y2;
			px2 = x1;
			py2 = y1;
			px3 = x2;
			py3 = yq;
			break;
		case NORTH:
			px1 = x1;
			py1 = y2;
			px2 = xm;
			py2 = y1;
			px3 = x2;
			py3 = y2;
			break;
		case NORTHEAST:
			px1 = x1;
			py1 = yq;
			px2 = x2;
			py2 = y1;
			px3 = xqg;
			py3 = y2;
			break;
		case EAST:
			px1 = x1;
			py1 = y1;
			px2 = x2;
			py2 = ym;
			px3 = x1;
			py3 = y2;
			break;
		case SOUTHEAST:
			px1 = xqg;
			py1 = y1;
			px2 = x2;
			py2 = y2;
			px3 = x1;
			py3 = yqg;
			break;
		case SOUTH:
			px1 = x1;
			py1 = y1;
			px2 = xm;
			py2 = y2;
			px3 = x2;
			py3 = y1;
			break;
		case SOUTHWEST:
			px1 = xq;
			py1 = y1;
			px2 = x1;
			py2 = y2;
			px3 = x2;
			py3 = yqg;
			break;
		case WEST:
			px1 = x2;
			py1 = y1;
			px2 = x1;
			py2 = ym;
			px3 = x2;
			py3 = y2;
			break;
		default:
			throw new IllegalArgumentException("Invalid facing");
		}

		return new int[] { px1, py1, px2, py2, px3, py3 };
	}

	public static Rectangle insetRectangle(Rectangle r, Rectangle insets) {
		Rectangle i = new Rectangle(r.x + insets.x, r.y + insets.y, r.width + insets.width, r.height + insets.height);
		if (i.width < 0) {
			return null;
		}
		if (i.height < 0) {
			return null;
		}
		if (!r.contains(i.x, i.y)) {
			return null;
		}
		return i;
	}

	public static boolean isEdgePoint(Point p, Rectangle r) {
		int x2 = r.x + r.width;
		int y2 = r.y + r.height;
		if (p.x == r.x && p.y >= r.y && p.y <= y2) {
			// It's on the left rail
			return true;
		}
		if (p.x == x2 && p.y >= r.y && p.y <= y2) {
			// It's on the right rail
			return true;
		}
		if (p.y == r.y && p.x >= r.x && p.x <= x2) {
			// it's on the top rail
			return true;
		}
		if (p.y == y2 && p.x >= r.x && p.x <= x2) {
			// it's on the bottom rail
			return true;
		}
		return false;
	}

	public static Orientation getOrientationOfEdgePoint(Point p, Rectangle r) {
		int x2 = r.x + r.width;
		int y2 = r.y + r.height;
		if (p.x == r.x && p.y == r.y) {
			return Orientation.NORTHWEST;
		}
		else if (p.x == r.x && p.y == y2) {
			return Orientation.SOUTHWEST;
		}
		else if (p.x == x2 && p.y == r.y) {
			return Orientation.NORTHEAST;
		}
		else if (p.x == x2 && p.y == y2) {
			return Orientation.SOUTHEAST;
		}
		else if (p.x == r.x && p.y >= r.y && p.y <= y2) {
			return Orientation.WEST;
		}
		if (p.x == x2 && p.y >= r.y && p.y <= y2) {
			return Orientation.EAST;
		}
		if (p.y == r.y && p.x >= r.x && p.x <= x2) {
			return Orientation.NORTH;
		}
		if (p.y == y2 && p.x >= r.x && p.x <= x2) {
			return Orientation.SOUTH;
		}

		// it's not on a rail
		return Orientation.NONE;

	}

	public static Point findClosestEdgePoint(Point p, Rectangle r) {
		Point np = new Point(p.x, p.y);

		boolean midx = false;
		boolean midy = false;

		if (p.x < r.x) {
			// It's to the left of the rectangle
			np.x = r.x;
		}
		else if (p.x < r.x + r.width) {
			// It's in the middle of the rectangle
			midx = true;
		}
		else {
			// It's beyond the right of the rectangle
			np.x = r.x + r.width;
		}

		if (p.y < r.y) {
			np.y = r.y;
		}
		else if (p.y < r.y + r.height) {
			midy = true;
		}
		else {
			np.y = r.y + r.height;
		}

		if (midx && midy) {
			// It was within the rectangle
			int dl = Math.abs(p.x - r.x);
			int dr = Math.abs(p.x - (r.x + r.width));
			int dt = Math.abs(p.y - r.y);
			int db = Math.abs(p.y - (r.y + r.height));

			if (dt <= db && dt <= dl && dt <= dr) {
				// it's closest to the top rail.
				np.y = r.y;
				return np;
			}
			else if (db <= dt && db <= dl && db <= dr) {
				// it's closest to the bottom rail
				np.y = r.y + r.height;
				return np;
			}
			else if (dl <= dt && dl <= db && dl <= dr) {
				// it's closest to the left rail
				np.x = r.x;
				return np;
			}
			else {
				np.x = r.x + r.width;
				return np;
			}
		}
		return np;
	}

	public static Point scaleAndMoveBorderPoint(Point p, Rectangle oldRect, Rectangle newRect) {
		if (oldRect == null || newRect == null) {
			return new Point(p.x, p.y);
		}

		//int ox1 = oldRect.x;
		//int ox2 = oldRect.x + oldRect.width;
		//int oy1 = oldRect.y;
		//int oy2 = oldRect.y + oldRect.height;
		int ow = oldRect.width;
		int oh = oldRect.height;

		//int nx1 = newRect.x;
		//int nx2 = newRect.x + newRect.width;
		//int ny1 = newRect.y;
		//int ny2 = newRect.y + newRect.height;
		int nw = newRect.width;
		int nh = newRect.height;

		int dw = nw - ow;
		int dh = nh - oh;

		double sx = (double) nw / (double) ow;
		double sy = (double) nh / (double) oh;

		//int dx = nx1 - ox1;
		//int dy = ny1 - oy1;

		Point p2 = new Point(p.x, p.y);

		if (p.y == oldRect.y) {
			// It's on the top rail

			// Keep it on the top rail
			p2.y = newRect.y;

			// Old distance from the left rail
			int dist = p.x - oldRect.x;

			if (dw != 0) {
				// Scale that distance
				dist = BNAUtils.round(dist * sx);
			}

			// Also perform translation
			p2.x = newRect.x + dist;
		}
		else if (p.y == oldRect.y + oldRect.height // - 1
				|| p.y == oldRect.y + oldRect.height) {
			// It's on the bottom rail

			// Keep it on the bottom rail
			p2.y = newRect.y + newRect.height/* - 1 */;

			// Old distance from the left rail
			int dist = p.x - oldRect.x;

			if (dw != 0) {
				// Scale that distance
				dist = BNAUtils.round(dist * sx);
			}

			// Also perform translation
			p2.x = newRect.x + dist;
		}
		else if (p.x == oldRect.x) {
			// It's on the left rail

			// Keep it on the left rail
			p2.x = newRect.x;

			// Old distance from the top rail
			int dist = p.y - oldRect.y;

			if (dh != 0) {
				// Scale that distance
				dist = BNAUtils.round(dist * sy);
			}

			// Also perform translation
			p2.y = newRect.y + dist;
		}
		else if (p.x == oldRect.x + oldRect.width // - 1
				|| p.x == oldRect.x + oldRect.width) {
			// It's on the right rail

			// Keep it on the right rail
			p2.x = newRect.x + newRect.width/* - 1 */;

			// Old distance from the top rail
			int dist = p.y - oldRect.y;

			if (dh != 0) {
				// Scale that distance
				dist = BNAUtils.round(dist * sy);
			}

			// Also perform translation
			p2.y = newRect.y + dist;
		}

		// Normalize
		if (p2.x < newRect.x) {
			p2.x = newRect.x;
		}
		if (p2.x >= newRect.x + newRect.width) {
			p2.x = newRect.x + newRect.width/* - 1 */;
		}
		if (p2.y < newRect.y) {
			p2.y = newRect.y;
		}
		if (p2.y >= newRect.y + newRect.height) {
			p2.y = newRect.y + newRect.height/* - 1 */;
		}

		return p2;
	}

	public static RGB getRGBForSystemColor(Device d, int systemColorID) {
		Color c = d.getSystemColor(systemColorID);
		if (c == null) {
			return null;
		}
		return c.getRGB();
	}

	public static boolean isPointOnRectangle(Point p, Rectangle r) {
		return isPointOnRectangle(p.x, p.y, r.x, r.y, r.width, r.height);
	}

	public static boolean isPointOnRectangle(int x, int y, int rx, int ry, int rw, int rh) {
		if (x == rx || x == rx + rw) {
			if (y >= ry && y <= ry + rh) {
				return true;
			}
		}
		if (y == ry || y == ry + rh) {
			if (x >= rx && x <= rx + rw) {
				return true;
			}
		}
		return false;
	}

	public static class PointToRectangleDistanceData {

		public Orientation closestSide;
		public double dist;
	}

	public static PointToRectangleDistanceData getPointToRectangleDistance(Point p, Rectangle r) {
		double closestDist = Double.MAX_VALUE;
		Orientation closestSide = Orientation.NONE;

		double dist;
		// Check north distance
		dist = Line2D.ptSegDist(r.x, r.y, r.x + r.width, r.y, p.x, p.y);
		if (dist < closestDist) {
			closestDist = dist;
			closestSide = Orientation.NORTH;
		}
		dist = Line2D.ptSegDist(r.x, r.y, r.x, r.y + r.height, p.x, p.y);
		if (dist < closestDist) {
			closestDist = dist;
			closestSide = Orientation.WEST;
		}
		dist = Line2D.ptSegDist(r.x + r.width, r.y, r.x + r.width, r.y + r.height, p.x, p.y);
		if (dist < closestDist) {
			closestDist = dist;
			closestSide = Orientation.EAST;
		}
		dist = Line2D.ptSegDist(r.x, r.y + r.height, r.x + r.width, r.y + r.height, p.x, p.y);
		if (dist < closestDist) {
			closestDist = dist;
			closestSide = Orientation.SOUTH;
		}
		PointToRectangleDistanceData dd = new PointToRectangleDistanceData();
		dd.closestSide = closestSide;
		dd.dist = closestDist;
		return dd;
	}

	public static EnvironmentPropertiesThing getEnvironmentPropertiesThing(IBNAModel m) {
		EnvironmentPropertiesThing ept = (EnvironmentPropertiesThing) m
				.getThing(EnvironmentPropertiesThing.ENVIRONMENT_PROPERTIES_THING_ID);
		if (ept == null) {
			m.addThing(ept = new EnvironmentPropertiesThing());
		}
		return ept;
	}

	//	public static Rectangle clone(Rectangle r) {
	//		return r == null ? null : new Rectangle(r.x, r.y, r.width, r.height);
	//	}
	//
	//	public static final Point clone(Point p) {
	//		return p == null ? null : new Point(p.x, p.y);
	//	}
	//
	//	public static final Point[] clone(Point[] points) {
	//		if (points == null) {
	//			return null;
	//		}
	//		Point[] newPoints = new Point[points.length];
	//		for (int i = 0; i < points.length; i++) {
	//			newPoints[i] = clone(points[i]);
	//		}
	//		return newPoints;
	//	}

	/**
	 * @deprecated Use {@link SWTWidgetUtils#async(Widget,Runnable)} instead
	 */
	@Deprecated
	public static void asyncExec(final Widget w, final Runnable r) {
		SWTWidgetUtils.async(w, r);
	}

	/**
	 * @deprecated Use {@link SWTWidgetUtils#async(Display,Runnable)} instead
	 */
	@Deprecated
	public static void asyncExec(final Display d, final Runnable r) {
		SWTWidgetUtils.async(d, r);
	}

	/**
	 * @deprecated Use {@link SWTWidgetUtils#sync(Widget,Runnable)} instead
	 */
	@Deprecated
	public static void syncExec(final Widget w, final Runnable r) {
		SWTWidgetUtils.sync(w, r);
	}

	/**
	 * @deprecated Use {@link SWTWidgetUtils#sync(Display,Runnable)} instead
	 */
	@Deprecated
	public static void syncExec(final Display d, final Runnable r) {
		SWTWidgetUtils.sync(d, r);
	}

	//	public static Widget getParentsComposite(IBNAView view) {
	//		if (view == null) {
	//			return null;
	//		}
	//		Composite c = view.getParentComposite();
	//		if (c != null) {
	//			return c;
	//		}
	//		return getParentComposite(view.getParentView());
	//	}

	public static @Nullable
	Point getCentralPoint(IThing t) {
		if (t instanceof IHasPoints) {
			List<Point> points = ((IHasPoints) t).getPoints();
			int x1 = Integer.MAX_VALUE;
			int y1 = Integer.MAX_VALUE;
			int x2 = Integer.MIN_VALUE;
			int y2 = Integer.MIN_VALUE;
			for (Point p : points) {
				x1 = Math.min(x1, p.x);
				x2 = Math.max(x2, p.x);
				y1 = Math.min(y1, p.y);
				y2 = Math.max(y2, p.y);
			}
			Point p = new Point((x1 + x2) / 2, (y1 + y2) / 2);
			if (t instanceof IHasAnchorPoint) {
				Point a = ((IHasAnchorPoint) t).getAnchorPoint();
				p.x += a.x;
				p.y += a.y;
			}
			return p;
		}
		if (t instanceof IHasAnchorPoint) {
			return ((IHasAnchorPoint) t).getAnchorPoint();
		}
		if (t instanceof IHasBoundingBox) {
			Rectangle r = ((IHasBoundingBox) t).getBoundingBox();
			return new Point(r.x + r.width / 2, r.y + r.height / 2);
		}
		return null;
	}

	private static final Predicate<IThing> isSelectedPredicate = new Predicate<IThing>() {

		public boolean apply(IThing t) {
			if (t instanceof IHasSelected) {
				return ((IHasSelected) t).isSelected();
			}
			return false;
		};
	};

	public static final Collection<IThing> getSelectedThings(IBNAModel m) {
		return Collections2.filter(m.getAllThings(), isSelectedPredicate);
	}

	public static final int sizeOfSelectedThings(IBNAModel m) {
		return Iterables.size(getSelectedThings(m));
	}

	public static void setGridSpacing(IBNAModel m, int gridSpacing) {
		GridThing gt = GridUtils.getGridThing(m);
		if (gt != null) {
			gt.setGridSpacing(gridSpacing);
		}
	}

	public static void setGridDisplayType(IBNAModel m, GridDisplayType gdt) {
		GridThing gt = GridUtils.getGridThing(m);
		if (gt != null) {
			gt.setGridDisplayType(gdt);
		}
	}

	public static void saveCoordinateMapperData(ICoordinateMapper cm, EnvironmentPropertiesThing ept) {
		ept.set(SCALE_KEY, cm.getLocalScale());
		ept.set(LOCAL_KEY, cm.getLocalOrigin());
		ept.set(WORLD_KEY, cm.localToWorld(cm.getLocalOrigin()));
	}

	public static void restoreCoordinateMapperData(IMutableCoordinateMapper cm, EnvironmentPropertiesThing ept) {
		try {
			cm.setLocalScaleAndAlign(ept.get(SCALE_KEY), ept.get(LOCAL_KEY), ept.get(WORLD_KEY));
		}
		catch (Exception e) {
		}
	}

	public static boolean infinitelyRecurses(IBNAView view) {
		IBNAWorld world = view.getBNAWorld();
		if (world == null) {
			return false;
		}

		IBNAView view2 = view.getParentView();
		while (view2 != null) {
			if (world.equals(view2.getBNAWorld())) {
				return true;
			}
			view2 = view2.getParentView();
		}
		return false;
	}

	/**
	 * Determines a point that represents the same relative location on new
	 * bounding box, as the old point represented on the old bounding box. For
	 * example, if the old point was midway down the left edge of the old
	 * bounding box, the new point will be midway down the left edge of the new
	 * bounding box. Likewise, if the old point was in the center of the old
	 * bounding box, the new point will be in the center of the new bounding
	 * box.
	 */
	public static Point movePointWith(Rectangle oldBoundingBox, Rectangle newBoundingBox, Point oldPoint) {
		if (oldBoundingBox != null && newBoundingBox != null && oldPoint != null) {
			int dx;
			if (oldPoint.x <= oldBoundingBox.x) {
				dx = newBoundingBox.x - oldBoundingBox.x;
			}
			else if (oldPoint.x >= oldBoundingBox.x + oldBoundingBox.width) {
				dx = newBoundingBox.x + newBoundingBox.width - (oldBoundingBox.x + oldBoundingBox.width);
			}
			else {
				float fx = (oldPoint.x - (float) oldBoundingBox.x) / oldBoundingBox.width;
				int nx = Math.round(fx * newBoundingBox.width) + newBoundingBox.x;
				dx = nx - oldPoint.x;
			}

			int dy;
			if (oldPoint.y <= oldBoundingBox.y) {
				dy = newBoundingBox.y - oldBoundingBox.y;
			}
			else if (oldPoint.y >= oldBoundingBox.y + oldBoundingBox.height) {
				dy = newBoundingBox.y + newBoundingBox.height - (oldBoundingBox.y + oldBoundingBox.height);
			}
			else {
				float fy = (oldPoint.y - (float) oldBoundingBox.y) / oldBoundingBox.height;
				int ny = Math.round(fy * newBoundingBox.height) + newBoundingBox.y;
				dy = ny - oldPoint.y;
			}

			return new Point(oldPoint.x + dx, oldPoint.y + dy);
		}
		return oldPoint;
	}

	public static float getDistance(Point p1, Point p2) {
		if (p1 != null && p2 != null) {
			int dx = p2.x - p1.x;
			int dy = p2.y - p1.y;
			return (float) Math.sqrt(dx * dx + dy * dy);
		}
		return Float.POSITIVE_INFINITY;
	}

	public static Point getClosestPointOnPolygon(int[] xyCoords, final int nearX, final int nearY, final int refX,
			final int refY) {

		if (nearX == refX && nearY == refY) {
			return new Point(refX, refY);
		}

		// convert to a polygon
		Polygon polygon = new Polygon();
		for (int i = 0; i < xyCoords.length; i += 2) {
			polygon.addPoint(xyCoords[i], xyCoords[i + 1]);
		}

		// search for the point closest to (nearX,nearY) that intersects the referenceLine
		Line2D referenceLine = new Line2D.Double(refX, refY, nearX, nearY);
		double[] pathCoords = new double[6];
		double closestDistanceSq = Double.POSITIVE_INFINITY;
		Point closestIntersection = new Point(refX, refY);
		{
			Point2D lastMoveTo = null;
			Point2D lastPoint = null;
			for (PathIterator i = polygon.getPathIterator(new AffineTransform()); !i.isDone(); i.next()) {
				Line2D lineSegment = null;
				switch (i.currentSegment(pathCoords)) {
				case PathIterator.SEG_MOVETO:
					lastMoveTo = new Point2D.Double(pathCoords[0], pathCoords[1]);
					lastPoint = lastMoveTo;
					continue;
				case PathIterator.SEG_CLOSE:
					lineSegment = new Line2D.Double(lastPoint, lastMoveTo);
					break;
				case PathIterator.SEG_LINETO:
					Point2D lineTo = new Point2D.Double(pathCoords[0], pathCoords[1]);
					lineSegment = new Line2D.Double(lastPoint, lineTo);
					lastPoint = lineTo;
					break;
				default:
					throw new UnsupportedOperationException();
				}

				Point2D intersection = getLineIntersection(lineSegment, referenceLine);
				if (lineSegment.ptSegDistSq(intersection) < 0.0000001d) {
					double distanceSq = intersection.distanceSq(nearX, nearY);
					if (distanceSq < closestDistanceSq) {
						closestDistanceSq = distanceSq;
						closestIntersection = new Point(round(intersection.getX()), round(intersection.getY()));
					}
				}
			}
		}

		return closestIntersection;
	}

	private static Point2D.Double getLineIntersection(Line2D l, Line2D r) {
		// see: http://en.wikipedia.org/wiki/Line-line_intersection
		double x1 = l.getX1();
		double y1 = l.getY1();
		double x2 = l.getX2();
		double y2 = l.getY2();
		double x3 = r.getX1();
		double y3 = r.getY1();
		double x4 = r.getX2();
		double y4 = r.getY2();
		double x1y2 = x1 * y2;
		double y1x2 = y1 * x2;
		double x3y4 = x3 * y4;
		double y3x4 = y3 * x4;
		double xNumerator = (x1y2 - y1x2) * (x3 - x4) - (x1 - x2) * (x3y4 - y3x4);
		double yNumerator = (x1y2 - y1x2) * (y3 - y4) - (y1 - y2) * (x3y4 - y3x4);
		double denominator = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
		return new Point2D.Double(xNumerator / denominator, yNumerator / denominator);
	}

	private static Point getClosestPointOnLineSegment(Line2D l, final int x3, final int y3) {
		// see: http://paulbourke.net/geometry/pointline/
		double x1 = l.getX1();
		double y1 = l.getY1();
		double x2 = l.getX2();
		double y2 = l.getY2();
		double u = ((x3 - x1) * (x2 - x1) + (y3 - y1) * (y2 - y1)) / l.getP2().distanceSq(l.getP1());
		double x = x1 + u * (x2 - x1);
		double y = y1 + u * (y2 - y1);
		Point2D p1 = l.getP1();
		Point2D p2 = l.getP2();

		// determine if the calculated intersection is between the segment points
		if (l.ptSegDistSq(x, y) < 0.0000001d) {
			return new Point(round(x), round(y));
		}
		// its not, so use the closest end point
		double dp1 = p1.distanceSq(x3, y3);
		double dp2 = p2.distanceSq(x3, y3);
		Point2D p = dp1 < dp2 ? p1 : p2;
		return new Point(round(p.getX()), round(p.getY()));
	}

	public static Point getClosestPointOnPolygon(int[] xyCoords, final int nearX, final int nearY) {

		// convert to a polygon
		Polygon polygon = new Polygon();
		for (int i = 0; i < xyCoords.length; i += 2) {
			polygon.addPoint(xyCoords[i], xyCoords[i + 1]);
		}

		// search for the closest line segment
		double[] pathCoords = new double[6];
		double closestDistanceSq = Double.POSITIVE_INFINITY;
		Line2D closestLineSeg = null;
		{
			Point2D lastMoveTo = null;
			Point2D lastPoint = null;
			for (PathIterator i = polygon.getPathIterator(new AffineTransform()); !i.isDone(); i.next()) {
				Line2D lineSegment = null;
				switch (i.currentSegment(pathCoords)) {
				case PathIterator.SEG_MOVETO:
					lastMoveTo = new Point2D.Double(pathCoords[0], pathCoords[1]);
					lastPoint = lastMoveTo;
					continue;
				case PathIterator.SEG_CLOSE:
					lineSegment = new Line2D.Double(lastPoint, lastMoveTo);
					break;
				case PathIterator.SEG_LINETO:
					Point2D lineTo = new Point2D.Double(pathCoords[0], pathCoords[1]);
					lineSegment = new Line2D.Double(lastPoint, lineTo);
					lastPoint = lineTo;
					break;
				default:
					throw new UnsupportedOperationException();
				}

				double distanceSq = lineSegment.ptSegDistSq(nearX, nearY);
				if (distanceSq < closestDistanceSq) {
					closestDistanceSq = distanceSq;
					closestLineSeg = lineSegment;
				}
			}
		}

		return getClosestPointOnLineSegment(closestLineSeg, nearX, nearY);
	}

	public static final Point getClosestPointOnEllipse(Rectangle r, int nearX, int nearY) {
		// normalize to a circle at (0,0) with a radius of 0.5
		double npx = (double) (nearX - r.x) / r.width - 0.5d;
		double npy = (double) (nearY - r.y) / r.height - 0.5d;

		// y = mx + b, b = 0;
		double nM = npy / npx;
		double nM2 = nM * nM;

		// x^2 + y^2 = r^2, r = 0.5
		double nx = Math.sqrt(0.25d / (1d + nM2));
		double ny = Math.sqrt(0.25d - nx * nx);

		// un-normalize results
		double x1 = ((npx < 0 ? -nx : nx) + 0.5d) * r.width + r.x;
		double y1 = ((npy < 0 ? -ny : ny) + 0.5d) * r.height + r.y;

		return new Point(round(x1), round(y1));
	}

	public static final Point getClosestPointOnEllipse(Rectangle r, int nearX, int nearY, int referenceX, int referenceY) {
		if (nearX != referenceX) {
			// normalize to a circle at (0,0) with a radius of 0.5
			double npx = (double) (nearX - r.x) / r.width - 0.5d;
			double npy = (double) (nearY - r.y) / r.height - 0.5d;
			double nrx = (double) (referenceX - r.x) / r.width - 0.5d;
			double nry = (double) (referenceY - r.y) / r.height - 0.5d;

			// y = mx + b
			double nM = (npy - nry) / (npx - nrx);
			double nB = nry - nM * nrx;
			double nM2 = nM * nM;
			double nB2 = nB * nB;

			// quadratic formula
			double QA = 1d + nM2;
			double QB = 2 * nM * nB;
			double QC = nB2 - 0.25;

			double b24ac = QB * QB - 4d * QA * QC;
			if (b24ac >= 0) {
				double sqrt = Math.sqrt(b24ac);
				double nx1 = (-QB + sqrt) / 2d / QA;
				double nx2 = (-QB - sqrt) / 2d / QA;

				double nx = npx < 0 ? Math.min(nx1, nx2) : Math.max(nx1, nx2);
				double ny = Math.sqrt(0.25d - nx * nx);

				// un-normalize results
				double x1 = (nx + 0.5d) * r.width + r.x;
				double y1 = ((npy < 0 ? -ny : ny) + 0.5d) * r.height + r.y;

				return new Point(round(x1), round(y1));
			}
		}
		else {
			return getClosestPointOnEllipse(r, nearX, nearY);
		}

		throw new IllegalArgumentException("This shouldn't happen");
	}

	public static Point getClosestPointOnRectangle(Rectangle rectangle, Dimension cornerSize, Point nearPoint) {
		int x1 = rectangle.x;
		int y1 = rectangle.y;
		int x2 = x1 + rectangle.width;
		int y2 = y1 + rectangle.height;
		Point closestPoint = BNAUtils.getClosestPointOnPolygon(new int[] { x1, y1, x2, y1, x2, y2, x1, y2, x1, y1 },
				nearPoint.x, nearPoint.y);
		if (cornerSize.width != 0 || cornerSize.height != 0) {
			int cornerWidth = cornerSize.width;
			int cornerHeight = cornerSize.height;
			if (closestPoint.x < rectangle.x + cornerWidth / 2
					|| closestPoint.x > rectangle.x + rectangle.width - cornerWidth / 2) {
				if (closestPoint.y < rectangle.y + cornerHeight / 2
						|| closestPoint.y > rectangle.y + rectangle.height - cornerHeight / 2) {
					int cornerX = nearPoint.x < rectangle.x + rectangle.width / 2 ? rectangle.x : rectangle.x
							+ rectangle.width - cornerWidth;
					int cornerY = nearPoint.y < rectangle.y + rectangle.height / 2 ? rectangle.y : rectangle.y
							+ rectangle.height - cornerHeight;
					Rectangle cornerR = new Rectangle(cornerX, cornerY, cornerWidth, cornerHeight);
					closestPoint = BNAUtils.getClosestPointOnEllipse(cornerR, nearPoint.x, nearPoint.y);
				}
			}
		}
		return closestPoint;
	}

	public static Point getClosestPointOnRectangle(Rectangle rectangle, Dimension cornerSize, Point nearPoint,
			Point referencePoint) {
		int x1 = rectangle.x;
		int y1 = rectangle.y;
		int x2 = x1 + rectangle.width;
		int y2 = y1 + rectangle.height;
		Point closestPoint = BNAUtils.getClosestPointOnPolygon(new int[] { x1, y1, x2, y1, x2, y2, x1, y2, x1, y1 },
				nearPoint.x, nearPoint.y, referencePoint.x, referencePoint.y);
		if (cornerSize.width != 0 || cornerSize.height != 0) {
			int cornerWidth = cornerSize.width;
			int cornerHeight = cornerSize.height;
			if (closestPoint.x < rectangle.x + cornerWidth / 2
					|| closestPoint.x > rectangle.x + rectangle.width - cornerWidth / 2) {
				if (closestPoint.y < rectangle.y + cornerHeight / 2
						|| closestPoint.y > rectangle.y + rectangle.height - cornerHeight / 2) {
					int cornerX = nearPoint.x < referencePoint.x ? rectangle.x : rectangle.x + rectangle.width
							- cornerWidth;
					int cornerY = nearPoint.y < referencePoint.y ? rectangle.y : rectangle.y + rectangle.height
							- cornerHeight;
					Rectangle cornerR = new Rectangle(cornerX, cornerY, cornerWidth, cornerHeight);
					closestPoint = BNAUtils.getClosestPointOnEllipse(cornerR, nearPoint.x, nearPoint.y,
							referencePoint.x, referencePoint.y);
				}
			}
		}
		return closestPoint;
	}

	public static final IBNAView getInternalView(IBNAView outerView, IThing worldThing) {
		if (worldThing instanceof IHasWorld) {
			IThingPeer<?> worldThingPeer = outerView.getThingPeer(worldThing);
			if (worldThingPeer instanceof IHasInnerViewPeer) {
				return ((IHasInnerViewPeer) worldThingPeer).getInnerView();
			}
		}
		return null;
	}

	public static final IBNAView getInternalView(IBNAView outerView, Object worldThingID) {
		return getInternalView(outerView, outerView.getBNAWorld().getBNAModel().getThing(worldThingID));
	}

	public static final void expandRectangle(Rectangle r, Point toIncludePoint) {
		if (toIncludePoint.x < r.x) {
			r.width += r.x - toIncludePoint.x;
			r.x = toIncludePoint.x;
		}
		else if (toIncludePoint.x > r.x + r.width) {
			r.width = toIncludePoint.x - r.x;
		}
		if (toIncludePoint.y < r.y) {
			r.height += r.y - toIncludePoint.y;
			r.y = toIncludePoint.y;
		}
		else if (toIncludePoint.y > r.y + r.height) {
			r.height = toIncludePoint.y - r.y;
		}
	}

	public static final List<IThing> toThings(String[] thingIDs, IBNAModel model) {
		List<IThing> things = new ArrayList<IThing>(thingIDs.length);
		for (String thingID : thingIDs) {
			IThing thing = model.getThing(thingID);
			if (thing != null) {
				things.add(thing);
			}
		}
		return things;
	}

	@SuppressWarnings("unchecked")
	public static final <T extends IThing> List<T> toThings(String[] thingIDs, IBNAModel model, Class<T> thingClass) {
		List<T> things = new ArrayList<T>(thingIDs.length);
		for (String thingID : thingIDs) {
			IThing thing = model.getThing(thingID);
			if (thingClass.isInstance(thing)) {
				things.add((T) thing);
			}
		}
		return things;
	}

	public static int[] toXYArray(List<Point> points) {
		int[] xyArray = new int[2 * points.size()];
		for (int i = 0, length = points.size(), xy = 0; i < length; i++) {
			Point p = points.get(i);
			xyArray[xy++] = p.x;
			xyArray[xy++] = p.y;
		}
		return xyArray;
	}

	public static int[] toXYArray(ICoordinateMapper cm, List<Point> points, Point anchorPoint) {
		int[] xyArray = new int[2 * points.size()];
		for (int i = 0, length = points.size(), xy = 0; i < length; i++) {
			Point p = points.get(i);
			p.x += anchorPoint.x;
			p.y += anchorPoint.y;
			p = cm.worldToLocal(p);
			xyArray[xy++] = p.x;
			xyArray[xy++] = p.y;
		}
		return xyArray;
	}

	public static final Rectangle getLocalBoundingBox(ICoordinateMapper cm, IHasBoundingBox t) {
		Rectangle localBoundingBox = cm.worldToLocal(t.getBoundingBox());
		Insets insets = t.get(IHasLocalInsets.LOCAL_INSETS_KEY);
		if (insets != null) {
			localBoundingBox.x += insets.left;
			localBoundingBox.y += insets.top;
			localBoundingBox.width -= insets.left + insets.right;
			localBoundingBox.height -= insets.top + insets.bottom;
		}
		return localBoundingBox;
	}

	private static final Function<IThing, Object> thingToIDFunction = new Function<IThing, Object>() {

		public Object apply(IThing input) {
			return input.getID();
		}
	};

	public static final Iterable<Object> getThingIDs(Iterable<? extends IThing> things) {
		return Iterables.transform(things, thingToIDFunction);
	}

	public static final Iterable<IThing> getThings(final IBNAModel model, Iterable<Object> thingIDs) {
		return Iterables.filter(Iterables.transform(thingIDs, new Function<Object, IThing>() {

			public IThing apply(Object input) {
				return model.getThing(input);
			}
		}), Predicates.notNull());
	}

	public static final void union(Rectangle result, Rectangle other) {
		if (result.isEmpty()) {
			result.x = other.x;
			result.y = other.y;
			result.width = other.width;
			result.height = other.height;
		}
		else if (!other.isEmpty()) {
			result.union(other);
		}
	}

	public static RGB adjustBrightness(RGB rgb, float factor) {
		float r = rgb.red * factor;
		float g = rgb.green * factor;
		float b = rgb.blue * factor;
		float w = ((r > 255 ? r - 255 : 0) + (g > 255 ? g - 255 : 0) + (b > 255 ? b - 255 : 0))
				/ ((r > 255 ? 1 : 0) + (g > 255 ? 1 : 0) + (b > 255 ? 1 : 0));

		return new RGB(//
				SystemUtils.bound(0, BNAUtils.round(r + w), 255),// 
				SystemUtils.bound(0, BNAUtils.round(g + w), 255),// 
				SystemUtils.bound(0, BNAUtils.round(b + w), 255));
	}

	public static final Rectangle toRectangle(java.awt.Rectangle bounds) {
		return new Rectangle(bounds.x, bounds.y, bounds.width, bounds.height);
	}

	public static final Rectangle toRectangle(java.awt.geom.Rectangle2D bounds) {
		int x1 = round(bounds.getMinX());
		int y1 = round(bounds.getMinY());
		int x2 = round(bounds.getMaxX());
		int y2 = round(bounds.getMaxY());
		return new Rectangle(x1, y1, x2 - x1 + 1, y2 - y1 + 1);
	}

	public static final Rectangle toRectangle(org.eclipse.swt.graphics.Rectangle bounds) {
		return new Rectangle(bounds.x, bounds.y, bounds.width, bounds.height);
	}

	public static Point toPoint(org.eclipse.swt.graphics.Point point) {
		return new Point(point.x, point.y);
	}

	public static Point toPoint(Point2D p) {
		return new Point(round(p.getX()), round(p.getY()));
	}

	public static float[] getEllipsePoints(Rectangle r) {
		float error = 0.25f;
		float radius = Math.max(r.width, r.height) / 2;
		float radianDelta = (float) Math.acos(2 * (1 - error / radius) * (1 - error / radius) - 1);
		float cx = r.x + (float) r.width / 2;
		float cy = r.y + (float) r.height / 2;
		List<Float> points = Lists.newArrayList();
		for (float radians = 0; radians < 2 * Math.PI; radians += radianDelta) {
			float x = cx + (float) Math.cos(radians) * r.width / 2f;
			float y = cy + (float) Math.sin(radians) * r.height / 2f;
			points.add(x);
			points.add(y);
		}
		return Floats.toArray(points);
	}
}