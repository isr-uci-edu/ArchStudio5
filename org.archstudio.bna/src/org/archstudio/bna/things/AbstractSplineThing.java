package org.archstudio.bna.things;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IThingListener;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasMutableEndpoints;
import org.archstudio.bna.facets.IHasMutableMidpoints;
import org.archstudio.bna.logics.coordinating.StickPointLogic;
import org.archstudio.bna.logics.coordinating.StickPointLogic.IHasSecondaryPoint;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.Point;

import com.google.common.collect.Lists;

@NonNullByDefault
public class AbstractSplineThing extends AbstractPointsThing implements IHasMutableEndpoints, IHasMutableMidpoints,
		IHasSecondaryPoint {

	public AbstractSplineThing(@Nullable Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		setEndpoint1(new Point(0, 0));
		setEndpoint2(new Point(0, 0));
		setMidpoints(Lists.<Point> newArrayList());
		addShapeModifyingKey(ENDPOINT_1_KEY);
		addShapeModifyingKey(ENDPOINT_2_KEY);
		addShapeModifyingKey(MIDPOINTS_KEY);
		super.initProperties();
		set(POINTS_KEY, Lists.newArrayList(new Point(0, 0), new Point(0, 0)));
		addThingListener(new IThingListener() {

			@Override
			public void thingChanged(ThingEvent thingEvent) {
				if (!POINTS_KEY.equals(thingEvent.getPropertyName())
						&& !BOUNDING_BOX_KEY.equals(thingEvent.getPropertyName())) {
					set(POINTS_KEY, getPoints());
				}
			}
		});
		set(POINTS_KEY, getPoints());
	}

	@Override
	public Point getEndpoint1() {
		return get(ENDPOINT_1_KEY);
	}

	@Override
	public void setEndpoint1(Point endpoint1) {
		checkNotNull(endpoint1);

		set(ENDPOINT_1_KEY, endpoint1);
	}

	@Override
	public Point getEndpoint2() {
		return get(ENDPOINT_2_KEY);
	}

	@Override
	public void setEndpoint2(Point endpoint2) {
		checkNotNull(endpoint2);

		set(ENDPOINT_2_KEY, endpoint2);
	}

	@Override
	public List<Point> getMidpoints() {
		return get(MIDPOINTS_KEY);
	}

	@Override
	public void setMidpoints(List<Point> midpoints) {
		set(MIDPOINTS_KEY, midpoints);
	}

	@Override
	public List<Point> getPoints() {
		List<Point> points = Lists.newArrayList();
		points.add(checkNotNull(getEndpoint1()));
		points.addAll(getMidpoints());
		points.add(checkNotNull(getEndpoint2()));
		return points;
	}

	@Override
	public void setPoints(final List<Point> points) {
		checkArgument(points.size() >= 2, "%s requires at least two points", this.getClass());

		setEndpoint1(points.get(0));
		setMidpoints(points.subList(1, points.size() - 1));
		setEndpoint2(points.get(points.size() - 1));
	}

	@Override
	public Point getSecondaryPoint(IBNAModel model, StickPointLogic stickLogic, IThingKey<Point> pointKey) {
		if (pointKey.equals(ENDPOINT_1_KEY)) {
			List<Point> points = getPoints();
			if (points.size() > 2) {
				return points.get(1);
			}
			return stickLogic.getStuckPoint(this, ENDPOINT_2_KEY, points.get(1));
		}
		if (pointKey.equals(ENDPOINT_2_KEY)) {
			List<Point> points = getPoints();
			if (points.size() > 2) {
				return points.get(points.size() - 2);
			}
			return stickLogic.getStuckPoint(this, ENDPOINT_1_KEY, points.get(points.size() - 2));
		}
		throw new IllegalArgumentException(pointKey.toString());
	}
}
