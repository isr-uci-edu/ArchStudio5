package org.archstudio.bna.keys;

public abstract class AbstractCloneThingKey<D, V> extends AbstractGenericThingKey<D, V> {

	protected AbstractCloneThingKey(D keyData, boolean isFireEventOnChange) {
		super(keyData, isFireEventOnChange);
	}

	@Override
	public final V preWrite(V value) {
		return clone(value);
	}

	@Override
	public final V postRead(V value) {
		return clone(value);
	}

	protected abstract V clone(V value);

}
