package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;

public interface IHasSelected extends IThing {
	public static final String SELECTED_PROPERTY_NAME = "selected";

	public boolean isSelected();
}
