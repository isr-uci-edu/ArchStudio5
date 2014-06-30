package org.archstudio.bna.keys;

import org.archstudio.bna.IThing;

public class ThingRefKey<T extends IThing> extends AbstractThingRefKey<T> {

	public static <T extends IThing> IThingRefKey<T> create(Object id) {
		return identity(new ThingRefKey<T>(id));
	}

	protected ThingRefKey(Object id) {
		super(id);
	}
}
