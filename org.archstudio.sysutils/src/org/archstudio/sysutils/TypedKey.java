package org.archstudio.sysutils;

public interface TypedKey<V> {

	public static class GenericTypedKey<K, V> implements TypedKey<V> {

		final Object key;

		public GenericTypedKey(Object key) {
			this.key = key;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((key == null) ? 0 : key.hashCode());
			return result;
		}

		@SuppressWarnings("rawtypes")
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			GenericTypedKey other = (GenericTypedKey) obj;
			if (key == null) {
				if (other.key != null)
					return false;
			}
			else if (!key.equals(other.key))
				return false;
			return true;
		}
	}
}
