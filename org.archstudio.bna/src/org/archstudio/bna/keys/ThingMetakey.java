package org.archstudio.bna.keys;

import org.archstudio.bna.IThing.IThingKey;

public class ThingMetakey<N, K extends IThingKey<?>, V> extends AbstractThingMetakey<N, K, V> implements
		IThingMetakey<N, K, V> {

	public static <N, K extends IThingKey<?>, V> IThingMetakey<N, K, V> create(N name, K key) {
		return new ThingMetakey<N, K, V>(true, name, key);
	}

	public static <N, K extends IThingKey<?>, V> IThingMetakey<N, K, V> create(boolean isFireEventOnChange, N name,
			K key) {
		return new ThingMetakey<N, K, V>(isFireEventOnChange, name, key);
	}

	protected ThingMetakey(boolean isFireEventOnChange, N name, K key) {
		super(isFireEventOnChange, name, key);
	}

}
