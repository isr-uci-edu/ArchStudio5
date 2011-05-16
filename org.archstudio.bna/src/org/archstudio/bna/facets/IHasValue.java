package org.archstudio.bna.facets;

import org.archstudio.bna.keys.ThingKey;

public interface IHasValue {

	public static final ThingKey<Object, V> VALUE_KEY = ThingKey.createKey("value");

	public Object getValue();

}
