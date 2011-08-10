package org.archstudio.bna.facets;

import java.util.List;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.AbstractCollectionThingKey;
import org.archstudio.bna.keys.CollectionThingKey;
import org.eclipse.draw2d.geometry.Point;

public interface IHasPoints extends IThing {

	public static final IThingKey<List<Point>> POINTS_KEY = CollectionThingKey.create("points",
			AbstractCollectionThingKey.list(AbstractCollectionThingKey.point()));

	public List<Point> getPoints();

	public Point getPoint(int index);

	public int getPointsSize();
}
