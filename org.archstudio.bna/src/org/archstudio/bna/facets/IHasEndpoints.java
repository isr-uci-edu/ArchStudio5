package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.AbstractCloneThingKey;
import org.archstudio.bna.keys.CloneThingKey;
import org.eclipse.swt.graphics.Point;

public interface IHasEndpoints extends IThing {

	public static final IThingKey<Point> ENDPOINT_1_KEY = CloneThingKey.create("endpoint1",
			AbstractCloneThingKey.point());
	public static final IThingKey<Point> ENDPOINT_2_KEY = CloneThingKey.create("endpoint2",
			AbstractCloneThingKey.point());

	public Point getEndpoint1();

	public Point getEndpoint2();
}
