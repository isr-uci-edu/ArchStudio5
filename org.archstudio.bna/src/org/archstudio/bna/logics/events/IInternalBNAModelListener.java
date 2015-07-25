package org.archstudio.bna.logics.events;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.facets.IHasWorld;

public interface IInternalBNAModelListener {
	public void internalBNAModelChanged(IHasWorld worldThing, BNAModelEvent evt);
}