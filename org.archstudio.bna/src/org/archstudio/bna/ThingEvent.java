package org.archstudio.bna;

import org.archstudio.bna.IThing.IThingKey;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

@NonNullByDefault
public class ThingEvent {

	public enum EventType {
		PROPERTY_SET, PROPERTY_REMOVED
	}

	protected EventType eventType;
	protected IThing targetThing;
	protected IThingKey<?> propertyName;
	protected Object oldPropertyValue;
	protected Object newPropertyValue;

	public static ThingEvent create(EventType eventType, IThing targetThing, IThingKey<?> propertyName,
			@Nullable Object oldPropertyValue, @Nullable Object newPropertyValue) {
		return new ThingEvent(eventType, targetThing, propertyName, oldPropertyValue, newPropertyValue);
	}

	protected ThingEvent(EventType eventType, IThing targetThing, IThingKey<?> propertyName,
			@Nullable Object oldPropertyValue, @Nullable Object newPropertyValue) {
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

	public IThingKey<?> getPropertyName() {
		return propertyName;
	}

	public @Nullable
	Object getOldPropertyValue() {
		return oldPropertyValue;
	}

	public @Nullable
	Object getNewPropertyValue() {
		return newPropertyValue;
	}

	public String toString() {
		return "ThingEvent{thingID=" + targetThing.getID() + "; eventType=" + eventType + "; propertyName="
				+ propertyName + "; oldPropertyValue=" + oldPropertyValue + "; newPropertyValue=" + newPropertyValue
				+ "; targetThing=" + targetThing + "};";
	}
}
