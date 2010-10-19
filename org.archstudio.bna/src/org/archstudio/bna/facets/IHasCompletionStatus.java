package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.constants.CompletionStatus;

public interface IHasCompletionStatus extends IThing {
	public static final String COMPLETION_STATUS_PROPERTY_NAME = "#completionStatus";

	public CompletionStatus getCompletionStatus();
}
