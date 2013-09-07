package org.archstudio.bna.facets;

import java.awt.geom.Point2D;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.AbstractCloneThingKey;
import org.archstudio.bna.keys.CloneThingKey;

public interface IHasMutableMappingPoint extends IThing {

	IThingKey<Point2D> MAPPING_POINT_KEY = CloneThingKey.create("mappingPoint", false, AbstractCloneThingKey.point2D());

	public void setMappingPoint(Point2D point);

	public Point2D getMappingPoint();
}
