package org.archstudio.bna.facets;

import org.eclipse.swt.graphics.RGB;

import org.archstudio.bna.IThing;

public interface IHasSecondaryColor extends IThing {
	public static final String SECONDARY_COLOR_PROPERTY_NAME = "secondaryColor";

	public RGB getSecondaryColor();
}
