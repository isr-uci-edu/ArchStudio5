package org.archstudio.bna.logics.coordinating;

import static com.google.common.base.Preconditions.checkNotNull;

import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.logics.AbstractCoordinatingThingLogic;

import com.google.common.base.Function;
import com.google.common.base.Functions;

public class MirrorValueLogic extends AbstractCoordinatingThingLogic implements IBNAModelListener {

	private class MirrorUpdater<FV, TV> extends Updater {

		final Object fromThingID;
		final IThingKey<FV> fromKey;
		final Object toThingID;
		final IThingKey<TV> toKey;
		final Function<? super FV, ? extends TV> transformFunction;

		public MirrorUpdater(IThing fromThing, IThingKey<FV> fromKey, IThing toThing, IThingKey<TV> toKey,
				Function<? super FV, ? extends TV> transformFunction) {
			this.fromThingID = fromThing.getID();
			this.fromKey = fromKey;
			this.toThingID = toThing.getID();
			this.toKey = toKey;
			this.transformFunction = transformFunction;
		}

		@Override
		public void update() {
			IThing fromThing = model.getThing(fromThingID);
			if (fromThing != null) {
				FV value = fromThing.get(fromKey);
				IThing toThing = model.getThing(toThingID);
				if (toThing != null) {
					toThing.set(toKey, transformFunction.apply(value));
				}
			}
		}
	}

	public MirrorValueLogic(IBNAWorld world) {
		super(world);
	}

	synchronized public <V> void mirrorValue(IThing fromThing, IThingKey<V> key, IThing toThing) {
		mirrorValue(fromThing, key, toThing, key, Functions.<V> identity());
	}

	synchronized public <V> void mirrorValue(IThing fromThing, IThingKey<V> fromKey, IThing toThing, IThingKey<V> toKey) {
		mirrorValue(fromThing, fromKey, toThing, toKey, Functions.<V> identity());
	}

	synchronized public <FV, TV> void mirrorValue(IThing fromThing, IThingKey<FV> fromKey, IThing toThing,
			IThingKey<TV> toKey, Function<? super FV, ? extends TV> transformFunction) {
		checkNotNull(fromThing);
		checkNotNull(fromKey);
		checkNotNull(toThing);
		checkNotNull(toKey);
		checkNotNull(transformFunction);

		MirrorUpdater<FV, TV> updater = new MirrorUpdater<FV, TV>(fromThing, fromKey, toThing, toKey, transformFunction);
		register(updater, toThing, toKey);

		track(updater, fromThing, fromKey);
		track(updater, toThing, toKey);
	}

	synchronized public void unmirror(IThing toThing, IThingKey<?> toKey) {
		checkNotNull(toThing);
		checkNotNull(toKey);

		unregister(toThing, toKey);
	}
}
