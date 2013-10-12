package org.archstudio.bna;

import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.utils.BNAUtils;
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
	private final int modCount;
	private final long thingKeyUID;
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
		this.thingKeyUID = BNAUtils.getThingKeyUID(targetThing, propertyName);
		this.modCount = targetThing.getModCount();
	}

	public long getThingKeyUID() {
		return thingKeyUID;
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

	public int getModCount() {
		return modCount;
	}

	@Override
	public String toString() {
		return "ThingEvent [eventType=" + eventType + ", propertyName=" + propertyName + ", oldPropertyValue="
				+ oldPropertyValue + ", newPropertyValue=" + newPropertyValue + ", modCount=" + modCount
				+ ", thingKeyUID=" + thingKeyUID + ", targetThing=" + targetThing + "]";
	}

}
