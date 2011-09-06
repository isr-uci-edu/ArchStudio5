package org.archstudio.bna.things;

import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingListener;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.IThing.IThingKey;

public class DebugThingListener implements IThingListener {

	@Override
	public <ET extends IThing, EK extends IThingKey<EV>, EV> void thingChanged(ThingEvent<ET, EK, EV> thingEvent) {
		System.err.println(thingEvent);
	}

}
