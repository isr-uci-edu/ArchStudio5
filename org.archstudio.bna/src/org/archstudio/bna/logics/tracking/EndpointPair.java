package org.archstudio.bna.logics.tracking;

import org.eclipse.swt.graphics.Point;

class EndpointPair {
	public Point endpoint1;
	public Point endpoint2;

	public EndpointPair(Point endpoint1, Point endpoint2) {
		this.endpoint1 = endpoint1;
		this.endpoint2 = endpoint2;
	}

	public Point getEndpoint1() {
		return endpoint1;
	}

	public Point getEndpoint2() {
		return endpoint2;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((endpoint1 == null) ? 0 : endpoint1.hashCode());
		result = PRIME * result + ((endpoint2 == null) ? 0 : endpoint2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final EndpointPair other = (EndpointPair) obj;
		if (endpoint1 == null) {
			if (other.endpoint1 != null)
				return false;
		}
		else if (!endpoint1.equals(other.endpoint1))
			return false;
		if (endpoint2 == null) {
			if (other.endpoint2 != null)
				return false;
		}
		else if (!endpoint2.equals(other.endpoint2))
			return false;
		return true;
	}

}
