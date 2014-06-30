package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.swtutils.constants.Flow;

public interface IHasFlow extends IThing {
	public static final IThingKey<Flow> FLOW_KEY = ThingKey.create("flow");

	public Flow getFlow();
}
