package org.archstudio.bna.facets;

import java.util.List;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.CloneThingKey;
import org.eclipse.swt.graphics.Point;

public interface IHasMidpoints extends IThing {

	public static final IThingKey<List<Point>> MIDPOINTS_KEY = CloneThingKey.create("midpoints",
			CloneThingKey.list(CloneThingKey.point()));

	public List<Point> getMidpoints();

}
