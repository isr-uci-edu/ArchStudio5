package org.archstudio.bna;

import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.utils.ThingKeyID;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

@NonNullByDefault
public class ThingEvent {

	public enum EventType {
		PROPERTY_SET, PROPERTY_REMOVED
	}

	private final EventType eventType;
	private final IThingKey<?> propertyName;
	private final Object oldPropertyValue;
	private final Object newPropertyValue;
	private final IThing targetThing;
	private final ThingKeyID<?> thingKeyID;

	public static ThingEvent create(EventType eventType, IThing targetThing, IThingKey<?> propertyName,
			@Nullable Object oldPropertyValue, @Nullable Object newPropertyValue) {
		return new ThingEvent(eventType, targetThing, propertyName, oldPropertyValue, newPropertyValue);
	}

	private ThingEvent(EventType eventType, IThing targetThing, IThingKey<?> propertyName,
			@Nullable Object oldPropertyValue, @Nullable Object newPropertyValue) {
		this.eventType = eventType;
		this.targetThing = targetThing;
		this.propertyName = propertyName;
		this.oldPropertyValue = oldPropertyValue;
		this.newPropertyValue = newPropertyValue;
		this.thingKeyID = ThingKeyID.create(targetThing, propertyName);
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

	public ThingKeyID<?> getThingKeyID() {
		return thingKeyID;
	}

	@Override
	public String toString() {
		return "ThingEvent [eventType=" + eventType + ", propertyName=" + propertyName + ", newPropertyValue="
				+ newPropertyValue + ", oldPropertyValue=" + oldPropertyValue + ", targetThing=" + targetThing + "]";
	}

}
