package org.archstudio.bna.logics.events;

import org.archstudio.bna.IBNAWorld;

public interface IInternalWorldEventListener {

	public void innerWorldAdded(IBNAWorld world);

	public void innerWorldRemoved(IBNAWorld world);
}
