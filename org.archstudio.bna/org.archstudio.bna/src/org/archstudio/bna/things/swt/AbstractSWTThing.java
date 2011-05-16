package org.archstudio.bna.things.swt;

import org.archstudio.bna.constants.CompletionStatus;
import org.archstudio.bna.facets.IHasMutableCompletionStatus;
import org.archstudio.bna.facets.IHasMutableEditing;
import org.archstudio.bna.things.AbstractAnchorPointThing;

public class AbstractSWTThing extends AbstractAnchorPointThing implements IHasMutableCompletionStatus,
		IHasMutableEditing {

	public AbstractSWTThing(Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		setCompletionStatus(CompletionStatus.INCOMPLETE);
		setEditing(false);
	}

	public CompletionStatus getCompletionStatus() {
		return get(COMPLETION_STATUS_KEY);
	}

	public void setCompletionStatus(CompletionStatus completionStatus) {
		put(COMPLETION_STATUS_KEY, completionStatus);
	}

	public void setEditing(boolean editing) {
		put(EDITING_KEY, editing);
	}

	public boolean isEditing() {
		return get(EDITING_KEY);
	}
}
