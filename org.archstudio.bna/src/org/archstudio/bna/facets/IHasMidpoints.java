package org.archstudio.bna.facets;

import java.util.List;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;
import org.eclipse.swt.graphics.Point;

public interface IHasMidpoints extends IThing {

	public static final IThingKey<List<Point>> MIDPOINTS_KEY = ThingKey.create("midpoints",
			ThingKey.list(ThingKey.point()));

	public List<Point> getMidpoints();

}
