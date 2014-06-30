package org.archstudio.bna.keys;

import org.eclipse.jdt.annotation.Nullable;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

abstract class AbstractThingMetakey<N, K extends IThingKey<?>, V> extends AbstractThingKey<V> implements
		IThingMetakey<N, K, V> {

	protected final N name;
	protected final K key;

	protected AbstractThingMetakey(N name, K key, @Nullable Function<V, V> cloneFunction) {
		super(Lists.newArrayList(name, key), cloneFunction);
		this.name = name;
		this.key = key;
	}

	@Override
	public N getName() {
		return name;
	}

	@Override
	public K getKey() {
		return key;
	}

}
