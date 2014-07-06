package org.archstudio.bna.keys;

import org.archstudio.bna.IThing;

public class ThingRefKey<T extends IThing> extends AbstractThingRefKey<T> {

	public static <T extends IThing> IThingRefKey<T> create(Object id) {
		return identity(new ThingRefKey<T>(id, true));
	}

	public static <T extends IThing> IThingRefKey<T> create(Object id, boolean nullable) {
		return identity(new ThingRefKey<T>(id, nullable));
	}

	protected ThingRefKey(Object id, boolean nullable) {
		super(id, nullable);
	}
}
