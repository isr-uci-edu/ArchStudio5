package org.archstudio.bna.constants;

public enum StickyMode {
	EDGE_FROM_CENTER(true, true), //
	EDGE(false, false), //
	CENTER(false, true);

	boolean dependsOnSecondaryPoint;
	boolean isStuckToCenterPoint;

	private StickyMode(boolean dependsOnSecondaryPoint, boolean isStuckToCenterPoint) {
		this.dependsOnSecondaryPoint = dependsOnSecondaryPoint;
		this.isStuckToCenterPoint = isStuckToCenterPoint;
	}

	public boolean isDependsOnSecondaryPoint() {
		return dependsOnSecondaryPoint;
	}
}