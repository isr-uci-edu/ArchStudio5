package org.archstudio.bna.logics.coordinating;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.sysutils.FastLongMap;

import com.google.common.base.Function;
import com.google.common.base.Functions;

public class MirrorValueLogic extends AbstractThingLogic implements IBNAModelListener {

	private static class Mirror<FV, TV> {

		boolean isUpdating = false;

		final Object fromThingID;
		final IThingKey<FV> fromKey;
		final Object toThingID;
		final IThingKey<TV> toKey;
		final Function<FV, TV> transformFunction;

		public Mirror(Object fromThingID, IThingKey<FV> fromKey, Object toThingID, IThingKey<TV> toKey,
				Function<FV, TV> transformFunction) {
			this.fromThingID = fromThingID;
			this.fromKey = fromKey;
			this.toThingID = toThingID;
			this.toKey = toKey;
			this.transformFunction = transformFunction;
		}

		public void apply(IBNAModel model) {
			if (isUpdating) {
				return;
			}

			IThing fromThing = model.getThing(fromThingID);
			IThing toThing = model.getThing(toThingID);
			if (fromThing != null && toThing != null) {
				isUpdating = true;
				try {
					toThing.set(toKey, transformFunction.apply(fromThing.get(fromKey)));
				}
				finally {
					isUpdating = false;
				}
			}
		}
	}

	private class Registrar {

		final int fromThingUID;
		final IThingKey<?> fromKey;
		final int toThingUID;
		final IThingKey<?> toKey;
		final Mirror<?, ?> mirror;

		public Registrar(int fromThingUID, IThingKey<?> fromKey, int toThingUID, IThingKey<?> toKey, Mirror<?, ?> mirror) {
			this.fromThingUID = fromThingUID;
			this.fromKey = fromKey;
			this.toThingUID = toThingUID;
			this.toKey = toKey;
			this.mirror = mirror;
		}

		public void register() {
			FastLongMap.getList(mirrors, BNAUtils.getThingKeyUID(fromThingUID, fromKey.getUID()), true).add(mirror);
			FastLongMap.getList(mirrors, BNAUtils.getThingKeyUID(toThingUID, toKey.getUID()), true).add(mirror);
			registrars.put(BNAUtils.getThingKeyUID(toThingUID, toKey.getUID()), this);
		}

		public void unregister() {
			FastLongMap.getList(mirrors, BNAUtils.getThingKeyUID(fromThingUID, fromKey.getUID()), false).remove(mirror);
			FastLongMap.getList(mirrors, BNAUtils.getThingKeyUID(toThingUID, toKey.getUID()), false).remove(mirror);
			registrars.remove(BNAUtils.getThingKeyUID(toThingUID, toKey.getUID()));
		}
	}

	public MirrorValueLogic() {
	}

	FastLongMap<List<Mirror<?, ?>>> mirrors = new FastLongMap<>(256);
	FastLongMap<Registrar> registrars = new FastLongMap<Registrar>(128);

	public <V> void mirrorValue(IThing fromThing, IThingKey<V> key, IThing toThing) {
		mirrorValue(fromThing, key, toThing, key, Functions.<V> identity());
	}

	public <V> void mirrorValue(IThing fromThing, IThingKey<V> fromKey, IThing toThing, IThingKey<V> toKey) {
		mirrorValue(fromThing, fromKey, toThing, toKey, Functions.<V> identity());
	}

	public <FV, TV> void mirrorValue(IThing fromThing, IThingKey<FV> fromKey, IThing toThing, IThingKey<TV> toKey,
			Function<FV, TV> transformFunction) {
		checkNotNull(fromThing);
		checkNotNull(fromKey);
		checkNotNull(toThing);
		checkNotNull(toKey);
		checkNotNull(transformFunction);

		unmirror(toThing, toKey);

		Mirror<FV, TV> mirror = new Mirror<FV, TV>(fromThing.getID(), fromKey, toThing.getID(), toKey,
				transformFunction);
		Registrar registrar = new Registrar(fromThing.getUID(), fromKey, toThing.getUID(), toKey, mirror);
		registrar.register();

		mirror.apply(getBNAModel());
	}

	public void unmirror(IThing toThing, IThingKey<?> toKey) {
		Registrar registrar = registrars.get(BNAUtils.getThingKeyUID(toThing, toKey));
		if (registrar != null) {
			registrar.unregister();
		}
	}

	@Override
	public void bnaModelChanged(BNAModelEvent evt) {
		ThingEvent thingEvent = evt.getThingEvent();
		if (thingEvent != null) {
			for (Mirror<?, ?> mirror : FastLongMap.getList(mirrors, thingEvent.getThingKeyUID(), false)) {
				mirror.apply(evt.getSource());
			}
		}
	}
}
