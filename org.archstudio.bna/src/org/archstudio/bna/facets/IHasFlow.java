package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.swtutils.constants.Flow;

public interface IHasFlow extends IThing {
	public static final String FLOW_PROPERTY_NAME = "flow";

	public Flow getFlow();
}
