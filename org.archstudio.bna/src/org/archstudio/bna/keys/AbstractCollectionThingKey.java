package org.archstudio.bna.keys;

import java.util.Collection;

public abstract class AbstractCollectionThingKey<D, C extends Collection<V>, V> extends AbstractGenericThingKey<D, C> {

	protected AbstractCollectionThingKey(D keyData, boolean isFireEventOnChange) {
		super(keyData, isFireEventOnChange);
	}

	@Override
	public C preWrite(C value) {
		return cloneReadOnly(value);
	}

	abstract protected C cloneReadOnly(C value);
}
