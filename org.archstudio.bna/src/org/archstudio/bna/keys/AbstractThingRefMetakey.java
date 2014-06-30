package org.archstudio.bna.keys;

import org.archstudio.bna.IThing;

import com.google.common.collect.Lists;

abstract class AbstractThingRefMetakey<T extends IThing, N, K extends IThingKey<?>> extends AbstractThingRefKey<T>
		implements IThingRefMetakey<T, N, K> {

	protected final N name;
	protected final K key;

	protected AbstractThingRefMetakey(N name, K key) {
		super(Lists.newArrayList(name, key));
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
