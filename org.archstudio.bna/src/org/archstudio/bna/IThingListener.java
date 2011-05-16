package org.archstudio.bna;

import org.archstudio.bna.IThing.IThingKey;

public interface IThingListener {

	public <ET extends IThing, EK extends IThingKey<EV>, EV> void thingChanged(ThingEvent<ET, EK, EV> thingEvent);

}
