package org.archstudio.myx.fw;

import java.util.Collection;
import java.util.Collections;

public abstract class AbstractMyxSimpleBrick implements IMyxBrick, IMyxLifecycleProcessor, IMyxProvidedServiceProvider {

	private IMyxBrickItems brickItems = null;

	@Override
	public Collection<? extends IMyxLifecycleProcessor> getLifecycleProcessors() {
		return Collections.singleton(this);
	}

	@Override
	public void setMyxBrickItems(IMyxBrickItems brickItems) {
		this.brickItems = brickItems;
	}

	@Override
	public IMyxBrickItems getMyxBrickItems() {
		return brickItems;
	}

	@Override
	public IMyxProvidedServiceProvider getProvidedServiceProvider() {
		return this;
	}

	@Override
	public void init() {
	}

	@Override
	public void begin() {
	}

	@Override
	public void end() {
	}

	@Override
	public void destroy() {
	}
}
