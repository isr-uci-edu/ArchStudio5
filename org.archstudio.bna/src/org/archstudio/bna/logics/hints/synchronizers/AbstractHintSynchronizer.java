package org.archstudio.bna.logics.hints.synchronizers;

import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.logics.hints.IHintSynchronizer;
import org.eclipse.jdt.annotation.Nullable;

public abstract class AbstractHintSynchronizer implements IHintSynchronizer {

	protected IBNAWorld bnaWorld;

	public AbstractHintSynchronizer() {
	}

	@Override
	public void setBNAWorld(@Nullable IBNAWorld bnaWorld) {
		this.bnaWorld = bnaWorld;
	}

}
