package org.archstudio.bna.logics.tracking;

import org.eclipse.swt.graphics.Point;

import org.archstudio.bna.*;

public class AnchorPointChangedEvent {

	protected AnchorPointTrackingLogic source;
	protected IThing targetThing;
	protected Point oldAnchorPoint;
	protected Point newAnchorPoint;

	public AnchorPointChangedEvent(AnchorPointTrackingLogic source, IThing targetThing, Point oldAnchorPoint, Point newAnchorPoint) {
		this.source = source;
		this.targetThing = targetThing;
		this.oldAnchorPoint = oldAnchorPoint;
		this.newAnchorPoint = newAnchorPoint;
	}

	public AnchorPointTrackingLogic getSource() {
		return source;
	}

	public IThing getTargetThing() {
		return targetThing;
	}

	public Point getOldAnchorPoint() {
		return oldAnchorPoint;
	}

	public Point getNewAnchorPoint() {
		return newAnchorPoint;
	}
}
