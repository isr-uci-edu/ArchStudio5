package org.archstudio.bna.logics;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThingLogic;
import org.archstudio.sysutils.SystemUtils;

public abstract class AbstractThingLogic implements IThingLogic {

	private IBNAWorld bnaWorld = null;

	@Override
	public IBNAWorld getBNAWorld() {
		return bnaWorld;
	}

	protected IBNAModel getBNAModel() {
		IBNAWorld bnaWorld = getBNAWorld();
		if (bnaWorld != null) {
			return bnaWorld.getBNAModel();
		}
		return null;
	}

	@Override
	public final void setBNAWorld(IBNAWorld newBNAWorld) {
		if (!SystemUtils.nullEquals(this.bnaWorld, newBNAWorld)) {
			if (this.bnaWorld != null) {
				destroy();
			}
			this.bnaWorld = newBNAWorld;
			if (newBNAWorld != null) {
				init();
			}
		}
	}

	protected void init() {
	}

	protected void destroy() {
	}
}
