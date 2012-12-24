package org.archstudio.myx.fw;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class AbstractMyxFlexibleBrick implements IMyxBrick {

	private IMyxBrickItems brickItems = null;
	protected List<IMyxLifecycleProcessor> lifecycleProcessors = Collections
			.synchronizedList(new CopyOnWriteArrayList<IMyxLifecycleProcessor>());
	protected MyxBasicProvidedServiceProvider providedServiceProvider = new MyxBasicProvidedServiceProvider();

	public void addLifecycleProcessor(IMyxLifecycleProcessor lp) {
		lifecycleProcessors.add(lp);
	}

	public void removeLifecycleProcessor(IMyxLifecycleProcessor lp) {
		lifecycleProcessors.remove(lp);
	}

	@Override
	public Collection<? extends IMyxLifecycleProcessor> getLifecycleProcessors() {
		return Collections.unmodifiableList(lifecycleProcessors);
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
		return providedServiceProvider;
	}
}
