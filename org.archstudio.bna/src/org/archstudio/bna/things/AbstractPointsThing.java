package org.archstudio.bna.things;

import java.util.List;

import org.archstudio.bna.IThingListener;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasMutablePoints;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

import com.google.common.collect.Lists;

@NonNullByDefault
public abstract class AbstractPointsThing extends AbstractRelativeMovableThing implements IHasMutablePoints,
		IHasBoundingBox {

	public AbstractPointsThing(Object id) {
		super(id);
		addThingListener(new IThingListener() {
			@Override
			public void thingChanged(ThingEvent thingEvent) {
				if (!IHasBoundingBox.BOUNDING_BOX_KEY.equals(thingEvent.getPropertyName())) {
					set(IHasBoundingBox.BOUNDING_BOX_KEY, calculateBoundingBox());
				}
			}
		});
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		setPoints(Lists.newArrayList(new Point(0, 0), new Point(0, 0)));
		set(IHasBoundingBox.BOUNDING_BOX_KEY, calculateBoundingBox());
	}

	@Override
	abstract public List<Point> getPoints();

	@Override
	public Point getPoint(int index) {
		List<Point> points = getPoints();
		Point p = points.get(index < 0 ? points.size() + index : index);
		return new Point(p.x, p.y);
	}

	@Override
	public int getPointsSize() {
		return getPoints().size();
	}

	@Override
	abstract public void setPoints(List<Point> points);

	@Override
	public void setPoint(final int index, final Point point) {
		List<Point> newPoints = getPoints();
		newPoints.set(index < 0 ? newPoints.size() + index : index, new Point(point.x, point.y));
		setPoints(newPoints);
	}

	@Override
	public void moveRelative(final Point worldDelta) {
		if (worldDelta.x != 0 || worldDelta.y != 0) {
			List<Point> newPoints = getPoints();
			for (int i = 0; i < newPoints.size(); i++) {
				//TODO: optimize by only updating unstuck points
				Point p = newPoints.get(i);
				p.x += worldDelta.x;
				p.y += worldDelta.y;
			}
			setPoints(newPoints);
		}
	}

	protected Rectangle calculateBoundingBox() {
		List<Point> points = getPoints();
		int minX1 = Integer.MAX_VALUE;
		int maxX1 = Integer.MIN_VALUE;
		int minY1 = Integer.MAX_VALUE;
		int maxY1 = Integer.MIN_VALUE;
		for (Point p : points) {
			if (minX1 > p.x) {
				minX1 = p.x;
			}
			if (maxX1 < p.x) {
				maxX1 = p.x;
			}
			if (minY1 > p.y) {
				minY1 = p.y;
			}
			if (maxY1 < p.y) {
				maxY1 = p.y;
			}
		}
		return new Rectangle(minX1, minY1, maxX1 - minX1 + 1, maxY1 - minY1 + 1);
	}

	@Override
	public Rectangle getBoundingBox() {
		return get(BOUNDING_BOX_KEY, new Rectangle(0, 0, 0, 0));
	}
}
