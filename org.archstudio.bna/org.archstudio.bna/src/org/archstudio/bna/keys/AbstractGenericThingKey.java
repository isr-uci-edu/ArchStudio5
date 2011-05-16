package org.archstudio.bna.keys;

import org.archstudio.bna.IThing;
import org.archstudio.sysutils.AbstractGenericKey;

public abstract class AbstractGenericThingKey<D, V> extends AbstractGenericKey<D, V> implements IThing.IThingKey<V> {

	private final boolean isFireEventOnChange;

	protected AbstractGenericThingKey(D keyData, boolean isFireEventOnChange) {
		super(keyData);
		this.isFireEventOnChange = isFireEventOnChange;
	}

	@Override
	public boolean isFireEventOnChange() {
		return isFireEventOnChange;
	}

	@Override
	public V preWrite(V value) {
		return value;
	}

	@Override
	public V postRead(V value) {
		return value;
	}

}