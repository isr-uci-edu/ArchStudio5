package org.archstudio.bna.facets;

import java.util.List;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;
import org.eclipse.swt.graphics.Point;

public interface IHasPoints extends IThing {

	public static final IThingKey<List<Point>> POINTS_KEY = ThingKey.create("points", ThingKey.list(ThingKey.point()));

	public List<Point> getPoints();

	public Point getPoint(int index);

	public int getPointsSize();
}
