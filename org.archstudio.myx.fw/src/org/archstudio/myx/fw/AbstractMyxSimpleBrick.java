package org.archstudio.myx.fw;

import java.util.Collection;
import java.util.Collections;

public abstract class AbstractMyxSimpleBrick implements IMyxBrick, IMyxLifecycleProcessor, IMyxProvidedServiceProvider {

	private IMyxBrickItems brickItems = null;

	public Collection<? extends IMyxLifecycleProcessor> getLifecycleProcessors() {
		return Collections.singleton(this);
	}

	public void setMyxBrickItems(IMyxBrickItems brickItems) {
		this.brickItems = brickItems;
	}

	public IMyxBrickItems getMyxBrickItems() {
		return brickItems;
	}

	public IMyxProvidedServiceProvider getProvidedServiceProvider() {
		return this;
	}

	public void init() {
	}

	public void begin() {
	}

	public void end() {
	}

	public void destroy() {
	}
}
