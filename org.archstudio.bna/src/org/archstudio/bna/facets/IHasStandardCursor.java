package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;

public interface IHasStandardCursor extends IHasCursor, IThing {
	public static final String STANDARD_CURSOR_PROPERTY_NAME = "standardCursor";

	public int getStandardCursor();
}
