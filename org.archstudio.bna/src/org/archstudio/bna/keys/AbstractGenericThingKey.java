package org.archstudio.bna.keys;

import org.archstudio.bna.IThing;
import org.archstudio.sysutils.AbstractGenericKey;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

@NonNullByDefault
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
	public @Nullable
	V preWrite(@Nullable V value) {
		return value;
	}

	@Override
	public @Nullable
	V postRead(@Nullable V value) {
		return value;
	}

}