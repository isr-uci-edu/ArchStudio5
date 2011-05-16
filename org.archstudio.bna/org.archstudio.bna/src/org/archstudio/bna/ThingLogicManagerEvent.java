package org.archstudio.bna;

public class ThingLogicManagerEvent {

	public enum EventType {
		LOGIC_ADDED, LOGIC_REMOVING, LOGIC_REMOVED
	}

	private final EventType eventType;
	private final IThingLogic logic;

	public ThingLogicManagerEvent(EventType eventType, IThingLogic logic) {
		this.eventType = eventType;
		this.logic = logic;
	}

	public EventType getEventType() {
		return eventType;
	}

	public IThingLogic getLogic() {
		return logic;
	}
}
