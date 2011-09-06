package org.archstudio.bna.logics.coordinating;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nullable;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.keys.ThingKey;

import com.google.common.base.Function;

public class AbstractMirrorValueLogic<FROM_THING extends IThing, TO_THING extends IThing> extends
		AbstractPropagateValueLogic<FROM_THING, TO_THING, IThingKey<?>> {

	protected static class MirrorData {
		public final IThingKey<?> fromKey;
		@Nullable
		public final Function<?, ?> transformFunction;

		public MirrorData(IThingKey<?> fromKey, @Nullable Function<?, ?> fromToFunction) {
			super();
			this.fromKey = checkNotNull(fromKey);
			this.transformFunction = fromToFunction;
		}
	}

	protected static final IThingKey<MirrorData> MIRROR_DATA_KEY = ThingKey.create("mirrorData");

	public AbstractMirrorValueLogic(Class<FROM_THING> fromThingClass, Class<TO_THING> toThingClass) {
		super(fromThingClass, toThingClass);
	}

	public void unmirrorValue(TO_THING toThing, IThingKey<?> toKey) {
		unpropagate(toThing, toKey);
	}

	public void mirrorValue(final FROM_THING fromThing, final IThingKey<?> key, final TO_THING toThing) {
		mirrorValue(fromThing, key, toThing, key, null);
	}

	public <FV, TV> void mirrorValue(final FROM_THING fromThing, final IThingKey<FV> fromKey, final TO_THING toThing,
			final IThingKey<TV> toKey) {
		mirrorValue(fromThing, fromKey, toThing, toKey, null);
	}

	public <FV, TV> void mirrorValue(final FROM_THING fromThing, final IThingKey<FV> fromKey, final TO_THING toThing,
			final IThingKey<TV> toKey, @Nullable final Function<FV, TV> transformFunction) {
		checkNotNull(fromThing);
		checkNotNull(fromKey);
		checkNotNull(toThing);
		checkNotNull(toKey);

		toThing.synchronizedUpdate(new Runnable() {
			@Override
			public void run() {
				setSetting(toThing, toKey, MIRROR_DATA_KEY, new MirrorData(fromKey, transformFunction));
				setThingRef(toThing, toKey, fromThing.getID());
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doSynchronizedPropagation(IBNAModel model, FROM_THING fromThing,
			@Nullable ThingEvent<FROM_THING, ?, ?> fromThingEvent, TO_THING toThing,
			@Nullable ThingEvent<TO_THING, ?, ?> toThingEvent, IThingKey<?> toKey) {

		MirrorData mirrorData = getSetting(toThing, toKey, MIRROR_DATA_KEY);
		if (mirrorData == null) {
			return;
		}
		if (fromThingEvent != null && !mirrorData.fromKey.equals(fromThingEvent.getPropertyName())) {
			return;
		}

		Object value = fromThing.get(mirrorData.fromKey);
		if (mirrorData.transformFunction != null) {
			value = ((Function<Object, Object>) mirrorData.transformFunction).apply(value);
		}
		toThing.set((IThingKey<Object>) toKey, value);
	}
}
