package org.archstudio.bna;

public class BNAModelEvent implements java.io.Serializable {

	public enum EventType {
		THING_ADDED, THING_REMOVING, THING_REMOVED, THING_CHANGED, BULK_CHANGE_BEGIN, BULK_CHANGE_END, STREAM_NOTIFICATION_EVENT
	};

	protected IBNAModel source;
	protected EventType eventType;
	protected IThing targetThing;
	protected ThingEvent thingEvent;
	protected String streamNotification;

	public BNAModelEvent(IBNAModel source, String streamNotification) {
		this.source = source;
		this.eventType = EventType.STREAM_NOTIFICATION_EVENT;
		this.streamNotification = streamNotification;
	}

	public BNAModelEvent(IBNAModel source, EventType eventType, IThing targetThing) {
		this.source = source;
		this.eventType = eventType;
		this.targetThing = targetThing;
		this.thingEvent = null;
	}

	public BNAModelEvent(IBNAModel source, EventType eventType, IThing targetThing, ThingEvent thingEvent) {
		this.source = source;
		this.eventType = eventType;
		this.targetThing = targetThing;
		this.thingEvent = thingEvent;
	}

	public String getStreamNotification() {
		return streamNotification;
	}

	public void setSource(IBNAModel source) {
		this.source = source;
	}

	public IBNAModel getSource() {
		return source;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public void setTargetThing(IThing targetThing) {
		this.targetThing = targetThing;
	}

	public void setThingEvent(ThingEvent thingEvent) {
		this.thingEvent = thingEvent;
	}

	public EventType getEventType() {
		return eventType;
	}

	public IThing getTargetThing() {
		return targetThing;
	}

	public ThingEvent getThingEvent() {
		return thingEvent;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer("BNAModelEvent[");
		sb.append("eventType=");
		switch (eventType) {
		case THING_ADDED:
			sb.append("THING_ADDED");
			break;
		case THING_CHANGED:
			sb.append("THING_CHANGED");
			break;
		case THING_REMOVED:
			sb.append("THING_REMOVED");
			break;
		case BULK_CHANGE_BEGIN:
			sb.append("BULK_CHANGE_BEGIN");
			break;
		case BULK_CHANGE_END:
			sb.append("BULK_CHANGE_END");
			break;
		case STREAM_NOTIFICATION_EVENT:
			sb.append("STREAM_NOTIFICATION_EVENT");
			break;
		case THING_REMOVING:
			sb.append("THING_REMOVING");
			break;
		default:
			sb.append(eventType);
		}
		sb.append(";");
		sb.append("source=");
		sb.append(source);
		sb.append(";");
		sb.append("targetThing=");
		sb.append(targetThing);
		sb.append(";");
		sb.append("thingEvent=");
		sb.append(thingEvent);
		sb.append(";");
		sb.append("streamNotification=");
		sb.append(streamNotification);
		sb.append("]");
		return sb.toString();
	}
}
