package org.archstudio.bna;

import org.archstudio.bna.utils.DefaultBNAModel;
import org.eclipse.jdt.annotation.Nullable;

public class BNAModelEvent {

	public enum EventType {
		THING_ADDED(true), //
		THING_REMOVED(true), //
		THING_CHANGED(true), //
		THING_RESTACKED(true), //
		BULK_CHANGE_BEGIN(false), //
		BULK_CHANGE_END(false), //
		STREAM_NOTIFICATION_EVENT(false);

		private final boolean modelModified;

		public boolean isModelModifyingEvent() {
			return modelModified;
		}

		private EventType(boolean modelModified) {
			this.modelModified = modelModified;
		}
	};

	public static final BNAModelEvent create(DefaultBNAModel source, EventType eventType, boolean inBulkChange) {
		return new BNAModelEvent(source, eventType, inBulkChange, null, null, null);
	}

	public static final BNAModelEvent create(IBNAModel source, EventType eventType, boolean inBulkChange,
			String streamNotification) {
		return new BNAModelEvent(source, eventType, inBulkChange, null, null, streamNotification);
	}

	public static final BNAModelEvent create(IBNAModel source, EventType eventType, boolean inBulkChange,
			IThing targetThing) {
		return new BNAModelEvent(source, eventType, inBulkChange, targetThing, null, null);
	}

	public static final BNAModelEvent create(IBNAModel source, EventType eventType, boolean inBulkChange,
			IThing targetThing, ThingEvent thingEvent) {
		return new BNAModelEvent(source, eventType, inBulkChange, targetThing, thingEvent, null);
	}

	protected final IBNAModel source;
	protected final EventType eventType;
	protected final boolean inBulkChange;
	protected final IThing targetThing;
	protected final ThingEvent thingEvent;
	protected final String streamNotification;

	protected BNAModelEvent(IBNAModel source, EventType eventType, boolean inBulkChange, @Nullable IThing targetThing,
			@Nullable ThingEvent thingEvent, @Nullable String streamNotification) {
		this.source = source;
		this.eventType = eventType;
		this.inBulkChange = inBulkChange;
		this.targetThing = targetThing;
		this.thingEvent = thingEvent;
		this.streamNotification = streamNotification;
	}

	public IBNAModel getSource() {
		return source;
	}

	public EventType getEventType() {
		return eventType;
	}

	public boolean isInBulkChange() {
		return inBulkChange;
	}

	public @Nullable
	IThing getTargetThing() {
		return targetThing;
	}

	public @Nullable
	ThingEvent getThingEvent() {
		return thingEvent;
	}

	public @Nullable
	String getStreamNotification() {
		return streamNotification;
	}

	@Override
	public String toString() {
		return "BNAModelEvent[" + //
				"eventType=" + eventType + ";" + //
				"inBulkChange=" + inBulkChange + ";" + //
				"streamNotification=" + streamNotification + ";" + //
				"thingEvent=" + thingEvent + ";" + //
				"source=" + source + ";" + //
				"targetThing=" + targetThing + ";" + //
				"]";
	}
}
