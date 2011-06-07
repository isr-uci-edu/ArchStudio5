package org.archstudio.bna.things;

import java.util.Set;

import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasMutableAnchorPoint;
import org.archstudio.bna.facets.IIsSticky;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;

import com.google.common.collect.Sets;

public abstract class AbstractPolygonThing extends AbstractSplineThing implements IHasMutableAnchorPoint, IIsSticky {

	public AbstractPolygonThing(Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		setAnchorPoint(new Point(0, 0));
		super.initProperties();
		addEdgeModifyingKey(POINTS_KEY);
		addEdgeModifyingKey(ANCHOR_POINT_KEY);
	}

	@Override
	protected Rectangle calculateBoundingBox() {
		return super.calculateBoundingBox().translate(getAnchorPoint());
	}

	@Override
	public Point getAnchorPoint() {
		return get(ANCHOR_POINT_KEY);
	}

	@Override
	public void setAnchorPoint(Point p) {
		set(ANCHOR_POINT_KEY, p);
	}

	@Override
	public Point getReferencePoint() {
		return getAnchorPoint();
	}

	@Override
	public void moveRelative(final Point moveDelta) {
		if (moveDelta.x != 0 || moveDelta.y != 0) {
			synchronizedUpdate(new Runnable() {
				@Override
				public void run() {
					setAnchorPoint(getAnchorPoint().translate(moveDelta));
				}
			});
		}
	}

	protected void addEdgeModifyingKey(final IThingKey<?> key) {
		synchronizedUpdate(new Runnable() {
			@Override
			public void run() {
				Set<IThingKey<?>> keys = Sets.newHashSet(getStickyModifyingKeys());
				keys.add(key);
				set(STICKY_MODIFYING_KEYS_KEY, keys);
			}
		});
	}

	@Override
	public Iterable<IThingKey<?>> getStickyModifyingKeys() {
		return get(STICKY_MODIFYING_KEYS_KEY);
	}

	@Override
	public PrecisionPoint getStickyPointNear(StickyMode stickyMode, Point nearPoint, Point refPoint) {
		Rectangle bb = getBoundingBox();
		Point center = bb.getCenter();
		switch (stickyMode) {
		case CENTER:
			return new PrecisionPoint(center);
		case EDGE:
			return BNAUtils.getClosestPointOnPolygon(
					BNAUtils.toXYArray(ICoordinateMapper.IDENTITY, getPoints(), getAnchorPoint()), nearPoint.x,
					nearPoint.y);
		case EDGE_FROM_CENTER:
			return BNAUtils.getClosestPointOnPolygon(
					BNAUtils.toXYArray(ICoordinateMapper.IDENTITY, getPoints(), getAnchorPoint()), nearPoint.x,
					nearPoint.y, center.x, center.y);
		}
		throw new IllegalArgumentException(stickyMode.name());
	}
}
