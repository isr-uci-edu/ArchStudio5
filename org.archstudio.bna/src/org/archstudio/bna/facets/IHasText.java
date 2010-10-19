package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;

public interface IHasText extends IThing {

	public static final String TEXT_PROPERTY_NAME = "text";

	public String getText();
}
