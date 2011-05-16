package org.archstudio.bna.keys;

import java.util.Collection;

public abstract class AbstractCollectionOfTypeThingKey<D, C extends Collection<V>, V> extends
		AbstractCollectionThingKey<D, C, V> {

	protected AbstractCollectionOfTypeThingKey(D keyData, boolean isFireEventOnChange) {
		super(keyData, isFireEventOnChange);
	}
}