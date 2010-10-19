package org.archstudio.bna.assemblies;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.facets.IHasAssemblyData;

public abstract class AbstractAssembly implements IAssembly {
	private int lastIndexValue = Integer.MIN_VALUE;

	protected IBNAModel model;
	protected IHasAssemblyData rootThing;

	public AbstractAssembly(IBNAModel model, IHasAssemblyData rootThing) {
		this.model = model;
		this.rootThing = rootThing;
		reindex();
	}

	protected void reindexIfNeeded() {
		int indexValue = rootThing.getIndexValue();
		if (indexValue != lastIndexValue) {
			reindex();
			lastIndexValue = indexValue;
		}
	}

	protected abstract void reindex();

	public IHasAssemblyData getRootThing() {
		return rootThing;
	}
}
