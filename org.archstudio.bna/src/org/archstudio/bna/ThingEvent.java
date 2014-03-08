package org.archstudio.bna;

import org.archstudio.bna.IThing.IThingKey;
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
	private Object newPropertyValue;
	private final ThingKeyID<?> thingKeyID;
	private final IThing targetThing;

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

	public ThingKeyID<?> getThingKeyID() {
		return thingKeyID;
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

	public void setNewPropertyValue(@Nullable Object newPropertyValue) {
		this.newPropertyValue = newPropertyValue;
	}

	@Override
	public String toString() {
		return "ThingEvent [eventType=" + eventType + ", propertyName=" + propertyName + ", oldPropertyValue="
				+ oldPropertyValue + ", newPropertyValue=" + newPropertyValue + ", thingKeyID=" + thingKeyID
				+ ", targetThing=" + targetThing + "]";
	}

}
