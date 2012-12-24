package org.archstudio.sysutils;

public abstract class AbstractGenericKey<D, V> implements TypedMap.Key<V> {

	private final int keyDataHashCode;
	private final D keyData;

	public AbstractGenericKey(D keyData) {
		this.keyData = keyData;
		this.keyDataHashCode = keyData == null ? 0 : keyData.hashCode();
	}

	public D getKeyData() {
		return keyData;
	}

	public final int hashCode() {
		return keyDataHashCode;
	}

	@SuppressWarnings("rawtypes")
	public final boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		AbstractGenericKey other = (AbstractGenericKey) obj;
		if (keyData == null) {
			if (other.keyData != null) {
				return false;
			}
		}
		else if (!keyData.equals(other.keyData)) {
			return false;
		}
		return true;
	}

	public final String toString() {
		return "Key[id=" + keyData + "]";
	}
}
