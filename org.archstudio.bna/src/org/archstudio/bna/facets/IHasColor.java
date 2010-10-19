package org.archstudio.bna.facets;

import org.eclipse.swt.graphics.RGB;

import org.archstudio.bna.IThing;

public interface IHasColor extends IThing {
	public static final String COLOR_PROPERTY_NAME = "color";

	public RGB getColor();
}
