package org.archstudio.bna.keys;

import static com.google.common.base.Preconditions.checkArgument;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.eclipse.jdt.annotation.Nullable;

public abstract class AbstractThingRefKey<D, T extends IThing> extends AbstractGenericThingKey<D, Object> implements
		IThingKey<Object>, IThingRefKey<T> {

	protected AbstractThingRefKey(D keyData, boolean isFireEventOnChange) {
		super(keyData, isFireEventOnChange);
	}

	@Override
	public @Nullable
	Object preWrite(@Nullable Object value) {
		checkArgument(!(value instanceof IThing), "Set this value to a thing's ID, not the thing itself: %s", value);
		return super.preWrite(value);
	}

	@Override
	@SuppressWarnings("unchecked")
	public T get(IThing fromThing, IBNAModel inModel) {
		return (T) inModel.getThing(fromThing.get(this));
	}

	@Override
	public void set(IThing fromThing, T thing) {
		fromThing.set(this, thing.getID());
	}
}
