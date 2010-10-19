package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.swtutils.constants.VerticalAlignment;

public interface IHasVerticalAlignment extends IThing {
	public static final String VERTICAL_ALIGNMENT_PROPERTY_NAME = "verticalAlignment";

	public VerticalAlignment getVerticalAlignment();
}
