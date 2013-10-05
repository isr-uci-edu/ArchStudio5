package org.archstudio.bna.logics.tracking;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Collection;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IEntry;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.keys.IThingRefKey;
import org.archstudio.bna.logics.AbstractThingLogic;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

public class ThingReferenceTrackingLogic extends AbstractThingLogic implements IBNAModelListener {

	public static final class Reference {
		private final Object fromThingID;
		private final IThingRefKey<?> fromKey;

		public Reference(Object fromThingID, IThingRefKey<?> fromKey) {
			this.fromThingID = fromThingID;
			this.fromKey = fromKey;
		}

		public Object getFromThingID() {
			return fromThingID;
		}

		public IThingRefKey<?> getFromKey() {
			return fromKey;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (fromKey == null ? 0 : fromKey.hashCode());
			result = prime * result + (fromThingID == null ? 0 : fromThingID.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			Reference other = (Reference) obj;
			if (fromKey == null) {
				if (other.fromKey != null) {
					return false;
				}
			}
			else if (!fromKey.equals(other.fromKey)) {
				return false;
			}
			if (fromThingID == null) {
				if (other.fromThingID != null) {
					return false;
				}
			}
			else if (!fromThingID.equals(other.fromThingID)) {
				return false;
			}
			return true;
		}
	}

	Multimap<Object, Reference> idToReferences = ArrayListMultimap.create();

	public ThingReferenceTrackingLogic(IBNAWorld world) {
		super(world);
		for (IThing t : model.getAllThings()) {
			scanForReferences(t);
		}
	}

	@SuppressWarnings("rawtypes")
	private void scanForReferences(IThing fromThing) {
		for (IEntry entry : fromThing.entries()) {
			if (entry.getKey() instanceof IThingRefKey) {
				addReference(fromThing, (IThingRefKey) entry.getKey(), entry.getValue());
			}
		}
	}

	@SuppressWarnings("rawtypes")
	private void removeReferences(IThing fromThing) {
		for (IEntry entry : fromThing.entries()) {
			if (entry.getKey() instanceof IThingRefKey) {
				removeReference(fromThing, (IThingRefKey) entry.getKey(), entry.getValue());
			}
		}
	}

	private synchronized void addReference(IThing fromThing, IThingRefKey<?> fromKey, Object toThingID) {
		idToReferences.put(toThingID, new Reference(fromThing.getID(), fromKey));
	}

	private synchronized void removeReference(IThing fromThing, IThingRefKey<?> fromKey, Object toThingID) {
		idToReferences.remove(toThingID, new Reference(fromThing.getID(), fromKey));
	}

	public synchronized Collection<Reference> getReferences(Object toThingID) {
		checkArgument(!(toThingID instanceof IThing), "Expected a thing's ID, not the thing itself: %s", toThingID);

		return Lists.newArrayList(idToReferences.get(toThingID));
	}

	@Override
	@SuppressWarnings("rawtypes")
	synchronized public void bnaModelChanged(BNAModelEvent evt) {
		switch (evt.getEventType()) {
		case THING_ADDED:
			scanForReferences(evt.getTargetThing());
			break;
		case THING_REMOVED:
			removeReferences(evt.getTargetThing());
			break;
		case THING_CHANGED:
			ThingEvent thingEvent = evt.getThingEvent();
			IThingKey key = thingEvent.getPropertyName();
			if (key instanceof IThingRefKey) {
				removeReference(evt.getTargetThing(), (IThingRefKey) key, thingEvent.getOldPropertyValue());
				addReference(evt.getTargetThing(), (IThingRefKey) key, thingEvent.getNewPropertyValue());
			}
		default:
			// do nothing
		}
	}
}
