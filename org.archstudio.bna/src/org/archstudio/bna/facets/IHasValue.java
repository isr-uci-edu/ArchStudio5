package org.archstudio.bna.facets;

import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.keys.ThingKey;

public interface IHasValue<V> {

	public IThingKey<Object> VALUE_KEY = ThingKey.create("value");

	public V getValue();

}
