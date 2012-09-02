package org.archstudio.bna.things.swt;

import org.archstudio.bna.constants.CompletionStatus;
import org.archstudio.bna.facets.IHasMutableCompletionStatus;
import org.archstudio.bna.facets.IHasMutableEditing;
import org.archstudio.bna.things.AbstractRectangleThing;

public class AbstractSWTThing extends AbstractRectangleThing implements IHasMutableCompletionStatus, IHasMutableEditing {

	public AbstractSWTThing(Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		setCompletionStatus(CompletionStatus.INCOMPLETE);
		setEditing(false);
	}

	@Override
	public CompletionStatus getCompletionStatus() {
		return get(COMPLETION_STATUS_KEY);
	}

	@Override
	public void setCompletionStatus(CompletionStatus completionStatus) {
		set(COMPLETION_STATUS_KEY, completionStatus);
	}

	@Override
	public void setEditing(boolean editing) {
		set(EDITING_KEY, editing);
	}

	@Override
	public boolean isEditing() {
		return get(EDITING_KEY, false);
	}
}
