package org.archstudio.bna.facets;

import java.util.Collection;
import java.util.Set;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.AbstractCollectionThingKey;
import org.archstudio.bna.keys.CollectionThingKey;
import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IHasShapeKeys extends IThing {

	public static final IThingKey<Set<IThingKey<?>>> SHAPE_MODIFYING_KEYS_KEY = CollectionThingKey.create(
			"shapeModifyingKeys", AbstractCollectionThingKey.set(CollectionThingKey.iThingKey()));

	public void addShapeModifyingKey(IThingKey<?> key);

	public Collection<IThingKey<?>> getShapeModifyingKeys();

	public boolean isShapeModifyingKey(IThingKey<?> key);

}
