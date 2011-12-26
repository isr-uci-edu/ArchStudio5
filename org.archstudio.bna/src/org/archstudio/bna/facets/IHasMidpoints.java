package org.archstudio.bna.facets;

import java.util.List;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.AbstractCloneThingKey;
import org.archstudio.bna.keys.AbstractCollectionThingKey;
import org.archstudio.bna.keys.CollectionThingKey;
import org.eclipse.swt.graphics.Point;

public interface IHasMidpoints extends IThing {

	public static final IThingKey<List<Point>> MIDPOINTS_KEY = CollectionThingKey.create("midpoints",
			AbstractCollectionThingKey.list(AbstractCloneThingKey.point()));

	public List<Point> getMidpoints();

}
