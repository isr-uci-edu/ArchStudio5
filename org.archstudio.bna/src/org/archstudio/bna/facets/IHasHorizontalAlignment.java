package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.swtutils.constants.HorizontalAlignment;

public interface IHasHorizontalAlignment extends IThing {
	public static final String HORIZONTAL_ALIGNMENT_PROPERTY_NAME = "horizontalAlignment";

	public HorizontalAlignment getHorizontalAlignment();
}
