package org.archstudio.bna;

import org.archstudio.bna.IThing.IThingKey;

public class ThingEvent<T extends IThing, K extends IThingKey<? super V>, V> {

	public enum EventType {
		PROPERTY_SET, PROPERTY_REMOVED
	}

	protected EventType eventType;
	protected T targetThing;
	protected K propertyName;
	protected V oldPropertyValue;
	protected V newPropertyValue;

	public static <T extends IThing, K extends IThingKey<? super V>, V> ThingEvent<T, K, V> create(EventType eventType,
			T targetThing, K propertyName, V oldPropertyValue, V newPropertyValue) {
		return new ThingEvent<T, K, V>(eventType, targetThing, propertyName, oldPropertyValue, newPropertyValue);
	}

	protected ThingEvent(EventType eventType, T targetThing, K propertyName, V oldPropertyValue, V newPropertyValue) {
		this.eventType = eventType;
		this.targetThing = targetThing;
		this.propertyName = propertyName;
		this.oldPropertyValue = oldPropertyValue;
		this.newPropertyValue = newPropertyValue;
	}

	public EventType getEventType() {
		return eventType;
	}

	public T getTargetThing() {
		return targetThing;
	}

	public K getPropertyName() {
		return propertyName;
	}

	public V getOldPropertyValue() {
		return oldPropertyValue;
	}

	public V getNewPropertyValue() {
		return newPropertyValue;
	}

	@Override
	public String toString() {
		return "ThingEvent{thingID=" + targetThing.getID() + "; eventType=" + eventType + "; propertyName="
				+ propertyName + "; oldPropertyValue=" + oldPropertyValue + "; newPropertyValue=" + newPropertyValue
				+ "; targetThing=" + targetThing + "};";
	}
}
