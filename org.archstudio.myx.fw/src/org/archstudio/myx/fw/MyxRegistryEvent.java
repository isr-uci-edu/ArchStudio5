package org.archstudio.myx.fw;

public class MyxRegistryEvent {

	public enum EventType {
		BRICK_REGISTERED, BRICK_UNREGISTERED, OBJECT_REGISTERED, OBJECT_UNREGISTERED
	}

	private EventType eventType;
	private IMyxBrick brick;
	private Object object;

	public MyxRegistryEvent(EventType eventType, IMyxBrick brick, Object object) {
		this.eventType = eventType;
		this.brick = brick;
		this.object = object;
	}

	public EventType getEventType() {
		return eventType;
	}

	public IMyxBrick getBrick() {
		return brick;
	}

	public Object getObject() {
		return object;
	}

	@Override
	public String toString() {
		return "" + eventType + ": Brick:" + brick.getMyxBrickItems().getBrickName() + " Object:" + object;
	}
}
