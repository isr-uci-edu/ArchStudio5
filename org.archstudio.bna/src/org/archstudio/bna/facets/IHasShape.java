package org.archstudio.bna.facets;

import java.util.Set;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.AbstractCollectionThingKey;
import org.archstudio.bna.keys.CollectionThingKey;

public interface IHasShape extends IThing {

	public static final IThingKey<Set<Object>> SHAPE_MODIFYING_KEYS_KEY = CollectionThingKey.create(
			"shapeModifyingKeys", AbstractCollectionThingKey.set(null));

	public void addShapeModifyingKey(IThingKey<?> key);

	public boolean isShapeModifyingKey(IThingKey<?> key);

}
