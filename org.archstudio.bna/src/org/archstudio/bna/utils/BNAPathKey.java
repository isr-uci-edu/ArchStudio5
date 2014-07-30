package org.archstudio.bna.utils;

import org.archstudio.bna.keys.IThingKey;

public class BNAPathKey {

	public static BNAPathKey create(BNAPath path, IThingKey<?> key) {
		return new BNAPathKey(path, key);
	}

	private final BNAPath path;
	private final IThingKey<?> key;

	private BNAPathKey(BNAPath path, IThingKey<?> key) {
		this.path = path;
		this.key = key;
	}

	public BNAPath getPath() {
		return path;
	}

	public IThingKey<?> getKey() {
		return key;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (key == null ? 0 : key.hashCode());
		result = prime * result + (path == null ? 0 : path.hashCode());
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
		BNAPathKey other = (BNAPathKey) obj;
		if (key == null) {
			if (other.key != null) {
				return false;
			}
		}
		else if (!key.equals(other.key)) {
			return false;
		}
		if (path == null) {
			if (other.path != null) {
				return false;
			}
		}
		else if (!path.equals(other.path)) {
			return false;
		}
		return true;
	}

}
