package org.archstudio.bna;

import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.utils.DefaultBNAModel;

public class BNAModelEvent<T extends IThing, K extends IThingKey<V>, V> {

	public enum EventType {
		THING_ADDED(true), //
		THING_REMOVING(false), //
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

	public static final BNAModelEvent<IThing, IThingKey<Object>, Object> create(DefaultBNAModel source,
			EventType eventType, boolean inBulkChange) {
		return new BNAModelEvent<IThing, IThingKey<Object>, Object>(source, eventType, inBulkChange, null, null, null);
	}

	public static final BNAModelEvent<IThing, IThingKey<Object>, Object> create(IBNAModel source, EventType eventType,
			boolean inBulkChange, String streamNotification) {
		return new BNAModelEvent<IThing, IThingKey<Object>, Object>(source, eventType, inBulkChange, null, null,
				streamNotification);
	}

	public static final <T extends IThing> BNAModelEvent<T, IThingKey<Object>, Object> create(IBNAModel source,
			EventType eventType, boolean inBulkChange, T targetThing) {
		return new BNAModelEvent<T, IThingKey<Object>, Object>(source, eventType, inBulkChange, targetThing, null, null);
	}

	public static final <T extends IThing, K extends IThingKey<V>, V> BNAModelEvent<T, K, V> create(IBNAModel source,
			EventType eventType, boolean inBulkChange, T targetThing, ThingEvent<T, K, V> thingEvent) {
		return new BNAModelEvent<T, K, V>(source, eventType, inBulkChange, targetThing, thingEvent, null);
	}

	protected final IBNAModel source;
	protected final EventType eventType;
	protected final boolean inBulkChange;
	protected final T targetThing;
	protected final ThingEvent<T, K, V> thingEvent;
	protected final String streamNotification;

	protected BNAModelEvent(IBNAModel source, EventType eventType, boolean inBulkChange, T targetThing,
			ThingEvent<T, K, V> thingEvent, String streamNotification) {
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

	public T getTargetThing() {
		return targetThing;
	}

	public ThingEvent<T, K, V> getThingEvent() {
		return thingEvent;
	}

	public String getStreamNotification() {
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
