package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;

public interface IHasEditing extends IThing {
	public static final String EDITING_PROPERTY_NAME = "editing";

	public boolean isEditing();
}
