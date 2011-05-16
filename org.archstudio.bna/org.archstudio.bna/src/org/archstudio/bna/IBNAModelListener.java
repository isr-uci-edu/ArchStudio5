package org.archstudio.bna;

import org.archstudio.bna.IThing.IThingKey;

public interface IBNAModelListener {
	public <ET extends IThing, EK extends IThingKey<EV>, EV> void bnaModelChanged(BNAModelEvent<ET, EK, EV> evt);
}
