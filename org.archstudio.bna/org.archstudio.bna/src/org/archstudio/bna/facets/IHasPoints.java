package org.archstudio.bna.facets;

import java.util.List;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.ListOfPointsThingKey;
import org.eclipse.draw2d.geometry.Point;

public interface IHasPoints extends IThing {

	public static final IThingKey<List<Point>> POINTS_KEY = ListOfPointsThingKey.create("points");

	public List<Point> getPoints();

	public Point getPoint(int index);

	public int getPointsSize();
}
