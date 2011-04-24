package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;

public interface IHasUserEditable extends IThing {
	public static final String USER_EDITABLE_PROPERTY_NAME = "userEditable";

	public boolean isUserEditable();
}
