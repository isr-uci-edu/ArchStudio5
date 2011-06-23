package org.archstudio.bna.keys;

import org.archstudio.bna.IThing.IThingKey;

import com.google.common.base.Function;

public class CloneThingKey<D, V> extends AbstractCloneThingKey<D, V> implements IThingKey<V> {

	public static final <D, V> IThingKey<V> create(D keyData, Function<V, V> cloneFunction) {
		return new CloneThingKey<D, V>(keyData, true, cloneFunction);
	}

	public static final <D, V> IThingKey<V> create(D keyData, boolean isFireEventOnChange, Function<V, V> cloneFunction) {
		return new CloneThingKey<D, V>(keyData, isFireEventOnChange, cloneFunction);
	}

	public CloneThingKey(D keyData, boolean isFireEventOnChange, Function<V, V> cloneFunction) {
		super(keyData, isFireEventOnChange, cloneFunction);
	}
}
