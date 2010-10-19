package org.archstudio.bna;

public abstract class AbstractThingLogic implements IThingLogic {

	protected IBNAWorld bnaWorld = null;

	public IBNAWorld getBNAWorld() {
		return bnaWorld;
	}

	public void setBNAWorld(IBNAWorld newBNAWorld) {
		if (BNAUtils.nulleq(this.bnaWorld, newBNAWorld))
			return;
		if (this.bnaWorld != null) {
			destroy();
		}
		this.bnaWorld = newBNAWorld;
		if (newBNAWorld != null) {
			init();
		}
	}

	/* Convenience method */
	public IBNAModel getBNAModel() {
		IBNAWorld w = getBNAWorld();
		if (w != null) {
			return w.getBNAModel();
		}
		return null;
	}

	public void init() {
	}

	public void destroy() {
	}
}
