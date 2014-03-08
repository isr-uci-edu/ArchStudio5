package org.archstudio.bna;

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

		private EventType(boolean modelModified) {
			this.modelModified = modelModified;
		}

		public boolean isModelModifyingEvent() {
			return modelModified;
		}
	};

	public static final BNAModelEvent create(IBNAModel source, EventType eventType) {
		return new BNAModelEvent(source, eventType, null, null, null);
	}

	public static final BNAModelEvent create(IBNAModel source, EventType eventType, Object streamNotification) {
		return new BNAModelEvent(source, eventType, null, null, streamNotification);
	}

	public static final BNAModelEvent create(IBNAModel source, EventType eventType, IThing targetThing) {
		return new BNAModelEvent(source, eventType, targetThing, null, null);
	}

	public static final BNAModelEvent create(IBNAModel source, EventType eventType, ThingEvent thingEvent) {
		return new BNAModelEvent(source, eventType, thingEvent.getTargetThing(), thingEvent, null);
	}

	protected final EventType eventType;
	protected final ThingEvent thingEvent;
	protected final IThing targetThing;
	protected final Object streamNotification;
	protected final IBNAModel source;
	protected final Thread thread = Thread.currentThread();

	protected BNAModelEvent(IBNAModel source, EventType eventType, @Nullable IThing targetThing,
			@Nullable ThingEvent thingEvent, @Nullable Object streamNotification) {
		this.source = source;
		this.eventType = eventType;
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

	public @Nullable
	IThing getTargetThing() {
		return targetThing;
	}

	public @Nullable
	ThingEvent getThingEvent() {
		return thingEvent;
	}

	public @Nullable
	Object getStreamNotification() {
		return streamNotification;
	}

	public Thread getThread() {
		return thread;
	}

	@Override
	public String toString() {
		return "BNAModelEvent [eventType=" + eventType + ", thingEvent=" + thingEvent + ", targetThing=" + targetThing
				+ ", streamNotification=" + streamNotification + ", source=" + source + "]";
	}
}
