package org.archstudio.bna.facets;

import java.util.Set;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.CloneThingKey;
import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IHasShapeKeys extends IThing {

	public static final IThingKey<Set<IThingKey<?>>> SHAPE_MODIFYING_KEYS_KEY = CloneThingKey.create(
			"shapeModifyingKeys", CloneThingKey.set(CloneThingKey.iThingKey()));

	public void addShapeModifyingKey(IThingKey<?> key);

	public Set<IThingKey<?>> getShapeModifyingKeys();

	public boolean isShapeModifyingKey(IThingKey<?> key);

}
