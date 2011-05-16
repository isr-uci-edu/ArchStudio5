package org.archstudio.bna.keys;

import org.archstudio.bna.IThing;

public class ThingRefKey<D, T extends IThing> extends AbstractThingRefKey<D, T> {

	public static <D, T extends IThing> IThingRefKey<T> create(D keyData) {
		return new ThingRefKey<D, T>(keyData, true);
	}

	protected ThingRefKey(D keyData, boolean isFireEventOnChange) {
		super(keyData, isFireEventOnChange);
	}
}
