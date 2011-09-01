package org.archstudio.bna.logics.coordinating;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.facets.IHasWorld;

public interface IInternalBNAModelListener {

	public <ET extends IThing, EK extends IThingKey<EV>, EV> void internalBNAModelChanged(IHasWorld src, BNAModelEvent<ET, EK, EV> evt);

	public <ET extends IThing, EK extends IThingKey<EV>, EV> void internalBNAModelChangedSync(IHasWorld src, BNAModelEvent<ET, EK, EV> evt);

}
