package org.archstudio.bna.facets;

import java.awt.geom.Point2D;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.IThingRefKey;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.keys.ThingRefKey;
import org.eclipse.swt.graphics.Point;

public interface IHasInternalWorldEndpoint extends IThing {

	public static final IThingRefKey<IHasWorld> INTERNAL_ENDPOINT_WORLD_THING_KEY = ThingRefKey
			.create("&internalEndpointWorldThingID");
	public static final IThingKey<Point> INTERNAL_ENDPOINT_KEY = ThingKey.create("internalEndpoint", ThingKey.point());
	public static final IThingKey<Point2D> EXTERNAL_ENDPOINT_KEY = ThingKey.create("externalEndpoint", ThingKey.point2D());

	public Object getInternalEndpointWorldThingID();

	public Point getInternalEndpoint();

	public Point2D getExternalEndpoint();

}
