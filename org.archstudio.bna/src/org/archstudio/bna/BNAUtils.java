package org.archstudio.bna;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.graphics.PathData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Widget;

import org.archstudio.bna.constants.GridDisplayType;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasAssemblyData;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasSelected;
import org.archstudio.bna.things.utility.EnvironmentPropertiesThing;
import org.archstudio.bna.things.utility.GridThing;
import org.archstudio.swtutils.constants.Orientation;
import org.archstudio.sysutils.UIDGenerator;

public class BNAUtils {
	public static final Rectangle NONEXISTENT_RECTANGLE = new Rectangle(Integer.MIN_VALUE, Integer.MIN_VALUE, 0, 0);

	public static final int round(double d) {
		return (int) Math.round(d);
	}

	public static final int round(float f) {
		return (int) Math.round(f);
	}

	public static Rectangle normalizeRectangle(Rectangle r) {
		if ((r.width >= 0) && (r.height >= 0))
			return r;

		Rectangle normalizedRect = new Rectangle(0, 0, 0, 0);
		normalizedRect.x = r.x;
		normalizedRect.y = r.y;

		if (r.width >= 0) {
			normalizedRect.width = r.width;
		}
		else {
			normalizedRect.width = -r.width;
			normalizedRect.x = r.x + r.width;
		}

		if (r.height >= 0) {
			normalizedRect.height = r.height;
		}
		else {
			normalizedRect.height = -r.height;
			normalizedRect.y = r.y + r.height;
		}
		return normalizedRect;
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

		return (x >= x1) && (x <= x2) && (y >= y1) && (y <= y2);
	}

	public static boolean isWithin(Rectangle outsideRect, Rectangle insideRect) {
		Rectangle in = normalizeRectangle(insideRect);

		return isWithin(outsideRect, in.x, in.y) && isWithin(outsideRect, in.x + in.width, in.y) && isWithin(outsideRect, in.x, in.y + in.height)
		        && isWithin(outsideRect, in.x + in.width, in.y + in.height);
	}

	public static boolean nulleq(Object o1, Object o2) {
		return o1 == null ? o2 == null : o1.equals(o2);
	}

	public static void drawMarqueeRectangle(GC g, Rectangle r, int o) {
		int[] oldDash = g.getLineDash();

		o = o % 6;
		g.setLineWidth(1);
		g.setLineDash(new int[] { 1, 5 });

		g.setForeground(g.getDevice().getSystemColor(SWT.COLOR_WHITE));
		if (r.width > o) {
			g.drawLine(r.x + o, r.y, r.x + r.width, r.y);
			g.drawLine(r.x + r.width - o, r.y + r.height, r.x, r.y + r.height);
		}
		if (r.height > o) {
			g.drawLine(r.x + r.width, r.y + o, r.x + r.width, r.y + r.height);
			g.drawLine(r.x, r.y + r.height - o, r.x, r.y);
		}

		o = (o + 1) % 6;
		g.setForeground(g.getDevice().getSystemColor(SWT.COLOR_GRAY));
		if (r.width > o) {
			g.drawLine(r.x + o, r.y, r.x + r.width, r.y);
			g.drawLine(r.x + r.width - o, r.y + r.height, r.x, r.y + r.height);
		}
		if (r.height > o) {
			g.drawLine(r.x + r.width, r.y + o, r.x + r.width, r.y + r.height);
			g.drawLine(r.x, r.y + r.height - o, r.x, r.y);
		}

		o = (o + 1) % 6;
		g.setForeground(g.getDevice().getSystemColor(SWT.COLOR_DARK_GRAY));
		if (r.width > o) {
			g.drawLine(r.x + o, r.y, r.x + r.width, r.y);
			g.drawLine(r.x + r.width - o, r.y + r.height, r.x, r.y + r.height);
		}
		if (r.height > o) {
			g.drawLine(r.x + r.width, r.y + o, r.x + r.width, r.y + r.height);
			g.drawLine(r.x, r.y + r.height - o, r.x, r.y);
		}

		o = (o + 1) % 6;
		g.setForeground(g.getDevice().getSystemColor(SWT.COLOR_BLACK));
		if (r.width > o) {
			g.drawLine(r.x + o, r.y, r.x + r.width, r.y);
			g.drawLine(r.x + r.width - o, r.y + r.height, r.x, r.y + r.height);
		}
		if (r.height > o) {
			g.drawLine(r.x + r.width, r.y + o, r.x + r.width, r.y + r.height);
			g.drawLine(r.x, r.y + r.height - o, r.x, r.y);
		}

		g.setLineDash(oldDash);
	}

	public static void drawMarquee(GC g, int[] p, int o) {
		int[] oldDash = g.getLineDash();

		o = o % 6;
		g.setLineWidth(1);

		// true marquee outline paths are not possible at this point
		// because swt does not allow a dashed line to start out with 0
		// in other words, it's not possible to start a dashed line
		// with empty space

		for (int i = 5; i >= 0; i--) {
			Color c = null;
			switch ((6 + i - o) % 6) {
			case 0:
				c = g.getDevice().getSystemColor(SWT.COLOR_WHITE);
				break;
			case 1:
				c = g.getDevice().getSystemColor(SWT.COLOR_GRAY);
				break;
			case 2:
				c = g.getDevice().getSystemColor(SWT.COLOR_DARK_GRAY);
				break;
			case 3:
				c = g.getDevice().getSystemColor(SWT.COLOR_BLACK);
				break;
			case 4:
				c = g.getDevice().getSystemColor(SWT.COLOR_BLACK);
				break;
			case 5:
				c = g.getDevice().getSystemColor(SWT.COLOR_BLACK);
				break;
			}

			if (i == 5) {
				g.setLineDash(null);
			}
			else {
				g.setLineDash(new int[] { g.getLineWidth() * (i + 1), g.getLineWidth() * (5 - i) });
			}

			g.setForeground(c);
			g.drawPolyline(p);
		}

		g.setLineDash(null);
	}

	public static void drawMarquee(GC g, Path p, int o) {
		int[] oldDash = g.getLineDash();

		o = o % 6;
		g.setLineWidth(1);

		// true marquee outline paths are not possible at this point
		// because swt does not allow a dashed line to start out with 0
		// in other words, it's not possible to start a dashed line
		// with empty space

		for (int i = 5; i >= 0; i--) {
			Color c = null;
			switch ((6 + i - o) % 6) {
			case 0:
				c = g.getDevice().getSystemColor(SWT.COLOR_WHITE);
				break;
			case 1:
				c = g.getDevice().getSystemColor(SWT.COLOR_GRAY);
				break;
			case 2:
				c = g.getDevice().getSystemColor(SWT.COLOR_DARK_GRAY);
				break;
			case 3:
				c = g.getDevice().getSystemColor(SWT.COLOR_BLACK);
				break;
			case 4:
				c = g.getDevice().getSystemColor(SWT.COLOR_BLACK);
				break;
			case 5:
				c = g.getDevice().getSystemColor(SWT.COLOR_BLACK);
				break;
			}

			if (i == 5) {
				g.setLineDash(null);
			}
			else {
				g.setLineDash(new int[] { g.getLineWidth() * (i + 1), g.getLineWidth() * (5 - i) });
			}

			g.setForeground(c);
			g.drawPath(p);
		}

		g.setLineDash(null);
	}

	public static final Point worldToLocal(ICoordinateMapper cm, Point worldPoint) {
		return new Point(cm.worldXtoLocalX(worldPoint.x), cm.worldYtoLocalY(worldPoint.y));
	}

	public static final Point[] worldToLocal(ICoordinateMapper cm, Point[] worldPoints) {
		Point[] localPoints = new Point[worldPoints.length];
		for (int i = 0; i < worldPoints.length; i++) {
			localPoints[i] = worldToLocal(cm, worldPoints[i]);
		}
		return localPoints;
	}

	public static final Rectangle worldToLocal(ICoordinateMapper cm, Rectangle worldRectangle) {
		Rectangle localRectangle = new Rectangle(cm.worldXtoLocalX(worldRectangle.x), cm.worldYtoLocalY(worldRectangle.y), 0, 0);
		localRectangle.width = cm.worldXtoLocalX(worldRectangle.x + worldRectangle.width) - localRectangle.x;
		localRectangle.height = cm.worldYtoLocalY(worldRectangle.y + worldRectangle.height) - localRectangle.y;
		return localRectangle;
	}

	public static final Point localToWorld(ICoordinateMapper cm, Point localPoint) {
		return new Point(cm.localXtoWorldX(localPoint.x), cm.localYtoWorldY(localPoint.y));
	}

	public static final Point[] localToWorld(ICoordinateMapper cm, Point[] localPoints) {
		Point[] worldPoints = new Point[localPoints.length];
		for (int i = 0; i < localPoints.length; i++) {
			worldPoints[i] = localToWorld(cm, localPoints[i]);
		}
		return worldPoints;
	}

	public static final Rectangle localToWorld(ICoordinateMapper cm, Rectangle localRectangle) {
		Rectangle worldRectangle = new Rectangle(cm.localXtoWorldX(localRectangle.x), cm.localYtoWorldY(localRectangle.y), 0, 0);
		worldRectangle.width = cm.localXtoWorldX(localRectangle.x + localRectangle.width) - worldRectangle.x;
		worldRectangle.height = cm.localYtoWorldY(localRectangle.y + localRectangle.height) - worldRectangle.y;
		return worldRectangle;
	}

	public static final void convertWorldToLocal(ICoordinateMapper cm, Point point) {
		point.x = cm.worldXtoLocalX(point.x);
		point.y = cm.worldYtoLocalY(point.y);
	}

	public static final void convertWorldToLocal(ICoordinateMapper cm, Point[] points) {
		for (Point point : points) {
			point.x = cm.worldXtoLocalX(point.x);
			point.y = cm.worldYtoLocalY(point.y);
		}
	}

	public static final void convertWorldToLocal(ICoordinateMapper cm, Rectangle r) {
		int x2 = r.x + r.width;
		int y2 = r.y + r.height;

		r.x = cm.worldXtoLocalX(r.x);
		r.y = cm.worldYtoLocalY(r.y);
		r.width = cm.worldXtoLocalX(x2) - r.x;
		r.height = cm.worldYtoLocalY(y2) - r.y;
	}

	public static final void convertWorldToLocal(ICoordinateMapper cm, PathData pathData) {
		float[] points = pathData.points;
		for (int pi = 0; pi < points.length; pi += 2) {
			points[pi] = cm.worldXtoLocalX(Math.round(points[pi]));
			points[pi + 1] = cm.worldYtoLocalY(Math.round(points[pi + 1]));
		}
	}

	public static final void convertLocalToWorld(ICoordinateMapper cm, Point point) {
		point.x = cm.localXtoWorldX(point.x);
		point.y = cm.localYtoWorldY(point.y);
	}

	public static final void convertLocalToWorld(ICoordinateMapper cm, Point[] points) {
		for (Point point : points) {
			point.x = cm.localXtoWorldX(point.x);
			point.y = cm.localYtoWorldY(point.y);
		}
	}

	public static final void convertLocalToWorld(ICoordinateMapper cm, Rectangle r) {
		int x2 = r.x + r.width;
		int y2 = r.y + r.height;

		r.x = cm.localXtoWorldX(r.x);
		r.y = cm.localYtoWorldY(r.y);
		r.width = cm.localXtoWorldX(x2) - r.x;
		r.height = cm.localYtoWorldY(y2) - r.y;
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
			if ((dx == 0) && (dy == 0)) {
				return true;
			}
		}
		return false;
	}

	public static final void addPathDataToPath(PathData pathData, Path path) {
		byte[] types = pathData.types;
		float[] points = pathData.points;
		float x1, y1, x2, y2, x3, y3;
		int pi = 0;
		for (int ti = 0; ti < types.length; ti++) {
			switch (types[ti]) {
			case SWT.PATH_CLOSE:
				path.close();
				break;
			case SWT.PATH_CUBIC_TO:
				x1 = points[pi++];
				y1 = points[pi++];
				x2 = points[pi++];
				y2 = points[pi++];
				x3 = points[pi++];
				y3 = points[pi++];
				path.cubicTo(x1, y1, x2, y2, x3, y3);
				break;
			case SWT.PATH_LINE_TO:
				x1 = points[pi++];
				y1 = points[pi++];
				path.lineTo(x1, y1);
				break;
			case SWT.PATH_MOVE_TO:
				x1 = points[pi++];
				y1 = points[pi++];
				path.moveTo(x1, y1);
				break;
			case SWT.PATH_QUAD_TO:
				x1 = points[pi++];
				y1 = points[pi++];
				x2 = points[pi++];
				y2 = points[pi++];
				path.quadTo(x1, y1, x2, y2);
				break;
			default:
				throw new IllegalArgumentException();
			}
		}
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
			setRectangle(r, p.x - (width / 2), p.y - (height / 2), width, height);
			break;
		case NORTHWEST:
			setRectangle(r, p.x - width, p.y - height, width, height);
			break;
		case NORTH:
			setRectangle(r, p.x - (width / 2), p.y - height, width, height);
			break;
		case NORTHEAST:
			setRectangle(r, p.x, p.y - height, width, height);
			break;
		case EAST:
			setRectangle(r, p.x, p.y - (height / 2), width, height);
			break;
		case SOUTHEAST:
			setRectangle(r, p.x, p.y, width, height);
			break;
		case SOUTH:
			setRectangle(r, p.x - (width / 2), p.y, width, height);
			break;
		case SOUTHWEST:
			setRectangle(r, p.x - width, p.y, width, height);
			break;
		case WEST:
			setRectangle(r, p.x - width, p.y - (height / 2), width, height);
			break;
		}
	}

	public static boolean isAssemblyRoot(IThing t, String assemblyKind) {
		if (t instanceof IHasAssemblyData) {
			String k = ((IHasAssemblyData) t).getAssemblyKind();
			return ((k != null) && (k.equals(assemblyKind)));
		}
		return false;
	}

	private static float[] deg2rad = null;

	public static float degreesToRadians(int degrees) {
		while (degrees < 0)
			degrees += 360;
		degrees = degrees % 360;
		if (deg2rad == null) {
			deg2rad = new float[360];
			for (int i = 0; i < 360; i++) {
				deg2rad[i] = i * ((float) Math.PI / 180f);
			}
		}
		return deg2rad[degrees];
	}

	public static Point[] toPointArray(int[] points) {
		Point[] pa = new Point[points.length / 2];
		for (int i = 0; i < points.length; i++) {
			pa[i] = new Point(points[i / 2], points[(i / 2) + 1]);
		}
		return pa;
	}

	public static int[] createIsocolesTriangle(Rectangle boundingBox, Orientation facing) {
		int x1, x2, x3;
		int y1, y2, y3;

		switch (facing) {
		case NORTHWEST:
			x1 = boundingBox.x;
			y1 = boundingBox.y + boundingBox.height;
			x2 = boundingBox.x;
			y2 = boundingBox.y;
			x3 = boundingBox.x + boundingBox.width;
			y3 = boundingBox.y;
			break;
		case NORTH:
			x1 = boundingBox.x + (boundingBox.width / 2);
			y1 = boundingBox.y;
			x2 = boundingBox.x;
			y2 = boundingBox.y + boundingBox.height;
			x3 = boundingBox.x + boundingBox.width;
			y3 = boundingBox.y + boundingBox.height;
			break;
		case NORTHEAST:
			x1 = boundingBox.x;
			y1 = boundingBox.y;
			x2 = boundingBox.x + boundingBox.width;
			y2 = boundingBox.y;
			x3 = boundingBox.x + boundingBox.width;
			y3 = boundingBox.y + boundingBox.height;
			break;
		case EAST:
			x1 = boundingBox.x + boundingBox.width;
			y1 = boundingBox.y + (boundingBox.height / 2);
			x2 = boundingBox.x;
			y2 = boundingBox.y;
			x3 = boundingBox.x;
			y3 = boundingBox.y + boundingBox.height;
			break;
		case SOUTHEAST:
			x1 = boundingBox.x + boundingBox.width;
			y1 = boundingBox.y;
			x2 = boundingBox.x + boundingBox.width;
			y2 = boundingBox.y + boundingBox.height;
			x3 = boundingBox.x;
			y3 = boundingBox.y + boundingBox.height;
			break;
		case SOUTH:
			x1 = boundingBox.x + (boundingBox.width / 2);
			y1 = boundingBox.y + boundingBox.height;
			x2 = boundingBox.x;
			y2 = boundingBox.y;
			x3 = boundingBox.x + boundingBox.width;
			y3 = boundingBox.y;
			break;
		case SOUTHWEST:
			x1 = boundingBox.x;
			y1 = boundingBox.y;
			x2 = boundingBox.x + boundingBox.width;
			y2 = boundingBox.y + boundingBox.height;
			x3 = boundingBox.x;
			y3 = boundingBox.y + boundingBox.height;
			break;
		case WEST:
			x1 = boundingBox.x;
			y1 = boundingBox.y + (boundingBox.height / 2);
			x2 = boundingBox.x + boundingBox.width;
			y2 = boundingBox.y;
			x3 = boundingBox.x + boundingBox.width;
			y3 = boundingBox.y + boundingBox.height;
			break;
		default:
			throw new IllegalArgumentException("Invalid facing");
		}

		return new int[] { x1, y1, x2, y2, x3, y3 };
	}

	public static Rectangle insetRectangle(Rectangle r, Rectangle insets) {
		Rectangle i = new Rectangle(r.x + insets.x, r.y + insets.y, r.width + insets.width, r.height + insets.height);
		if (i.width < 0)
			return null;
		if (i.height < 0)
			return null;
		if (!r.contains(i.x, i.y))
			return null;
		return i;
	}

	public static boolean isEdgePoint(Point p, Rectangle r) {
		int x2 = r.x + r.width;
		int y2 = r.y + r.height;
		if ((p.x == r.x) && (p.y >= r.y) && (p.y <= y2)) {
			//It's on the left rail
			return true;
		}
		if ((p.x == x2) && (p.y >= r.y) && (p.y <= y2)) {
			//It's on the right rail
			return true;
		}
		if ((p.y == r.y) && (p.x >= r.x) && (p.x <= x2)) {
			//it's on the top rail
			return true;
		}
		if ((p.y == y2) && (p.x >= r.x) && (p.x <= x2)) {
			//it's on the bottom rail
			return true;
		}
		return false;
	}

	public static Orientation getOrientationOfEdgePoint(Point p, Rectangle r) {
		int x2 = r.x + r.width;
		int y2 = r.y + r.height;
		if ((p.x == r.x) && (p.y == r.y)) {
			return Orientation.NORTHWEST;
		}
		else if ((p.x == r.x) && (p.y == y2)) {
			return Orientation.SOUTHWEST;
		}
		else if ((p.x == x2) && (p.y == r.y)) {
			return Orientation.NORTHEAST;
		}
		else if ((p.x == x2) && (p.y == y2)) {
			return Orientation.SOUTHEAST;
		}
		else if ((p.x == r.x) && (p.y >= r.y) && (p.y <= y2)) {
			return Orientation.WEST;
		}
		if ((p.x == x2) && (p.y >= r.y) && (p.y <= y2)) {
			return Orientation.EAST;
		}
		if ((p.y == r.y) && (p.x >= r.x) && (p.x <= x2)) {
			return Orientation.NORTH;
		}
		if ((p.y == y2) && (p.x >= r.x) && (p.x <= x2)) {
			return Orientation.SOUTH;
		}

		//it's not on a rail
		return Orientation.NONE;

	}

	public static Point findClosestEdgePoint(Point p, Rectangle r) {
		Point np = new Point(p.x, p.y);

		boolean midx = false;
		boolean midy = false;

		if (p.x < r.x) {
			//It's to the left of the rectangle
			np.x = r.x;
		}
		else if (p.x < (r.x + r.width)) {
			//It's in the middle of the rectangle
			midx = true;
		}
		else {
			//It's beyond the right of the rectangle
			np.x = r.x + r.width;
		}

		if (p.y < r.y) {
			np.y = r.y;
		}
		else if (p.y < (r.y + r.height)) {
			midy = true;
		}
		else {
			np.y = r.y + r.height;
		}

		if (midx && midy) {
			//It was within the rectangle
			int dl = Math.abs(p.x - r.x);
			int dr = Math.abs(p.x - (r.x + r.width));
			int dt = Math.abs(p.y - r.y);
			int db = Math.abs(p.y - (r.y + r.height));

			if ((dt <= db) && (dt <= dl) && (dt <= dr)) {
				//it's closest to the top rail.
				np.y = r.y;
				return np;
			}
			else if ((db <= dt) && (db <= dl) && (db <= dr)) {
				//it's closest to the bottom rail
				np.y = r.y + r.height;
				return np;
			}
			else if ((dl <= dt) && (dl <= db) && (dl <= dr)) {
				//it's closest to the left rail
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
		if ((oldRect == null) || (newRect == null))
			return new Point(p.x, p.y);

		int ox1 = oldRect.x;
		int ox2 = oldRect.x + oldRect.width;
		int oy1 = oldRect.y;
		int oy2 = oldRect.y + oldRect.height;
		int ow = oldRect.width;
		int oh = oldRect.height;

		int nx1 = newRect.x;
		int nx2 = newRect.x + newRect.width;
		int ny1 = newRect.y;
		int ny2 = newRect.y + newRect.height;
		int nw = newRect.width;
		int nh = newRect.height;

		int dw = nw - ow;
		int dh = nh - oh;

		double sx = (double) nw / (double) ow;
		double sy = (double) nh / (double) oh;

		int dx = nx1 - ox1;
		int dy = ny1 - oy1;

		Point p2 = new Point(p.x, p.y);

		if (p.y == oldRect.y) {
			//It's on the top rail

			//Keep it on the top rail
			p2.y = newRect.y;

			//Old distance from the left rail
			int dist = p.x - oldRect.x;

			if (dw != 0) {
				//Scale that distance
				dist = BNAUtils.round((double) dist * sx);
			}

			//Also perform translation
			p2.x = newRect.x + dist;
		}
		else if ((p.y == (oldRect.y + oldRect.height/* - 1 */)) || (p.y == (oldRect.y + oldRect.height))) {
			//It's on the bottom rail

			//Keep it on the bottom rail
			p2.y = newRect.y + newRect.height/* - 1 */;

			//Old distance from the left rail
			int dist = p.x - oldRect.x;

			if (dw != 0) {
				//Scale that distance
				dist = BNAUtils.round((double) dist * sx);
			}

			//Also perform translation
			p2.x = newRect.x + dist;
		}
		else if (p.x == oldRect.x) {
			//It's on the left rail

			//Keep it on the left rail
			p2.x = newRect.x;

			//Old distance from the top rail
			int dist = p.y - oldRect.y;

			if (dh != 0) {
				//Scale that distance
				dist = BNAUtils.round((double) dist * sy);
			}

			//Also perform translation
			p2.y = newRect.y + dist;
		}
		else if ((p.x == (oldRect.x + oldRect.width/* - 1 */)) || (p.x == (oldRect.x + oldRect.width))) {
			//It's on the right rail

			//Keep it on the right rail
			p2.x = newRect.x + newRect.width/* - 1 */;

			//Old distance from the top rail
			int dist = p.y - oldRect.y;

			if (dh != 0) {
				//Scale that distance
				dist = BNAUtils.round((double) dist * sy);
			}

			//Also perform translation
			p2.y = newRect.y + dist;
		}

		//Normalize
		if (p2.x < newRect.x) {
			p2.x = newRect.x;
		}
		if (p2.x >= (newRect.x + newRect.width)) {
			p2.x = newRect.x + newRect.width/* - 1 */;
		}
		if (p2.y < newRect.y) {
			p2.y = newRect.y;
		}
		if (p2.y >= (newRect.y + newRect.height)) {
			p2.y = newRect.y + newRect.height/* - 1 */;
		}

		return p2;
	}

	public static void fillPath(Path path, PathData pathData, Point pathDataOffset, ICoordinateMapper cm) {
		float[] points = pathData.points;
		if (pathDataOffset != null) {
			for (int pi = 0; pi < points.length; pi += 2) {
				points[pi] = points[pi] + pathDataOffset.x;
				points[pi + 1] = points[pi + 1] + pathDataOffset.y;
			}
		}
		if (cm != null)
			BNAUtils.convertWorldToLocal(cm, pathData);
		BNAUtils.addPathDataToPath(pathData, path);
	}

	public static Rectangle getBounds(Path path) {
		float[] bounds = new float[4];
		path.getBounds(bounds);
		int x1 = (int) Math.floor(bounds[0]);
		int y1 = (int) Math.floor(bounds[1]);
		int x2 = (int) Math.ceil(bounds[0] + bounds[2]);
		int y2 = (int) Math.ceil(bounds[1] + bounds[3]);
		return new Rectangle(x1, y1, x2 - x1 + 1, y2 - y1 + 1);
	}

	public static RGB getRGBForSystemColor(Device d, int systemColorID) {
		Color c = d.getSystemColor(systemColorID);
		if (c == null)
			return null;
		return c.getRGB();
	}

	public static boolean isPointOnRectangle(Point p, Rectangle r) {
		return isPointOnRectangle(p.x, p.y, r.x, r.y, r.width, r.height);
	}

	public static boolean isPointOnRectangle(int x, int y, int rx, int ry, int rw, int rh) {
		if ((x == rx) || (x == rx + rw)) {
			if ((y >= ry) && (y <= ry + rh)) {
				return true;
			}
		}
		if ((y == ry) || (y == ry + rh)) {
			if ((x >= rx) && (x <= rx + rw)) {
				return true;
			}
		}
		return false;
	}

	public static Point snapToNormal(Point p, Rectangle r, Orientation side) {
		Point np = new Point(p.x, p.y);
		switch (side) {
		case NORTH:
			np.y = r.y;
			if (p.x < r.x)
				np.x = r.x;
			if (p.x > r.x + r.width)
				np.x = r.x + r.width;
			break;
		case SOUTH:
			np.y = r.y + r.height;
			if (p.x < r.x)
				np.x = r.x;
			if (p.x > r.x + r.width)
				np.x = r.x + r.width;
			break;
		case WEST:
			np.x = r.x;
			if (p.y < r.y)
				np.y = r.y;
			if (p.y > r.y + r.height)
				np.y = r.y + r.height;
			break;
		case EAST:
			np.x = r.x + r.width;
			if (p.y < r.y)
				np.y = r.y;
			if (p.y > r.y + r.height)
				np.y = r.y + r.height;
			break;
		}
		return np;
	}

	public static class PointToRectangleDistanceData {
		public Orientation closestSide;
		public double dist;
	}

	public static PointToRectangleDistanceData getPointToRectangleDistance(Point p, Rectangle r) {
		double closestDist = Double.MAX_VALUE;
		Orientation closestSide = Orientation.NONE;

		double dist;
		//Check north distance
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
		for (IThing t : m.getAllThings()) {
			if (t instanceof EnvironmentPropertiesThing) {
				return (EnvironmentPropertiesThing) t;
			}
		}
		EnvironmentPropertiesThing ept = new EnvironmentPropertiesThing();
		m.addThing(ept);
		return ept;
	}

	public static Rectangle clone(Rectangle r) {
		return new Rectangle(r.x, r.y, r.width, r.height);
	}

	public static void asyncExec(final Widget w, final Runnable r) {
		if (!w.isDisposed())
			asyncExec(w.getDisplay(), new Runnable() {
				public void run() {
					if (!w.isDisposed())
						r.run();
				}
			});
	}

	public static void asyncExec(final Display d, final Runnable r) {
		if (!d.isDisposed()) {
			d.asyncExec(new Runnable() {
				public void run() {
					if (!d.isDisposed())
						r.run();
				}
			});
		}
	}

	public static void syncExec(final Widget w, final Runnable r) {
		if (!w.isDisposed())
			syncExec(w.getDisplay(), new Runnable() {
				public void run() {
					if (!w.isDisposed())
						r.run();
				}
			});
	}

	public static void syncExec(final Display d, final Runnable r) {
		if (!d.isDisposed()) {
			d.syncExec(new Runnable() {
				public void run() {
					if (!d.isDisposed())
						r.run();
				}
			});
		}
	}

	public static Composite getParentComposite(IBNAView view) {
		if (view == null) {
			return null;
		}
		Composite c = view.getParentComposite();
		if (c != null) {
			return c;
		}
		return getParentComposite(view.getParentView());
	}

	public static Point getCentralPoint(IThing t) {
		if (t instanceof IHasAnchorPoint) {
			return ((IHasAnchorPoint) t).getAnchorPoint();
		}
		if (t instanceof IHasBoundingBox) {
			Rectangle r = ((IHasBoundingBox) t).getBoundingBox();
			return new Point(r.x + (r.width / 2), r.y + (r.height / 2));
		}
		return null;
	}

	public static List<IThing> getSelectedThings(IBNAModel m) {
		List<IThing> selectedThings = new ArrayList<IThing>();
		for (IThing t : m.getAllThings()) {
			if (t instanceof IHasSelected) {
				if (((IHasSelected) t).isSelected()) {
					selectedThings.add(t);
				}
			}
		}
		return Collections.unmodifiableList(selectedThings);
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
		ept.setProperty("x", cm.localXtoWorldX(0));
		ept.setProperty("y", cm.localYtoWorldY(0));
		ept.setProperty("scale", cm.getScale());
	}

	public static void restoreCoordinateMapperData(IMutableCoordinateMapper cm, EnvironmentPropertiesThing ept) {
		try {
			int x = ept.getProperty("x");
			int y = ept.getProperty("y");
			double scale = ept.getProperty("scale");

			cm.repositionAbsolute(x, y);
			cm.rescaleAbsolute(scale);
		}
		catch (Exception e) {
		}
	}

	public static boolean infinitelyRecurses(IBNAView view) {
		IBNAWorld world = view.getWorld();
		if (world == null)
			return false;

		IBNAView view2 = view.getParentView();
		while (view2 != null) {
			if (world.equals(view2.getWorld())) {
				return true;
			}
			view2 = view2.getParentView();
		}
		return false;
	}
}
