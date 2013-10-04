package org.archstudio.bna.facets;

import java.util.List;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.CloneThingKey;
import org.archstudio.bna.keys.CollectionThingKey;
import org.eclipse.swt.graphics.Point;

public interface IHasPoints extends IThing {

	public static final IThingKey<List<Point>> POINTS_KEY = CollectionThingKey.create("points",
			CollectionThingKey.list(CloneThingKey.point()));

	public List<Point> getPoints();

	public Point getPoint(int index);

	public int getPointsSize();
}
