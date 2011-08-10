package org.archstudio.bna.things;

import java.util.Arrays;
import java.util.List;

import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingListener;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasMutablePoints;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public abstract class AbstractPointsThing extends AbstractRelativeMovableThing implements IHasMutablePoints,
		IHasBoundingBox {

	public AbstractPointsThing(Object id) {
		super(id);
		addThingListener(new IThingListener() {
			@Override
			public <ET extends IThing, EK extends IThingKey<EV>, EV> void thingChanged(ThingEvent<ET, EK, EV> thingEvent) {
				if (!IHasBoundingBox.BOUNDING_BOX_KEY.equals(thingEvent.getPropertyName())) {
					set(IHasBoundingBox.BOUNDING_BOX_KEY, calculateBoundingBox());
				}
			}
		});
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		setPoints(Arrays.<Point> asList(new Point(0, 0), new Point(0, 0)));
		set(IHasBoundingBox.BOUNDING_BOX_KEY, calculateBoundingBox());
	}

	@Override
	abstract public List<Point> getPoints();

	@Override
	public Point getPoint(int index) {
		List<Point> points = getPoints();
		Point p = points.get(index < 0 ? points.size() + index : index);
		return new Point(p);
	}

	@Override
	public int getPointsSize() {
		return getPoints().size();
	}

	@Override
	abstract public void setPoints(List<Point> points);

	@Override
	public void setPoint(final int index, final Point point) {
		synchronizedUpdate(new Runnable() {
			@Override
			public void run() {
				List<Point> newPoints = getPoints();
				newPoints.set(index < 0 ? newPoints.size() + index : index, new Point(point));
				setPoints(newPoints);
			}
		});
	}

	@Override
	public void moveRelative(final Point worldDelta) {
		if (worldDelta.x != 0 || worldDelta.y != 0) {
			synchronizedUpdate(new Runnable() {
				@Override
				public void run() {
					List<Point> newPoints = getPoints();
					for (int i = 0; i < newPoints.size(); i++) {
						//TODO: optimize by only updating unstuck points
						Point p = newPoints.get(i);
						p.x += worldDelta.x;
						p.y += worldDelta.y;
					}
					setPoints(newPoints);
				}
			});
		}
	}

	protected Rectangle calculateBoundingBox() {
		List<Point> points = getPoints();
		Rectangle allPoints = new Rectangle(points.get(0), points.get(1));
		for (int i = 2; i < points.size(); i++) {
			allPoints.union(points.get(i));
		}
		return allPoints;
	}

	@Override
	public Rectangle getBoundingBox() {
		return get(BOUNDING_BOX_KEY);
	}
}
