package org.archstudio.bna.things.glass;

import org.archstudio.bna.facets.IHasWorld;
import org.archstudio.bna.keys.CloneThingKey;
import org.archstudio.bna.keys.IThingRefKey;
import org.archstudio.bna.keys.ThingRefKey;
import org.eclipse.draw2d.geometry.Point;

/*
 * Same as an ordinary SplineThing, except that WORLD_POINT_2 is interpreted by
 * the peer to be in the coordinate space of the WORLD_REF and is used to set
 * the ENDPOINT_2
 */
// FIXME: setting the world point in the peer will cause problems if multiple views of the model are deployed
public class MappingGlassThing extends SplineGlassThing {

	public static final IThingRefKey<IHasWorld> WORLD_REF_KEY = ThingRefKey.create("&worldThingID");
	public static final IThingKey<Point> WORLD_POINT_KEY = CloneThingKey.create("innerWorldPoint",
			CloneThingKey.point());

	public MappingGlassThing(String id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		setWorldPoint(new Point(0, 0));
	}

	public void setWorldRef(Object worldThingID) {
		set(WORLD_REF_KEY, worldThingID);
	}

	public Object getWorldRef() {
		return get(WORLD_REF_KEY);
	}

	public void setWorldPoint(Point foreignPoint) {
		set(WORLD_POINT_KEY, foreignPoint);
	}

	public Point getWorldPoint() {
		return get(WORLD_POINT_KEY);
	}

}
