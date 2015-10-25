package org.archstudio.bna.utils;

import java.awt.geom.Point2D;
import java.util.List;

import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasEndpoints;
import org.archstudio.bna.facets.IHasMutableAnchorPoint;
import org.archstudio.bna.facets.IHasMutableBoundingBox;
import org.archstudio.bna.facets.IHasMutableEndpoints;
import org.archstudio.bna.facets.IHasMutableMidpoints;
import org.archstudio.bna.facets.IHasMutableReferencePoint;
import org.archstudio.bna.facets.IHasStickyShape;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.logics.coordinating.StickPointLogic;
import org.archstudio.bna.logics.coordinating.StickPointLogic.IStuckThing;
import org.archstudio.swtutils.constants.HorizontalAlignment;
import org.archstudio.swtutils.constants.VerticalAlignment;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

import com.google.common.base.Predicate;

public class BNAAlignUtils {
	private BNAAlignUtils() {
	}

	public static void align(IThing[] things, VerticalAlignment a) {
		switch (a) {
		case TOP: {
			double y = Double.POSITIVE_INFINITY;
			for (IThing t : things) {
				if (t instanceof IHasBoundingBox) {
					Rectangle boundingBox = ((IHasBoundingBox) t).getBoundingBox();
					y = Math.min(boundingBox.y, y);
				}
				else if (t instanceof IHasAnchorPoint) {
					Point2D anchorPoint = ((IHasAnchorPoint) t).getAnchorPoint();
					y = Math.min(anchorPoint.getY(), y);
				}
			}
			for (IThing t : things) {
				if (t instanceof IHasMutableBoundingBox) {
					Rectangle boundingBox = ((IHasMutableBoundingBox) t).getBoundingBox();
					boundingBox.y = SystemUtils.round(y);
					((IHasMutableBoundingBox) t).setBoundingBox(boundingBox);
				}
				else if (t instanceof IHasMutableAnchorPoint) {
					Point2D anchorPoint = ((IHasMutableAnchorPoint) t).getAnchorPoint();
					anchorPoint.setLocation(anchorPoint.getX(), y);
					((IHasMutableAnchorPoint) t).setAnchorPoint(anchorPoint);
				}
			}
		}
			break;
		case MIDDLE: {
			double y = 0;
			int i = 0;
			for (IThing t : things) {
				if (t instanceof IHasBoundingBox) {
					Rectangle boundingBox = ((IHasBoundingBox) t).getBoundingBox();
					y += boundingBox.y + boundingBox.height / 2;
					i++;
				}
				else if (t instanceof IHasAnchorPoint) {
					Point2D anchorPoint = ((IHasAnchorPoint) t).getAnchorPoint();
					y += anchorPoint.getY();
					i++;
				}
			}
			y = y / i;
			for (IThing t : things) {
				if (t instanceof IHasMutableBoundingBox) {
					Rectangle boundingBox = ((IHasMutableBoundingBox) t).getBoundingBox();
					boundingBox.y = SystemUtils.round(y) - boundingBox.height / 2;
					((IHasMutableBoundingBox) t).setBoundingBox(boundingBox);
				}
				else if (t instanceof IHasMutableAnchorPoint) {
					Point2D anchorPoint = ((IHasMutableAnchorPoint) t).getAnchorPoint();
					anchorPoint.setLocation(anchorPoint.getX(), y);
					((IHasMutableAnchorPoint) t).setAnchorPoint(anchorPoint);
				}
			}
		}
			break;
		case BOTTOM: {
			double y = Double.NEGATIVE_INFINITY;
			for (IThing t : things) {
				if (t instanceof IHasBoundingBox) {
					Rectangle boundingBox = ((IHasBoundingBox) t).getBoundingBox();
					y = Math.max(boundingBox.y + boundingBox.height, y);
				}
				else if (t instanceof IHasAnchorPoint) {
					Point2D anchorPoint = ((IHasAnchorPoint) t).getAnchorPoint();
					y = Math.max(anchorPoint.getY(), y);
				}
			}
			for (IThing t : things) {
				if (t instanceof IHasMutableBoundingBox) {
					Rectangle boundingBox = ((IHasMutableBoundingBox) t).getBoundingBox();
					boundingBox.y = SystemUtils.round(y) - boundingBox.height;
					((IHasMutableBoundingBox) t).setBoundingBox(boundingBox);
				}
				else if (t instanceof IHasMutableAnchorPoint) {
					Point2D anchorPoint = ((IHasMutableAnchorPoint) t).getAnchorPoint();
					anchorPoint.setLocation(anchorPoint.getX(), y);
					((IHasMutableAnchorPoint) t).setAnchorPoint(anchorPoint);
				}
			}
		}
			break;
		}
	}

	public static void align(IThing[] things, HorizontalAlignment a) {
		switch (a) {
		case LEFT: {
			double x = Double.POSITIVE_INFINITY;
			for (IThing t : things) {
				if (t instanceof IHasBoundingBox) {
					Rectangle boundingBox = ((IHasBoundingBox) t).getBoundingBox();
					x = Math.min(boundingBox.x, x);
				}
				else if (t instanceof IHasAnchorPoint) {
					Point2D anchorPoint = ((IHasAnchorPoint) t).getAnchorPoint();
					x = Math.min(anchorPoint.getX(), x);
				}
			}
			for (IThing t : things) {
				if (t instanceof IHasMutableBoundingBox) {
					Rectangle boundingBox = ((IHasMutableBoundingBox) t).getBoundingBox();
					boundingBox.x = SystemUtils.round(x);
					((IHasMutableBoundingBox) t).setBoundingBox(boundingBox);
				}
				else if (t instanceof IHasMutableAnchorPoint) {
					Point2D anchorPoint = ((IHasMutableAnchorPoint) t).getAnchorPoint();
					anchorPoint.setLocation(x, anchorPoint.getY());
					((IHasMutableAnchorPoint) t).setAnchorPoint(anchorPoint);
				}
			}
		}
			break;
		case CENTER: {
			double x = 0;
			int i = 0;
			for (IThing t : things) {
				if (t instanceof IHasBoundingBox) {
					Rectangle boundingBox = ((IHasBoundingBox) t).getBoundingBox();
					x += boundingBox.x + boundingBox.width / 2;
					i++;
				}
				else if (t instanceof IHasAnchorPoint) {
					Point2D anchorPoint = ((IHasAnchorPoint) t).getAnchorPoint();
					x += anchorPoint.getX();
					i++;
				}
			}
			x = x / i;
			for (IThing t : things) {
				if (t instanceof IHasMutableBoundingBox) {
					Rectangle boundingBox = ((IHasMutableBoundingBox) t).getBoundingBox();
					boundingBox.x = SystemUtils.round(x) - boundingBox.width / 2;
					((IHasMutableBoundingBox) t).setBoundingBox(boundingBox);
				}
				else if (t instanceof IHasMutableAnchorPoint) {
					Point2D anchorPoint = ((IHasMutableAnchorPoint) t).getAnchorPoint();
					anchorPoint.setLocation(x, anchorPoint.getY());
					((IHasMutableAnchorPoint) t).setAnchorPoint(anchorPoint);
				}
			}
		}
			break;
		case RIGHT: {
			double x = Double.NEGATIVE_INFINITY;
			for (IThing t : things) {
				if (t instanceof IHasBoundingBox) {
					Rectangle boundingBox = ((IHasBoundingBox) t).getBoundingBox();
					x = Math.max(boundingBox.x + boundingBox.width, x);
				}
				else if (t instanceof IHasAnchorPoint) {
					Point2D anchorPoint = ((IHasAnchorPoint) t).getAnchorPoint();
					x = Math.max(anchorPoint.getX(), x);
				}
			}
			for (IThing t : things) {
				if (t instanceof IHasMutableBoundingBox) {
					Rectangle boundingBox = ((IHasMutableBoundingBox) t).getBoundingBox();
					boundingBox.x = SystemUtils.round(x) - boundingBox.width;
					((IHasMutableBoundingBox) t).setBoundingBox(boundingBox);
				}
				else if (t instanceof IHasMutableAnchorPoint) {
					Point2D anchorPoint = ((IHasMutableAnchorPoint) t).getAnchorPoint();
					anchorPoint.setLocation(x, anchorPoint.getY());
					((IHasMutableAnchorPoint) t).setAnchorPoint(anchorPoint);
				}
			}
		}
			break;
		}
	}

	private static Rectangle union(Rectangle r, Point2D p2d) {
		Point p = BNAUtils.toPoint(p2d);
		if (r == null) {
			r = new Rectangle(p.x, p.y, 1, 1);
		}
		else {
			r = r.union(new Rectangle(p.x, p.y, 1, 1));
		}
		return r;
	}

	private static Rectangle union(Rectangle r, IThing t, StickPointLogic stickLogic, IThingKey<Point2D> pKey) {
		if (stickLogic.getStickyThing(t, pKey) == null) {
			return union(r, t.get(pKey));
		}
		return r;
	}

	private static Rectangle getBounds(StickPointLogic stickLogic, Iterable<IThing> thingsToFlip) {
		Rectangle r = null;
		for (IThing t : thingsToFlip) {
			if (t instanceof IHasMutableBoundingBox
					&& UserEditableUtils.isEditableForAllQualities(t, IHasMutableReferencePoint.USER_MAY_MOVE)) {
				if (r == null) {
					r = ((IHasMutableBoundingBox) t).getBoundingBox();
				}
				else {
					r = r.union(((IHasMutableBoundingBox) t).getBoundingBox());
				}
			}
			if (t instanceof IHasMutableAnchorPoint
					&& UserEditableUtils.isEditableForAllQualities(t, IHasMutableReferencePoint.USER_MAY_MOVE)) {
				r = union(r, t, stickLogic, IHasAnchorPoint.ANCHOR_POINT_KEY);
			}
			if (t instanceof IHasMutableEndpoints) {
				if (UserEditableUtils.isEditableForAllQualities(t, IHasMutableEndpoints.USER_MAY_MOVE_ENDPOINT_1)) {
					r = union(r, t, stickLogic, IHasEndpoints.ENDPOINT_1_KEY);
				}
				if (UserEditableUtils.isEditableForAllQualities(t, IHasMutableEndpoints.USER_MAY_MOVE_ENDPOINT_2)) {
					r = union(r, t, stickLogic, IHasEndpoints.ENDPOINT_2_KEY);
				}
			}
			if (t instanceof IHasMutableMidpoints) {
				if (UserEditableUtils.isEditableForAllQualities(t, IHasMutableMidpoints.USER_MAY_MOVE_MIDPOINTS)) {
					for (Point2D p2d : ((IHasMutableMidpoints) t).getMidpoints()) {
						r = union(r, p2d);
					}
				}
			}
		}
		if (r == null) {
			throw new IllegalArgumentException("No things to create bounds with.");
		}
		return r;
	}

	private static Point2D flip(Rectangle bounds, Point2D p, boolean horizontal) {
		if (horizontal) {
			return new Point2D.Double(bounds.x + bounds.width - (p.getX() - bounds.x), p.getY());
		}
		else {
			return new Point2D.Double(p.getX(), bounds.y + bounds.height - (p.getY() - bounds.y));
		}
	}

	private static void flip(IThing t, Rectangle bounds, StickPointLogic stickLogic, IThingKey<Point2D> pKey,
			boolean horizontal) {
		if (stickLogic == null || stickLogic.getStickyThing(t, pKey) == null) {
			t.set(pKey, flip(bounds, t.get(pKey), horizontal));
		}
	}

	public static void flip(StickPointLogic stickLogic, Iterable<IThing> thingsToFlip, boolean horizontal) {
		Rectangle bounds = getBounds(stickLogic, thingsToFlip);
		for (IThing t : thingsToFlip) {
			if (t instanceof IHasMutableBoundingBox
					&& UserEditableUtils.isEditableForAllQualities(t, IHasMutableReferencePoint.USER_MAY_MOVE)) {
				Rectangle r = ((IHasMutableBoundingBox) t).getBoundingBox();
				if (horizontal) {
					r.x = bounds.x + bounds.width - (r.x - bounds.x + r.width);
				}
				else {
					r.y = bounds.y + bounds.height - (r.y - bounds.y + r.height);
				}
				((IHasMutableBoundingBox) t).setBoundingBox(r);
			}
			if (t instanceof IHasMutableAnchorPoint
					&& UserEditableUtils.isEditableForAllQualities(t, IHasMutableReferencePoint.USER_MAY_MOVE)) {
				flip(t, bounds, stickLogic, IHasAnchorPoint.ANCHOR_POINT_KEY, horizontal);
			}
			if (t instanceof IHasMutableEndpoints) {
				if (UserEditableUtils.isEditableForAllQualities(t, IHasMutableEndpoints.USER_MAY_MOVE_ENDPOINT_1)) {
					flip(t, bounds, stickLogic, IHasEndpoints.ENDPOINT_1_KEY, horizontal);
				}
				if (UserEditableUtils.isEditableForAllQualities(t, IHasMutableEndpoints.USER_MAY_MOVE_ENDPOINT_2)) {
					flip(t, bounds, stickLogic, IHasEndpoints.ENDPOINT_2_KEY, horizontal);
				}
			}
			if (t instanceof IHasMutableMidpoints) {
				if (UserEditableUtils.isEditableForAllQualities(t, IHasMutableMidpoints.USER_MAY_MOVE_MIDPOINTS)) {
					List<Point2D> midpoints = ((IHasMutableMidpoints) t).getMidpoints();
					for (int i = 0; i < midpoints.size(); ++i) {
						midpoints.set(i, flip(bounds, midpoints.get(i), horizontal));
					}
					((IHasMutableMidpoints) t).setMidpoints(midpoints);
				}
			}
		}
		// Now flip sticky things.
		for (IThing t : thingsToFlip) {
			if (t instanceof IHasBoundingBox && t instanceof IHasStickyShape) {
				bounds = ((IHasBoundingBox) t).getBoundingBox();
				for (IStuckThing stuckThing : stickLogic.getStuckThings((IHasStickyShape) t)) {
					flip(stuckThing.getStuckThing(), bounds, null, stuckThing.getStuckPointKey(), horizontal);
				}
			}
		}
	}

	public static final Predicate<IThing> FLIP_THING_PREDICATE = new Predicate<IThing>() {
		@Override
		public boolean apply(IThing t) {
			if (t instanceof IHasMutableBoundingBox) {
				return UserEditableUtils.isEditableForAllQualities(t, IHasMutableReferencePoint.USER_MAY_MOVE);
			}
			if (t instanceof IHasMutableAnchorPoint) {
				return UserEditableUtils.isEditableForAllQualities(t, IHasMutableReferencePoint.USER_MAY_MOVE);
			}
			if (t instanceof IHasMutableEndpoints) {
				return UserEditableUtils.isEditableForAllQualities(t, IHasMutableEndpoints.USER_MAY_MOVE_ENDPOINT_1)
						|| UserEditableUtils.isEditableForAllQualities(t,
								IHasMutableEndpoints.USER_MAY_MOVE_ENDPOINT_2);
			}
			if (t instanceof IHasMutableMidpoints) {
				return UserEditableUtils.isEditableForAllQualities(t, IHasMutableMidpoints.USER_MAY_MOVE_MIDPOINTS);
			}
			return false;
		}
	};
}