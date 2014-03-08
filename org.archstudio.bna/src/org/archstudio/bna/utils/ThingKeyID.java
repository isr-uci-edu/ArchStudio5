package org.archstudio.bna.utils;

import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;

public final class ThingKeyID<V> {

	private final Object thingID;
	private final IThingKey<V> key;

	public static <V> ThingKeyID<V> create(IThing thing, IThingKey<V> key) {
		return new ThingKeyID<V>(thing.getID(), key);
	}

	protected ThingKeyID(Object thingID, IThingKey<V> key) {
		this.thingID = thingID;
		this.key = key;
	}

	public Object getThingID() {
		return thingID;
	}

	public IThingKey<V> getKey() {
		return key;
	}

	@Override
	public String toString() {
		return thingID + "." + key;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (key == null ? 0 : key.hashCode());
		result = prime * result + (thingID == null ? 0 : thingID.hashCode());
		return result;
	}

	@SuppressWarnings("rawtypes")
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
		ThingKeyID other = (ThingKeyID) obj;
		if (key == null) {
			if (other.key != null) {
				return false;
			}
		}
		else if (!key.equals(other.key)) {
			return false;
		}
		if (thingID == null) {
			if (other.thingID != null) {
				return false;
			}
		}
		else if (!thingID.equals(other.thingID)) {
			return false;
		}
		return true;
	}

}