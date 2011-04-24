package org.archstudio.bna.logics.coordinating;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.facets.IHasWorld;

public interface IInternalBNAModelListener {

	public void internalBNAModelChanged(IHasWorld src, BNAModelEvent evt);

	public void internalBNAModelChangedSync(IHasWorld src, BNAModelEvent evt);

}
