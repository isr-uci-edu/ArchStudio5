package org.archstudio.bna.keys;

import org.archstudio.bna.IThing;

public class ThingKey<D, V> extends AbstractGenericThingKey<D, V> {

	public static final <D, V> IThing.IThingKey<V> create(D keyData) {
		return new ThingKey<D, V>(keyData, true);
	}

	public static final <D, V> IThing.IThingKey<V> create(D keyData, boolean isFireEventOnChange) {
		return new ThingKey<D, V>(keyData, isFireEventOnChange);
	}

	protected ThingKey(D keyData, boolean isFireEventOnChange) {
		super(keyData, isFireEventOnChange);
	}
}
