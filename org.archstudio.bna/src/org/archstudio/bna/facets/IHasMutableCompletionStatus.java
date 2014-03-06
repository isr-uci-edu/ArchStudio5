package org.archstudio.bna.facets;

import org.archstudio.bna.constants.CompletionStatus;

public interface IHasMutableCompletionStatus extends IHasCompletionStatus {
	public void setCompletionStatus(CompletionStatus completionStatus);
}
