package org.archstudio.bna.facets;

import java.util.Set;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;
import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IHasShapeKeys extends IThing {

	public static final IThingKey<Set<IThingKey<?>>> SHAPE_MODIFYING_KEYS_KEY = ThingKey.create("shapeModifyingKeys",
			ThingKey.<IThingKey<?>> set(null));

	public void addShapeModifyingKey(IThingKey<?> key);

	public Set<IThingKey<?>> getShapeModifyingKeys();

	public boolean isShapeModifyingKey(IThingKey<?> key);

}
