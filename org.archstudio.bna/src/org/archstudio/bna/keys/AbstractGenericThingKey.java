package org.archstudio.bna.keys;

import org.archstudio.bna.IThing;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.sysutils.AbstractGenericKey;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

@NonNullByDefault
public abstract class AbstractGenericThingKey<D, V> extends AbstractGenericKey<D, V> implements IThing.IThingKey<V> {

	private int uid = 0;
	private final boolean isFireEventOnChange;

	protected AbstractGenericThingKey(D keyData, boolean isFireEventOnChange) {
		super(keyData);
		this.isFireEventOnChange = isFireEventOnChange;
		BNAUtils.registerKey(this);
	}

	@Override
	public Object getID() {
		return getKeyData();
	}

	@Override
	public int getUID() {
		return uid;
	}

	@Override
	public void setUID(int uid) {
		this.uid = uid;
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