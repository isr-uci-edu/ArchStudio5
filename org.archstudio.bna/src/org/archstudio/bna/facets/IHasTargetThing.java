package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;

public interface IHasTargetThing extends IThing {
	public static final String TARGET_THING_ID_PROPERTY_NAME = "&targetThingID";

	public String getTargetThingID();
}
