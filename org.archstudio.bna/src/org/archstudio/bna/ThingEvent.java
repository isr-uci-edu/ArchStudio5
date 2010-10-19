package org.archstudio.bna;

public class ThingEvent implements java.io.Serializable {

	public enum EventType {
		PROPERTY_SET, PROPERTY_REMOVED
	}

	protected EventType eventType;
	protected IThing targetThing;
	protected String propertyName;
	protected Object oldPropertyValue;
	protected Object newPropertyValue;

	public ThingEvent(EventType eventType, IThing targetThing, String propertyName, Object oldPropertyValue, Object newPropertyValue) {
		this.eventType = eventType;
		this.targetThing = targetThing;
		this.propertyName = propertyName;
		this.oldPropertyValue = oldPropertyValue;
		this.newPropertyValue = newPropertyValue;
	}

	public EventType getEventType() {
		return eventType;
	}

	public IThing getTargetThing() {
		return targetThing;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public Object getOldPropertyValue() {
		return oldPropertyValue;
	}

	public Object getNewPropertyValue() {
		return newPropertyValue;
	}

	public String toString() {
		return "ThingEvent{eventType=" + eventType + "; targetThing=" + targetThing + "; propertyName=" + propertyName + "; oldPropertyValue="
		        + oldPropertyValue + "; newPropertyValue=" + newPropertyValue + "};";
	}

}
