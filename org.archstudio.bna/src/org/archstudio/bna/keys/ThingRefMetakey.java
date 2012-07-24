package org.archstudio.bna.keys;

import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;

public class ThingRefMetakey<T extends IThing, N, K extends IThingKey<?>> extends AbstractThingRefMetakey<T, N, K>
		implements IThingRefMetakey<T, N, K> {

	public static <D, T extends IThing, N, K extends IThingKey<?>> IThingRefMetakey<T, N, K> create(N name, K key) {
		return new ThingRefMetakey<T, N, K>(true, name, key);
	}

	public ThingRefMetakey(boolean isFireEventOnChange, N name, K key) {
		super(isFireEventOnChange, name, key);
	}

}
