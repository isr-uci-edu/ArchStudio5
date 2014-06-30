package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;
import org.eclipse.swt.graphics.Point;

public interface IHasEndpoints extends IThing {

	public static final IThingKey<Point> ENDPOINT_1_KEY = ThingKey.create("endpoint1", ThingKey.point());
	public static final IThingKey<Point> ENDPOINT_2_KEY = ThingKey.create("endpoint2", ThingKey.point());

	public Point getEndpoint1();

	public Point getEndpoint2();
}
