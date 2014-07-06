package org.archstudio.bna.keys;

import static com.google.common.base.Preconditions.checkArgument;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IThing;
import org.eclipse.jdt.annotation.Nullable;

abstract class AbstractThingRefKey<T extends IThing> extends AbstractThingKey<Object> implements IThingKey<Object>,
		IThingRefKey<T> {

	protected AbstractThingRefKey(Object id, boolean nullable) {
		super(id, null, nullable);
	}

	@Override
	public @Nullable
	Object clone(@Nullable Object value) {
		checkArgument(!(value instanceof IThing),
				"Set thing reference value to a thing's ID, not the thing itself: %s", value);
		return super.clone(value);
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
