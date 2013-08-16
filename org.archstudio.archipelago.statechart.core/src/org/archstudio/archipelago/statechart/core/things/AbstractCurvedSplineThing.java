package org.archstudio.archipelago.statechart.core.things;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.awt.Shape;
import java.awt.geom.QuadCurve2D;
import java.util.List;

import org.archstudio.bna.IThingListener;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasEndpoints;
import org.archstudio.bna.facets.IHasMutableEndpoints;
import org.archstudio.bna.facets.IHasMutablePoints;
import org.archstudio.bna.facets.IHasMutableValue;
import org.archstudio.bna.facets.IHasPoints;
import org.archstudio.bna.things.AbstractAnchorPointThing;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

import com.google.common.collect.Lists;

public abstract class AbstractCurvedSplineThing extends AbstractAnchorPointThing implements IHasMutableEndpoints,
		IHasMutableValue<Integer>, IHasMutablePoints, IHasBoundingBox {

	public AbstractCurvedSplineThing(Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		addThingListener(new IThingListener() {
			@Override
			public void thingChanged(ThingEvent thingEvent) {
				if (isShapeModifyingKey(thingEvent.getPropertyName())
						&& !IHasAnchorPoint.ANCHOR_POINT_KEY.equals(thingEvent.getPropertyName())
						&& !IHasPoints.POINTS_KEY.equals(thingEvent.getPropertyName())
						&& !IHasBoundingBox.BOUNDING_BOX_KEY.equals(thingEvent.getPropertyName())) {
					Point p1 = getEndpoint1();
					Point p2 = getEndpoint2();
					double dx = p2.x - p1.x;
					double dy = p2.y - p1.y;
					double angle = Math.PI - Math.atan2(dy, dx);
					Point m = new Point((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
					double l = -getValue();
					double cx = m.x + Math.sin(angle) * l;
					double cy = m.y + Math.cos(angle) * l;
					Point ap = new Point(BNAUtils.round(cx), BNAUtils.round(cy));
					setAnchorPoint(ap);
					set(IHasPoints.POINTS_KEY, Lists.newArrayList(p1, ap, p2));
					Rectangle r = new Rectangle(p1.x, p1.y, 0, 0);
					r = r.union(new Rectangle(p2.x, p2.y, 0, 0));
					r = r.union(new Rectangle(ap.x, ap.y, 0, 0));
					set(IHasBoundingBox.BOUNDING_BOX_KEY, r);
				}
			}
		});
		setEndpoint1(new Point(0, 0));
		setEndpoint2(new Point(0, 0));
		super.initProperties();
		addShapeModifyingKey(ENDPOINT_1_KEY);
		addShapeModifyingKey(ENDPOINT_2_KEY);
		addShapeModifyingKey(VALUE_KEY);
		setValue(10);
	}

	@Override
	protected Shape createStickyShape() {
		Point p1 = getEndpoint1();
		Point p2 = getEndpoint2();
		Point ap = getAnchorPoint();
		return new QuadCurve2D.Double(p1.x, p1.y, ap.x, ap.y, p2.x, p2.y);
	}

	@Override
	public void moveRelative(Point worldDelta) {
		Point p1 = getEndpoint1();
		p1.x += worldDelta.x;
		p1.y += worldDelta.y;
		setEndpoint1(p1);

		Point p2 = getEndpoint2();
		p2.x += worldDelta.x;
		p2.y += worldDelta.y;
		setEndpoint2(p2);
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
	public Integer getValue() {
		return (Integer) get(VALUE_KEY);
	}

	@Override
	public void setValue(Integer value) {
		set(VALUE_KEY, value);
	}

	@Override
	public int getPointsSize() {
		return getPoints().size();
	}

	@Override
	public List<Point> getPoints() {
		return get(POINTS_KEY);
	}

	@Override
	public Point getPoint(int index) {
		List<Point> points = getPoints();
		return index >= 0 ? points.get(index) : points.get(points.size() + index);
	}

	@Override
	public void setPoint(int index, Point point) {
		switch (index) {
		case 0:
			setEndpoint1(point);
			break;
		case -1:
		case 2:
			setEndpoint2(point);
			break;
		default:
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void setPoints(List<Point> points) {
		checkArgument(points.size() == 3, "Exactly 3 points must be specified");
		setEndpoint1(points.get(0));
		setEndpoint2(points.get(2));
	}

	@Override
	public Rectangle getBoundingBox() {
		return get(IHasBoundingBox.BOUNDING_BOX_KEY);
	}
}
