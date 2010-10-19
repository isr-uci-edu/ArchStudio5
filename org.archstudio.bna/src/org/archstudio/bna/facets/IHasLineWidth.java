package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;

public interface IHasLineWidth extends IThing {

	public static final String LINE_WIDTH_PROPERTY_NAME = "lineWidth";

	public int getLineWidth();
}
