package org.archstudio.bna.logics.coordinating;

import javax.annotation.Nullable;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.ThingEvent;

import com.google.common.base.Function;

public class MirrorValueLogic extends AbstractPropagateValueLogic<IThing, IThing, Function<Object, Object>> {

	public MirrorValueLogic() {
		super(IThing.class, IThing.class);
	}

	public void mirrorValue(IThing fromThing, IThingKey<?> key, IThing... toThings) {
		setPropagate(fromThing, key, key, null, toThings);
	}

	@SuppressWarnings("unchecked")
	public <F, T> void mirrorValue(IThing fromThing, IThingKey<F> fromKey, IThingKey<T> toKey, Function<F, T> function,
			IThing... toThings) {
		setPropagate(fromThing, fromKey, toKey, (Function<Object, Object>) function, toThings);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doPropagation(IBNAModel model, IThing fromThing, IThingKey<?> fromKey,
			@Nullable ThingEvent<IThing, ?, ?> fromThingEvent, Function<Object, Object> data, IThing toThing,
			IThingKey<?> toKey, @Nullable ThingEvent<IThing, ?, ?> toThingEvent) {

		if (data == null) {
			toThing.set((IThingKey<Object>) toKey, fromThing.get(fromKey));
		}
		else {
			toThing.set((IThingKey<Object>) toKey, data.apply(fromThing.get(fromKey)));
		}
	}
}
