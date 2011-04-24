package org.archstudio.bna.logics.tracking;

import org.eclipse.swt.graphics.Point;

import org.archstudio.bna.IThing;

public class EndpointsChangedEvent {

	protected EndpointsTrackingLogic source;
	protected IThing targetThing;

	protected Point oldEndpoint1;
	protected Point newEndpoint1;
	protected Point oldEndpoint2;
	protected Point newEndpoint2;

	public EndpointsChangedEvent(EndpointsTrackingLogic source, IThing targetThing, Point oldEndpoint1, Point newEndpoint1, Point oldEndpoint2,
	        Point newEndpoint2) {
		this.source = source;
		this.targetThing = targetThing;
		this.oldEndpoint1 = oldEndpoint1;
		this.newEndpoint1 = newEndpoint1;
		this.oldEndpoint2 = oldEndpoint2;
		this.newEndpoint2 = newEndpoint2;
	}

	public EndpointsTrackingLogic getSource() {
		return source;
	}

	public IThing getTargetThing() {
		return targetThing;
	}

	public Point getNewEndpoint1() {
		return newEndpoint1;
	}

	public Point getNewEndpoint2() {
		return newEndpoint2;
	}

	public Point getOldEndpoint1() {
		return oldEndpoint1;
	}

	public Point getOldEndpoint2() {
		return oldEndpoint2;
	}

}
