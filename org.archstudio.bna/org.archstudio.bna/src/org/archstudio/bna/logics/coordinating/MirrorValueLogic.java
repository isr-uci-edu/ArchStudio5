package org.archstudio.bna.logics.coordinating;

import javax.annotation.Nullable;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.ThingEvent;

public class MirrorValueLogic extends AbstractPropagateValueLogic<IThing, IThing, Object> {

	public MirrorValueLogic() {
		super(IThing.class, IThing.class);
	}

	public void mirrorValue(IThing fromThing, IThingKey<?> key, IThing... toThings) {
		setPropagate(fromThing, key, key, null, toThings);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doPropagation(IBNAModel model, IThing fromThing, IThingKey<?> fromKey,
			@Nullable ThingEvent<IThing, ?, ?> fromThingEvent, Object data, IThing toThing, IThingKey<?> toKey,
			@Nullable ThingEvent<IThing, ?, ?> toThingEvent) {

		toThing.set((IThingKey<Object>) toKey, fromThing.get(fromKey));
	}
}
