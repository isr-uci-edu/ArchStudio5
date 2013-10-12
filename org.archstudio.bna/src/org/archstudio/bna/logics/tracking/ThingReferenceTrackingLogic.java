package org.archstudio.bna.logics.tracking;

import java.util.Collection;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IPrivilegedBNAModelListener;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IEntry;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.keys.IThingRefKey;
import org.archstudio.bna.logics.AbstractThingLogic;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

public class ThingReferenceTrackingLogic extends AbstractThingLogic implements IPrivilegedBNAModelListener {

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

	protected final Multimap<Object, Reference> idToReferences = HashMultimap.create();

	public ThingReferenceTrackingLogic(IBNAWorld world) {
		super(world);
		for (IThing t : model.getAllThings()) {
			addReferences(t);
		}
	}

	@Override
	synchronized public void dispose() {
		idToReferences.clear();
		super.dispose();
	}

	private void addReferences(IThing fromThing) {
		for (IEntry entry : fromThing.entries()) {
			if (entry.getKey() instanceof IThingRefKey) {
				IThingRefKey<?> refKey = (IThingRefKey<?>) entry.getKey();
				addReference(fromThing, refKey, entry.getValue());
			}
		}
	}

	private void removeReferences(IThing fromThing) {
		for (IEntry entry : fromThing.entries()) {
			if (entry.getKey() instanceof IThingRefKey) {
				IThingRefKey<?> refKey = (IThingRefKey<?>) entry.getKey();
				removeReference(fromThing, refKey, entry.getValue());
			}
		}
	}

	private void addReference(IThing fromThing, IThingRefKey<?> fromKey, Object toThingID) {
		idToReferences.put(toThingID, new Reference(fromThing.getID(), fromKey));
	}

	private void removeReference(IThing fromThing, IThingRefKey<?> fromKey, Object toThingID) {
		idToReferences.remove(toThingID, new Reference(fromThing.getID(), fromKey));
	}

	synchronized public Collection<Reference> getReferences(IThing toThing) {
		return getReferences(toThing.getID());
	}

	synchronized public Collection<Reference> getReferences(Object toThingID) {
		if (toThingID != null) {
			Collection<Reference> references = idToReferences.get(toThingID);
			if (references != null) {
				return Lists.newArrayList(idToReferences.get(toThingID));
			}
		}
		return Lists.newArrayList();
	}

	@Override
	@SuppressWarnings("rawtypes")
	synchronized public void privilegedBNAModelChanged(BNAModelEvent evt) {
		switch (evt.getEventType()) {
		case THING_ADDED:
			addReferences(evt.getTargetThing());
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
