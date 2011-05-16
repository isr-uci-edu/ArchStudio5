package org.archstudio.bna;

import org.archstudio.bna.IThing.IThingKey;

public interface IBNASynchronousModelListener {
	public <ET extends IThing, EK extends IThingKey<EV>, EV> void bnaModelChangedSync(BNAModelEvent<ET, EK, EV> evt);
}
