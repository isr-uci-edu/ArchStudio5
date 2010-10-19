package org.archstudio.bna.logics.tracking;

import org.archstudio.bna.IThing;

public class SelectionChangedEvent {

	public enum EventType {
		THING_SELECTED, THING_DESELECTED
	}

	protected SelectionTrackingLogic source;
	protected EventType eventType;
	protected IThing targetThing;

	public SelectionChangedEvent(SelectionTrackingLogic source, EventType eventType, IThing targetThing) {
		this.source = source;
		this.eventType = eventType;
		this.targetThing = targetThing;
	}

	public SelectionTrackingLogic getSource() {
		return source;
	}

	public EventType getEventType() {
		return eventType;
	}

	public IThing getTargetThing() {
		return targetThing;
	}

}
