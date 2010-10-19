package org.archstudio.myx.fw;

import java.util.Collection;

public interface IMyxBrick {
	public void setMyxBrickItems(IMyxBrickItems brickItems);

	public IMyxBrickItems getMyxBrickItems();

	public Collection<? extends IMyxLifecycleProcessor> getLifecycleProcessors();

	public IMyxProvidedServiceProvider getProvidedServiceProvider();
}
