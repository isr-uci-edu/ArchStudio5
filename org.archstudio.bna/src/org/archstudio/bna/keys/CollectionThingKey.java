package org.archstudio.bna.keys;

import java.util.Collection;

import org.archstudio.bna.IThing.IThingKey;

import com.google.common.base.Function;

public class CollectionThingKey<D, C extends Collection<V>, V> extends AbstractCollectionThingKey<D, C, V> {

	public static <D, C extends Collection<V>, V> IThingKey<C> create(D keyData,
			final Function<Collection<V>, C> cloneFunction) {
		return new CollectionThingKey<D, C, V>(keyData, true, cloneFunction);
	}

	public CollectionThingKey(D keyData, boolean isFireEventOnChange, Function<? super C, C> cloneFunction) {
		super(keyData, isFireEventOnChange, cloneFunction);
	}
}
