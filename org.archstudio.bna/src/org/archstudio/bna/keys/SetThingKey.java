package org.archstudio.bna.keys;

import java.util.Collections;
import java.util.Set;

import org.archstudio.bna.IThing;

import com.google.common.collect.Sets;

public class SetThingKey<D, V> extends AbstractCollectionThingKey<D, Set<V>, V> {

	public static final <D, V> IThing.IThingKey<Set<V>> create(D keyData) {
		return new SetThingKey<D, V>(keyData, true);
	}

	protected SetThingKey(D keyData, boolean isFireEventOnChange) {
		super(keyData, isFireEventOnChange);
	}

	@Override
	public Set<V> postRead(Set<V> value) {
		if (value == null) {
			return Collections.emptySet();
		}
		return value;
	};

	@Override
	protected Set<V> cloneReadOnly(Set<V> value) {
		return Collections.unmodifiableSet(Sets.newHashSet(value));
	}
}
