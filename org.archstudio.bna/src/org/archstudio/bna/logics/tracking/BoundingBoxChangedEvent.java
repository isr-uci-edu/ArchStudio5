package org.archstudio.bna.logics.tracking;

import org.eclipse.swt.graphics.Rectangle;

import org.archstudio.bna.IThing;

public class BoundingBoxChangedEvent {

	protected BoundingBoxTrackingLogic source;
	protected IThing targetThing;
	protected Rectangle oldBoundingBox;
	protected Rectangle newBoundingBox;

	public BoundingBoxChangedEvent(BoundingBoxTrackingLogic source, IThing targetThing, Rectangle oldBoundingBox, Rectangle newBoundingBox) {
		this.source = source;
		this.targetThing = targetThing;
		this.oldBoundingBox = oldBoundingBox;
		this.newBoundingBox = newBoundingBox;
	}

	public BoundingBoxTrackingLogic getSource() {
		return source;
	}

	public IThing getTargetThing() {
		return targetThing;
	}

	public Rectangle getOldBoundingBox() {
		return oldBoundingBox;
	}

	public Rectangle getNewBoundingBox() {
		return newBoundingBox;
	}
}
