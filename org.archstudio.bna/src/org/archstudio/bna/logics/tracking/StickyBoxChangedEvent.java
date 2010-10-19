package org.archstudio.bna.logics.tracking;

import org.eclipse.swt.graphics.Rectangle;

import org.archstudio.bna.IThing;

public class StickyBoxChangedEvent {

	protected StickyBoxTrackingLogic source;
	protected IThing targetThing;
	protected Rectangle oldStickyBox;
	protected Rectangle newStickyBox;

	public StickyBoxChangedEvent(StickyBoxTrackingLogic source, IThing targetThing, Rectangle oldStickyBox, Rectangle newStickyBox) {
		this.source = source;
		this.targetThing = targetThing;
		this.oldStickyBox = oldStickyBox;
		this.newStickyBox = newStickyBox;
	}

	public StickyBoxTrackingLogic getSource() {
		return source;
	}

	public IThing getTargetThing() {
		return targetThing;
	}

	public Rectangle getOldStickyBox() {
		return oldStickyBox;
	}

	public Rectangle getNewStickyBox() {
		return newStickyBox;
	}
}
