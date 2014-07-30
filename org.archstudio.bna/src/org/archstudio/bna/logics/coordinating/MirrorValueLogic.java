package org.archstudio.bna.logics.coordinating;

import static com.google.common.base.Preconditions.checkNotNull;

import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.logics.AbstractCoordinatingThingLogic;
import org.archstudio.bna.logics.coordinating.MirrorValueLogic.MirrorUpdater;
import org.archstudio.bna.utils.BNAUtils;

import com.google.common.base.Function;
import com.google.common.base.Functions;

public class MirrorValueLogic extends AbstractCoordinatingThingLogic<MirrorUpdater<?, ?>> implements IBNAModelListener {

	protected class MirrorUpdater<FV, TV> extends AbstractCoordinatingThingLogic.Updater {

		final Object fromThingID;
		final IThingKey<FV> fromKey;
		final Object toThingID;
		final IThingKey<TV> toKey;
		final Function<? super FV, ? extends TV> transformFunction;
		boolean updating;

		public MirrorUpdater(IThing fromThing, IThingKey<FV> fromKey, IThing toThing, IThingKey<TV> toKey,
				Function<? super FV, ? extends TV> transformFunction) {
			this.fromThingID = fromThing.getID();
			this.fromKey = fromKey;
			this.toThingID = toThing.getID();
			this.toKey = toKey;
			this.transformFunction = transformFunction;
		}

		@Override
		public void update(ThingEvent event) {
			if (updating) {
				return;
			}
			if (event != null) {
				if (event.getTargetThing().getID().equals(fromThingID)) {
					if (!event.getPropertyName().equals(fromKey)) {
						return;
					}
				}
				else {
					if (!event.getPropertyName().equals(toKey)) {
						return;
					}
				}
			}

			IThing fromThing = model.getThing(fromThingID);
			if (fromThing != null) {
				IThing toThing = model.getThing(toThingID);
				if (toThing != null) {
					updating = true;
					try {
						toThing.set(toKey, transformFunction.apply(fromThing.get(fromKey)));
					}
					finally {
						updating = false;
					}
				}
			}
		}
	}

	public MirrorValueLogic(IBNAWorld world) {
		super(world);
	}

	public <V> void mirrorValue(IThing fromThing, IThingKey<V> key, IThing toThing) {
		mirrorValue(fromThing, key, toThing, key, Functions.<V> identity());
	}

	public <V> void mirrorValue(IThing fromThing, IThingKey<V> fromKey, IThing toThing, IThingKey<V> toKey) {
		mirrorValue(fromThing, fromKey, toThing, toKey, Functions.<V> identity());
	}

	public <FV, TV> void mirrorValue(IThing fromThing, IThingKey<FV> fromKey, IThing toThing, IThingKey<TV> toKey,
			Function<? super FV, ? extends TV> transformFunction) {
		checkNotNull(fromThing);
		checkNotNull(fromKey);
		checkNotNull(toThing);
		checkNotNull(toKey);
		checkNotNull(transformFunction);
		BNAUtils.checkLock();

		MirrorUpdater<FV, TV> updater = new MirrorUpdater<FV, TV>(fromThing, fromKey, toThing, toKey, transformFunction);
		register(updater, toThing, toKey);
		removeWithThing(updater, fromThing, toThing);
		track(updater, fromThing);
	}

	public void unmirror(IThing toThing, IThingKey<?> toKey) {
		checkNotNull(toThing);
		checkNotNull(toKey);
		BNAUtils.checkLock();

		unregister(toThing, toKey);
	}
}
