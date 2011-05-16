package org.archstudio.bna.things;

import java.util.Arrays;
import java.util.List;

import org.archstudio.bna.facets.IHasMutablePoints;
import org.eclipse.draw2d.geometry.Point;

import com.google.common.collect.Lists;

public abstract class AbstractPointsThing extends AbstractRelativeMovableThing implements IHasMutablePoints {

	public AbstractPointsThing(Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		setPoints(Arrays.asList(new Point(0, 0), new Point(0, 0)));
	}

	@Override
	public List<Point> getPoints() {
		return get(POINTS_KEY);
	}

	@Override
	public Point getPoint(int index) {
		List<Point> points = getRaw(POINTS_KEY);
		Point p = points.get(index < 0 ? points.size() + index : index);
		return new Point(p);
	}

	@Override
	public int getPointsSize() {
		return getRaw(POINTS_KEY).size();
	}

	@Override
	public void setPoints(List<Point> points) {
		set(POINTS_KEY, points);
	}

	@Override
	public void setPoint(final int index, final Point point) {
		synchronizedUpdate(new Runnable() {
			@Override
			public void run() {
				List<Point> newPoints = Lists.newArrayList(getRaw(POINTS_KEY));
				newPoints.set(index < 0 ? newPoints.size() + index : index, new Point(point));
				setPoints(newPoints);
			}
		});
	}

	@Override
	public Point getReferencePoint() {
		//TODO: return the first unstuck point
		return getPoint(0);
	}

	@Override
	public void moveRelative(final Point worldDelta) {
		if (worldDelta.x != 0 || worldDelta.y != 0) {
			synchronizedUpdate(new Runnable() {
				@Override
				public void run() {
					List<Point> newPoints = Lists.newArrayList(getRaw(POINTS_KEY));
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
}
