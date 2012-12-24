package org.archstudio.bna.logics.coordinating;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Iterator;
import java.util.List;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.logics.AbstractThingLogic;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

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
			checkNotNull(fromThingID);
			checkNotNull(fromKey);
			checkNotNull(toThingID);
			checkNotNull(toKey);
			checkNotNull(transformFunction);

			this.fromThingID = fromThingID;
			this.fromKey = fromKey;
			this.toThingID = toThingID;
			this.toKey = toKey;
			this.transformFunction = transformFunction;
		}

		public void apply(IBNAModel model) {
			checkNotNull(model);

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

	Multimap<List<?>, Mirror<?, ?>> mirrors = ArrayListMultimap.create();

	public MirrorValueLogic() {
	}

	public <V> void mirrorValue(IThing fromThing, IThingKey<V> key, IThing toThing) {
		mirrorValue(fromThing, key, toThing, key, Functions.<V> identity());
	}

	public <V> void mirrorValue(IThing fromThing, IThingKey<V> fromKey, IThing toThing, IThingKey<V> toKey) {
		mirrorValue(fromThing, fromKey, toThing, toKey, Functions.<V> identity());
	}

	public synchronized <FV, TV> void mirrorValue(IThing fromThing, IThingKey<FV> fromKey, IThing toThing,
			IThingKey<TV> toKey, Function<FV, TV> transformFunction) {
		Mirror<FV, TV> mirror = new Mirror<FV, TV>(fromThing.getID(), fromKey, toThing.getID(), toKey,
				transformFunction);

		mirrors.put(Lists.newArrayList(fromThing.getID(), fromKey), mirror);
		mirrors.put(Lists.newArrayList(toThing.getID(), toKey), mirror);

		mirror.apply(getBNAModel());
	}

	public synchronized <FV> void unmirror(IThing toThing, IThingKey<FV> toKey) {
		Iterator<Mirror<?, ?>> i;
		while ((i = mirrors.get(Lists.newArrayList(toThing.getID(), toKey)).iterator()).hasNext()) {
			Mirror<?, ?> mirror = i.next();
			while (mirrors.values().remove(mirror)) {
			}
		}
	}

	public void bnaModelChanged(BNAModelEvent evt) {
		ThingEvent thingEvent = evt.getThingEvent();
		if (thingEvent != null) {
			for (Mirror<?, ?> mirror : mirrors.get(Lists.newArrayList(thingEvent.getTargetThing().getID(),
					thingEvent.getPropertyName()))) {
				mirror.apply(evt.getSource());
			}
		}
	}
}
