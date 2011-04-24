package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;

public interface IHasToolTip extends IThing {
	public static final String TOOL_TIP_PROPERTY_NAME = "toolTip";

	public String getToolTip();
}
