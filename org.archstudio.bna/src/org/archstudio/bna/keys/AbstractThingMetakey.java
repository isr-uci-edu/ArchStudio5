package org.archstudio.bna.keys;

import java.util.List;

import org.archstudio.bna.IThing.IThingKey;

import com.google.common.collect.Lists;

public abstract class AbstractThingMetakey<N, K extends IThingKey<?>, V> extends AbstractGenericThingKey<List<?>, V>
		implements IThingMetakey<N, K, V> {

	protected final N name;
	protected final K key;

	protected AbstractThingMetakey(boolean isFireEventOnChange, N name, K key) {
		super(Lists.newArrayList(name, key), isFireEventOnChange);
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
