package org.archstudio.bna.keys;

import org.eclipse.jdt.annotation.Nullable;

import com.google.common.base.Function;

public class ThingMetakey<N, K extends IThingKey<?>, V> extends AbstractThingMetakey<N, K, V> implements
		IThingMetakey<N, K, V> {

	public static <N, K extends IThingKey<?>, V> IThingMetakey<N, K, V> create(N name, K key) {
		return create(name, key, null);
	}

	public static <N, K extends IThingKey<?>, V> IThingMetakey<N, K, V> create(N name, K key,
			Function<V, V> cloneFunction) {
		return identity(new ThingMetakey<N, K, V>(name, key, cloneFunction, false));
	}

	public static <N, K extends IThingKey<?>, V> IThingMetakey<N, K, V> create(N name, K key,
			Function<V, V> cloneFunction, boolean nullable) {
		return identity(new ThingMetakey<N, K, V>(name, key, cloneFunction, nullable));
	}

	protected ThingMetakey(N name, K key, @Nullable Function<V, V> cloneFunction, boolean nullable) {
		super(name, key, cloneFunction, nullable);
	}

}
