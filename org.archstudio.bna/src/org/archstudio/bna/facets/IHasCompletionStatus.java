package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.constants.CompletionStatus;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;

public interface IHasCompletionStatus extends IThing {
	public static final IThingKey<CompletionStatus> COMPLETION_STATUS_KEY = ThingKey.create("completionStatus");

	public CompletionStatus getCompletionStatus();
}
