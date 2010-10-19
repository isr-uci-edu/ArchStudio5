package org.archstudio.bna.logics.tracking;

import org.archstudio.bna.IThing;

public class ThingRefChangedEvent {

	protected ThingRefTrackingLogic source;
	protected IThing targetThing;
	protected String propertyName;
	protected String oldValue;
	protected String newValue;

	public ThingRefChangedEvent(ThingRefTrackingLogic source, IThing targetThing, String propertyName, String oldValue, String newValue) {
		this.source = source;
		this.targetThing = targetThing;
		this.propertyName = propertyName;
		this.oldValue = oldValue;
		this.newValue = newValue;
	}

	public String getNewValue() {
		return newValue;
	}

	public String getOldValue() {
		return oldValue;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public ThingRefTrackingLogic getSource() {
		return source;
	}

	public IThing getTargetThing() {
		return targetThing;
	}
}
