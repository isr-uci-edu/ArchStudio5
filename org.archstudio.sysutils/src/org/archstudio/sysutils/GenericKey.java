package org.archstudio.sysutils;

public class GenericKey<D, V> extends AbstractGenericKey<D, V> {

	public static <D, V> TypedMap.Key<V> create(D keyData) {
		return new GenericKey<D, V>(keyData);
	}

	protected GenericKey(D keyData) {
		super(keyData);
	}
}
