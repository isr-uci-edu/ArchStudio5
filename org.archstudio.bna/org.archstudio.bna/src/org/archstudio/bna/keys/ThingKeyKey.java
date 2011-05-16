package org.archstudio.bna.keys;

import org.archstudio.bna.IThing.IThingKey;

public class ThingKeyKey<N, K extends IThingKey<?>, V> extends AbstractThingKeyKey<N, K, V> implements
		IThingKeyKey<N, K, V> {

	public static <N, K extends IThingKey<?>, V> IThingKeyKey<N, K, V> create(N name, K key) {
		return new ThingKeyKey<N, K, V>(true, name, key);
	}

	public static <N, K extends IThingKey<?>, V> IThingKeyKey<N, K, V> create(boolean isFireEventOnChange, N name, K key) {
		return new ThingKeyKey<N, K, V>(isFireEventOnChange, name, key);
	}

	protected ThingKeyKey(boolean isFireEventOnChange, N name, K key) {
		super(isFireEventOnChange, name, key);
	}

}
