package org.archstudio.bna;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IThingListener {

	public void thingChanged(ThingEvent thingEvent);

}
