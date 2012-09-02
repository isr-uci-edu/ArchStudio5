package org.archstudio.archipelago.statechart.core.things;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.archstudio.bna.IThingListener;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasEndpoints;
import org.archstudio.bna.facets.IHasMutableEndpoints;
import org.archstudio.bna.facets.IHasMutableValue;
import org.archstudio.bna.facets.IHasPoints;
import org.archstudio.bna.things.AbstractPointsThing;
import org.eclipse.swt.graphics.Point;

import com.google.common.collect.Lists;

public class AbstractCurvedSplineThing extends AbstractPointsThing implements IHasMutableEndpoints,
		IHasMutableValue<Integer>, IHasAnchorPoint {

	public AbstractCurvedSplineThing(Object id) {
		super(id);
		addThingListener(new IThingListener() {
			@Override
			public void thingChanged(ThingEvent thingEvent) {
				if (!IHasPoints.POINTS_KEY.equals(thingEvent.getPropertyName())
						&& !IHasBoundingBox.BOUNDING_BOX_KEY.equals(thingEvent.getPropertyName())
						&& !IHasAnchorPoint.ANCHOR_POINT_KEY.equals(thingEvent.getPropertyName())) {
					set(IHasPoints.POINTS_KEY, getPoints());
					set(IHasAnchorPoint.ANCHOR_POINT_KEY,
							AbstractCurvedSplineThingPeer.getCurvedPoint(AbstractCurvedSplineThing.this));
				}
			}
		});
	}

	@Override
	protected void initProperties() {
		setEndpoint1(new Point(0, 0));
		setEndpoint2(new Point(0, 0));
		setValue(10);
		super.initProperties();
	}

	@Override
	public Point getEndpoint1() {
		return get(IHasEndpoints.ENDPOINT_1_KEY);
	}

	@Override
	public void setEndpoint1(Point endpoint1) {
		checkNotNull(endpoint1);

		set(IHasEndpoints.ENDPOINT_1_KEY, endpoint1);
	}

	@Override
	public Point getEndpoint2() {
		return get(IHasEndpoints.ENDPOINT_2_KEY);
	}

	@Override
	public void setEndpoint2(Point endpoint2) {
		checkNotNull(endpoint2);

		set(IHasEndpoints.ENDPOINT_2_KEY, endpoint2);
	}

	@Override
	public List<Point> getPoints() {
		List<Point> points = Lists.newArrayList();
		points.add(checkNotNull(getEndpoint1()));
		points.add(AbstractCurvedSplineThingPeer.getCurvedPoint(this));
		points.add(checkNotNull(getEndpoint2()));
		return points;
	}

	@Override
	public void setPoints(final List<Point> points) {
		checkArgument(points.size() <= 3, "%s requires no more than three points (the center point is ignored)", this.getClass());
		setEndpoint1(points.get(0));
		setEndpoint2(points.get(points.size() - 1));
	}

	@Override
	public Integer getValue() {
		return (Integer) get(VALUE_KEY);
	}

	@Override
	public void setValue(Integer value) {
		set(VALUE_KEY, value);
	}

	@Override
	public Point getStickyPointNear(StickyMode stickyMode, Point nearPoint) {
		return getAnchorPoint();
	}

	@Override
	public Point getAnchorPoint() {
		return get(ANCHOR_POINT_KEY);
	}

}
