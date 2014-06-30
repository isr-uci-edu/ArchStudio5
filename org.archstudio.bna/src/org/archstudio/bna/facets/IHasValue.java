package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;

public interface IHasValue<V> extends IThing {

	public IThingKey<Object> VALUE_KEY = ThingKey.create("value");

	public V getValue();

}
