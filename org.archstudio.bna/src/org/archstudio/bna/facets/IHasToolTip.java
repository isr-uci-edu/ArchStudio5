package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;

public interface IHasToolTip extends IThing {

	public static final String USER_MAY_EDIT_TOOL_TIP = "userMayEditToolTip";

	public static final IThingKey<String> TOOL_TIP_KEY = ThingKey.create("toolTip");

}
