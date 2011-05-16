package org.archstudio.bna.keys;

import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;

public class ThingRefKeyKey<T extends IThing, N, K extends IThingKey<?>> extends AbstractThingRefKeyKey<T, N, K>
		implements IThingRefKeyKey<T, N, K> {

	public static <D, T extends IThing, N, K extends IThingKey<?>> IThingRefKeyKey<T, N, K> create(N name, K key) {
		return new ThingRefKeyKey<T, N, K>(true, name, key);
	}

	public ThingRefKeyKey(boolean isFireEventOnChange, N name, K key) {
		super(isFireEventOnChange, name, key);
	}

}
