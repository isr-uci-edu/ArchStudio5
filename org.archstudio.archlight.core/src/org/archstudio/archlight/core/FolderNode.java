package org.archstudio.archlight.core;

import org.archstudio.sysutils.ArrayUtils;

class FolderNode {
	protected String[] pathSegments = null;

	public FolderNode(String[] pathSegments) {
		this.pathSegments = pathSegments;
	}

	public String[] getPathSegments() {
		return pathSegments;
	}

	public String getLastSegment() {
		return pathSegments[pathSegments.length - 1];
	}

	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj != null && obj.getClass().equals(this.getClass())) {
			return ArrayUtils.equals(((FolderNode) obj).pathSegments, pathSegments);
		}
		return false;
	}

	public int hashCode() {
		return ArrayUtils.arrayHashCode(pathSegments);
	}
}
