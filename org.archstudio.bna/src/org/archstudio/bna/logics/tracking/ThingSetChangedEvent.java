package org.archstudio.bna.logics.tracking;

import org.archstudio.bna.IThing;
import org.archstudio.bna.BNAModelEvent.EventType;

public class ThingSetChangedEvent {
	protected TypedThingSetTrackingLogic<?> source;
	protected IThing targetThing;
	protected EventType eventType;

	public ThingSetChangedEvent(TypedThingSetTrackingLogic<?> source, IThing targetThing, EventType eventType) {
		this.source = source;
		this.targetThing = targetThing;
		this.eventType = eventType;
	}

	public EventType getEventType() {
		return eventType;
	}

	public TypedThingSetTrackingLogic<?> getSource() {
		return source;
	}

	public IThing getTargetThing() {
		return targetThing;
	}
}
