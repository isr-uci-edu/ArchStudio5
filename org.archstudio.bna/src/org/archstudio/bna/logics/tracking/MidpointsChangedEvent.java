package org.archstudio.bna.logics.tracking;

import org.eclipse.swt.graphics.Point;

import org.archstudio.bna.IThing;

public class MidpointsChangedEvent {

	protected MidpointsTrackingLogic source;
	protected IThing targetThing;
	protected Point[] oldMidpoints;
	protected Point[] newMidpoints;

	public MidpointsChangedEvent(MidpointsTrackingLogic source, IThing targetThing, Point[] oldMidpoints, Point[] newMidpoints) {
		this.source = source;
		this.targetThing = targetThing;
		this.oldMidpoints = oldMidpoints;
		this.newMidpoints = newMidpoints;
	}

	public MidpointsTrackingLogic getSource() {
		return source;
	}

	public IThing getTargetThing() {
		return targetThing;
	}

	public Point[] getOldMidpoints() {
		return oldMidpoints;
	}

	public Point[] getNewMidpoints() {
		return newMidpoints;
	}
}
