package org.archstudio.bna.keys;

import java.util.List;

import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;

import com.google.common.collect.Lists;

public abstract class AbstractThingRefMetakey<T extends IThing, N, K extends IThingKey<?>> extends
		AbstractThingRefKey<List<?>, T> implements IThingRefMetakey<T, N, K> {

	protected final N name;
	protected final K key;

	protected AbstractThingRefMetakey(boolean isFireEventOnChange, N name, K key) {
		super(Lists.newArrayList(name, key), isFireEventOnChange);
		this.name = name;
		this.key = key;
	}

	public N getName() {
		return name;
	}

	public K getKey() {
		return key;
	}
}
