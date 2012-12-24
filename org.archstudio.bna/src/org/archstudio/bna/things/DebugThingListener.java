package org.archstudio.bna.things;

import org.archstudio.bna.IThingListener;
import org.archstudio.bna.ThingEvent;

public class DebugThingListener implements IThingListener {

	@Override
	public void thingChanged(ThingEvent thingEvent) {
		System.err.println(thingEvent);
	}

}
