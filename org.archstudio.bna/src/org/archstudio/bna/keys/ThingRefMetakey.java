package org.archstudio.bna.keys;

import org.archstudio.bna.IThing;

public class ThingRefMetakey<T extends IThing, N, K extends IThingKey<?>> extends AbstractThingRefMetakey<T, N, K>
		implements IThingRefMetakey<T, N, K> {

	public static <D, T extends IThing, N, K extends IThingKey<?>> IThingRefMetakey<T, N, K> create(N name, K key) {
		return identity(new ThingRefMetakey<T, N, K>(name, key, true));
	}

	public static <D, T extends IThing, N, K extends IThingKey<?>> IThingRefMetakey<T, N, K> create(N name, K key,
			boolean nullable) {
		return identity(new ThingRefMetakey<T, N, K>(name, key, nullable));
	}

	public ThingRefMetakey(N name, K key, boolean nullable) {
		super(name, key, nullable);
	}

}
