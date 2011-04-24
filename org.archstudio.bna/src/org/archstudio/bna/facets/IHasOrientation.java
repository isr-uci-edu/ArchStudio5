package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.swtutils.constants.Orientation;

public interface IHasOrientation extends IThing {
	public static final String ORIENTATION_PROPERTY_NAME = "orientation";

	public Orientation getOrientation();
}
