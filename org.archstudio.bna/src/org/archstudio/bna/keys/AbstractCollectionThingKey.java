package org.archstudio.bna.keys;

import java.util.Collection;

import com.google.common.base.Function;

public abstract class AbstractCollectionThingKey<D, C extends Collection<V>, V> extends AbstractCloneThingKey<D, C> {

	protected AbstractCollectionThingKey(D keyData, boolean isFireEventOnChange, Function<? super C, C> cloneFunction) {
		super(keyData, isFireEventOnChange, cloneFunction);
	}
}
